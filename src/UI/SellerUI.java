package UI;

import Dialog.InvenAddDialog;
import Dialog.InvenEditDialog;
import Elements.Item;
import Elements.Sales;
import Processor.SellerProcessor;
import Table.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class SellerUI extends UserUI {

    // Left ToolBar Components
    private Button      inventoryButton;
    private ImageView   inventoryImage;
    private String      inventoryImagePath;
    private Text        inventoryText;
    private VBox        inventoryBox;

    private Button      salesButton;
    private ImageView   salesImage;
    private String      salesImagePath;
    private Text        salesText;
    private VBox        salesBox;

    // Right ToolBar - Inventory Components
    private Button      addItemButton;
    private Button      editItemButton;
    private Button      removeItemButton;
    private VBox        itemButtonBox;

    // Right ToolBar - Sales Components
    private Label       totalProfitLabel;
    private Label       totalSalesLabel;

    // Workspace - Tables
    private InventoryTable  inventoryTable;
    private SalesTable      salesTable;

    // Workspace - Lists
    private ObservableList<Item> inventory;
    private ObservableList<Sales> sales;

    private final InvenAddDialog    invenAddDialog  = InvenAddDialog.getInvenAddDialog();
    private final InvenEditDialog   invenEditDialog = InvenEditDialog.getInvenEditDialog();


    public SellerUI(AppUI mainUI) {
        super(mainUI);
        processor = new SellerProcessor(this);
        invenAddDialog.initialize(primaryStage, (SellerProcessor) processor);
        invenEditDialog.initialize(primaryStage, (SellerProcessor) processor);
    }

    @Override
    public void initialize() {
        initLeftToolBar();
        initRightToolBar();
        initWorkspace();
    }

    @Override
    public void initLeftToolBar(){
        inventoryImagePath   = "inventory.png";
        inventoryText        = new Text("Inventory");
        inventoryImage       = new ImageView();
        inventoryButton      = new Button();
        inventoryBox         = new VBox();

        salesImagePath       = "sales.png";
        salesText            = new Text("Sales History & Analysis");
        salesImage           = new ImageView();
        salesButton          = new Button();
        salesBox             = new VBox();

        leftToolBar          = new ToolBar();
    }

    @Override
    public void initRightToolBar() {
        addItemButton       = new Button("Add new Item");
        editItemButton      = new Button("Modify Item");
        removeItemButton    = new Button("Remove this Item");
        itemButtonBox       = new VBox();

        totalProfitLabel    = new Label("Total Profit:");
        totalSalesLabel     = new Label("Total Number of Sales:");

        rightToolBar        = new BorderPane();
    }

    public void initWorkspace(){
        inventory = FXCollections.observableArrayList();
        inventoryTable = new InventoryTable(inventory);
        sales = FXCollections.observableArrayList();
        salesTable = new SalesTable(sales);
        table = inventoryTable;
    }

    @Override
    public void layout() {
        layoutLeftToolBar();
        layoutRightToolBar();
    }

    @Override
    public void layoutLeftToolBar(){
        inventoryImage.setImage(new Image(inventoryImagePath));
        inventoryImage.setFitHeight(50);
        inventoryImage.setFitWidth(50);
        inventoryButton.setGraphic(inventoryImage);
        inventoryBox.getChildren().addAll(inventoryButton, inventoryText);
        inventoryBox.setAlignment(Pos.CENTER);
        inventoryBox.setSpacing(3);
        inventoryBox.setPadding(new Insets(30));

        salesImage.setImage(new Image(salesImagePath));
        salesImage.setFitHeight(50);
        salesImage.setFitWidth(50);
        salesButton.setGraphic(salesImage);
        salesBox.getChildren().addAll(salesButton, salesText);
        salesBox.setAlignment(Pos.CENTER);
        salesBox.setSpacing(3);
        salesBox.setPadding(new Insets(30));

        leftToolBar.setOrientation(Orientation.VERTICAL);
        leftToolBar.setPrefWidth(175);
        leftToolBar.setPadding(new Insets(8,10,8,10));
        leftToolBar.getItems().addAll(inventoryBox, salesBox);
    }

    @Override
    public void layoutRightToolBar() {
        layoutInventoryUI();
    }

    public void layoutInventoryUI(){
        itemButtonBox.setAlignment(Pos.CENTER);
        itemButtonBox.setPadding(new Insets(20));
        itemButtonBox.setSpacing(30);
        itemButtonBox.getChildren().addAll(addItemButton, editItemButton, removeItemButton);
    }

    @Override
    public void setHandlers() {
        inventoryButton.setOnAction(e -> {
            showInventory();
            mainUI.refreshUI();
        });

        salesButton.setOnAction(e -> {
            showSales();
            showAnalysis();
            mainUI.refreshUI();
        });

        addItemButton.setOnAction(e -> {
            invenAddDialog.showAndWait();
            showInventory();
            mainUI.refreshUI();
        });

        editItemButton.setOnAction(e -> {
            Item item = (Item) inventoryTable.getTable().getSelectionModel().getSelectedItem();
            if(item != null) {
                invenEditDialog.setItem(item.getId());
                invenEditDialog.showAndWait();
                showInventory();
                mainUI.refreshUI();
            }
        });

        removeItemButton.setOnAction(e -> {
            Item item = (Item) inventoryTable.getTable().getSelectionModel().getSelectedItem();
            if(item != null) {
                ((SellerProcessor) processor).handleRemoveItem(item.getId());
                showInventory();
                mainUI.refreshUI();
            }
        });

        inventoryTable.getTable().setOnMouseClicked(e -> {
            rightToolBar.setTop(itemButtonBox);
            rightToolBar.setCenter(null);
            rightToolBar.setBottom(null);
            mainUI.refreshUI();
        });
    }

    public void showInventory(){
        ObservableList<Item> inventory = ((SellerProcessor) processor).handleInventory();
        if(inventory != null)
            this.inventory.setAll(inventory);
        table = inventoryTable;
        rightToolBar.setTop(itemButtonBox);
        rightToolBar.setCenter(null);
        rightToolBar.setBottom(null);
    }

    public void showSales() {
        ObservableList sales = ((SellerProcessor) processor).handleSales();
        if(sales != null)
            this.sales.setAll(sales);
        table = salesTable;
    }

    public void showAnalysis(){
        double totalProfit = 0;
        for(Sales sale : sales)
            totalProfit += sale.getNet();
        Text totalProfitText = new Text("$ "+totalProfit);
        HBox totalProfitBox = new HBox(totalProfitLabel, totalProfitText);
        Text totalSalesText = new Text(""+sales.size());
        HBox totalSalesBox = new HBox(totalSalesLabel, totalSalesText);

        VBox analysis = new VBox(totalProfitBox, totalSalesBox);
        for(Node node : analysis.getChildren()){
            HBox box = (HBox) node;
            box.setAlignment(Pos.CENTER);
            box.setSpacing(5);
        }
        analysis.setAlignment(Pos.CENTER);
        analysis.setSpacing(10);

        rightToolBar.setTop(analysis);
        rightToolBar.setCenter(null);
        rightToolBar.setBottom(null);
    }

    @Override
    public void clear() {

    }
}
