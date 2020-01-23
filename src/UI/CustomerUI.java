package UI;

import Dialog.*;
import Elements.Item;
import Elements.Order;
import Processor.CustomerProcessor;
import Table.ItemTable;
import Table.OrderItemTable;
import Table.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class CustomerUI extends UserUI {

    // Left ToolBar Components
    private Button      shopCartButton;
    private ImageView   shopCartImage;
    private String      shopCartImagePath;
    private Text        shopCartText;
    private VBox        shopCartBox;

    private Button      orderButton;
    private ImageView   orderImage;
    private String      orderImagePath;
    private Text        orderText;
    private VBox        orderBox;

    // Right ToolBar - Item Components
    private Label       quantity;
    private TextField   qtyBar;
    private HBox        qtyBox;
    private ImageView   toCartImage;
    private String      toCartImagePath;
    private Button      toCartButton;
    private Label       toCartLabel;
    private VBox        toCartBox;
    private HBox        toCartInfoBox;

    // Right ToolBar - Shopping Cart Components
    private Button      itemEditButton;
    private Button      itemDeleteButton;
    private HBox        itemButtonBox;
    private ImageView   toOrderImage;
    private String      toOrderImagePath;
    private Button      toOrderButton;
    private Label       toOrderLabel;
    private VBox        toOrderBox;

    // Right ToolBar - Order Items
    private OrderItemTable orderItemTable;
    private ObservableList<Item> orderItems;

    // Workspace - Tables
    private ItemTable   shopCartTable;
    private OrderTable  orderTable;

    // Workspace - Lists
    private ObservableList<Item> shopCart;
    private ObservableList<Order> orders;

    // Dialog
    private final CartEditDialog cartEditDialog = CartEditDialog.getCartEditDialog();


    public CustomerUI(AppUI mainUI) {
        super(mainUI);
        processor = new CustomerProcessor(this);
        cartEditDialog.initialize(primaryStage, (CustomerProcessor) processor);
    }

    public HBox getToCartInfoBox() { return toCartInfoBox; }

    public ObservableList getShopCart() { return shopCart; }

    @Override
    public void initialize() {
        initLeftToolBar();
        initRightToolBar();
        initWorkspace();
    }

    @Override
    public void initLeftToolBar(){
        shopCartImagePath   = "shopCart.png";
        shopCartText        = new Text("Shopping Cart");
        shopCartImage       = new ImageView();
        shopCartButton      = new Button();
        shopCartBox         = new VBox();

        orderImagePath      = "order.png";
        orderText           = new Text("Orders");
        orderImage          = new ImageView();
        orderButton         = new Button();
        orderBox            = new VBox();

        leftToolBar         = new ToolBar();
    }

    @Override
    public void initRightToolBar() {
        initItemUI();
        initShopCartUI();
        initOrderItemUI();

        rightToolBar = new BorderPane();
    }

    public void initItemUI(){
        quantity        = new Label("Quantity:");
        qtyBar          = new TextField();
        qtyBox          = new HBox();

        toCartImage     = new ImageView();
        toCartImagePath = "toShopCart.png";
        toCartButton    = new Button();
        toCartLabel     = new Label("To Shopping Cart");
        toCartBox       = new VBox();

        toCartInfoBox   = new HBox();
    }

    public void initShopCartUI(){
        itemEditButton          = new Button("Edit");
        itemDeleteButton        = new Button("Remove");
        itemButtonBox           = new HBox();

        toOrderImage            = new ImageView();
        toOrderImagePath        = "toOrder.png";
        toOrderButton           = new Button();
        toOrderLabel            = new Label("To Order");
        toOrderBox              = new VBox();
    }

    public void initOrderItemUI(){
        orderItems = FXCollections.observableArrayList();
        orderItemTable = new OrderItemTable(orderItems);
    }

    public void initWorkspace(){
        shopCart = FXCollections.observableArrayList();
        shopCartTable = new ItemTable(shopCart);
        orders = FXCollections.observableArrayList();
        orderTable = new OrderTable(orders);
        table = shopCartTable;
    }

    @Override
    public void layout() {
        layoutLeftToolBar();
        layoutRightToolBar();
    }

    @Override
    public void layoutLeftToolBar(){
        shopCartImage.setImage(new Image(shopCartImagePath));
        shopCartImage.setFitHeight(50);
        shopCartImage.setFitWidth(50);
        shopCartButton.setGraphic(shopCartImage);
        shopCartButton.setStyle("-fx-border-color: white;");
        shopCartBox.getChildren().addAll(shopCartButton, shopCartText);
        shopCartBox.setAlignment(Pos.CENTER);
        shopCartBox.setSpacing(3);
        shopCartBox.setPadding(new Insets(30));

        orderImage.setImage(new Image(orderImagePath));
        orderImage.setFitHeight(50);
        orderImage.setFitWidth(50);
        orderButton.setGraphic(orderImage);
        orderButton.setStyle("-fx-border-color: white;");
        orderBox.getChildren().addAll(orderButton, orderText);
        orderBox.setAlignment(Pos.CENTER);
        orderBox.setSpacing(3);
        orderBox.setPadding(new Insets(30));

        leftToolBar.setOrientation(Orientation.VERTICAL);
        leftToolBar.setPrefWidth(175);
        leftToolBar.setPadding(new Insets(8,10,8,10));
        leftToolBar.getItems().addAll(shopCartBox, orderBox);
    }

    @Override
    public void layoutRightToolBar() {
        layoutItemUI();
        layoutShopCartUI();
        layoutOrderItemUI();
    }

    public void layoutItemUI(){
        qtyBar.setPrefSize(50, 30);
        qtyBox.setAlignment(Pos.CENTER_LEFT);
        qtyBox.setSpacing(5);
        qtyBox.getChildren().addAll(quantity, qtyBar);

        toCartImage.setImage(new Image(toCartImagePath));
        toCartImage.setFitHeight(30);
        toCartImage.setFitWidth(30);
        toCartButton.setGraphic(toCartImage);
        toCartButton.setStyle("-fx-border-color: white;");
        toCartBox.setAlignment(Pos.CENTER);
        toCartBox.setSpacing(5);
        toCartBox.setPadding(new Insets(20));
        toCartBox.getChildren().addAll(toCartButton, toCartLabel);

        toCartInfoBox.setAlignment(Pos.CENTER);
        toCartInfoBox.setSpacing(60);
        toCartInfoBox.setPadding(new Insets(20));
        toCartInfoBox.getChildren().addAll(qtyBox, toCartBox);
    }

    public void layoutShopCartUI(){
        itemButtonBox.setSpacing(30);
        itemButtonBox.setAlignment(Pos.CENTER);
        itemButtonBox.setPadding(new Insets(20));
        itemButtonBox.getChildren().addAll(itemEditButton, itemDeleteButton);

        toOrderImage.setImage(new Image(toOrderImagePath));
        toOrderImage.setFitWidth(30);
        toOrderImage.setFitHeight(30);
        toOrderButton.setGraphic(toOrderImage);
        toOrderButton.setStyle("-fx-border-color: white;");
        toOrderBox.setAlignment(Pos.CENTER);
        toOrderBox.setSpacing(5);
        toOrderBox.setPadding(new Insets(20));
        toOrderBox.getChildren().addAll(toOrderButton, toOrderLabel);
    }

    public void layoutOrderItemUI() {
        orderItemTable.getTable().setPrefSize(350, 500);
        orderItemTable.getTable().setEditable(true);
    }

    @Override
    public void setHandlers() {
        shopCartButton.setOnAction(e -> {
            showShopCart();
            mainUI.refreshUI();
        });

        orderButton.setOnAction(e -> {
            showOrders();
            mainUI.refreshUI();
        });

        toCartButton.setOnAction(e -> {
            Item item = (Item) mainUI.getTable().getTable().getSelectionModel().getSelectedItem();
            int itemId = item.getId();
            int qty = Integer.parseInt(qtyBar.getText());
            ((CustomerProcessor) processor).handleToCart(itemId, qty);
            showShopCart();
            mainUI.refreshUI();
        });

        itemEditButton.setOnAction(e -> {
            Item item = (Item) shopCartTable.getTable().getSelectionModel().getSelectedItem();
            cartEditDialog.setItem(item.getId());
            cartEditDialog.showAndWait();
            showShopCart();
            mainUI.refreshUI();
        });

        itemDeleteButton.setOnAction(e -> {
            Item item = (Item) shopCartTable.getTable().getSelectionModel().getSelectedItem();
            ((CustomerProcessor) processor).handleDeleteCartItem(item.getId());
            showShopCart();
            mainUI.refreshUI();
        });

        toOrderButton.setOnAction(e -> {
            ((CustomerProcessor) processor).handleToOrder();
            showOrders();
            mainUI.refreshUI();
        });

        shopCartTable.getTable().setOnMouseClicked(e -> {
            rightToolBar.setTop(itemButtonBox);
            rightToolBar.setCenter(null);
            rightToolBar.setBottom(toOrderBox);
            mainUI.refreshUI();
        });

        orderTable.getTable().setOnMouseClicked(e -> {
            Order order = (Order) orderTable.getTable().getSelectionModel().getSelectedItem();
            showOrderItem(order.getId());
            mainUI.refreshUI();
        });
    }

    public void showShopCart(){
        ObservableList shopCart = ((CustomerProcessor) processor).handleShopCart();
        if(shopCart != null)
            this.shopCart.setAll(shopCart);
        table = shopCartTable;
    }

    public void showOrders() {
        ObservableList orders = ((CustomerProcessor) processor).handleOrder();
        if(orders != null)
            this.orders.setAll(orders);
        table = orderTable;
    }

    public void showOrderItem(int orderId) {
        ObservableList orderItems = ((CustomerProcessor) processor).handleOrderItems(orderId);
        if(orderItems != null)
            this.orderItems.setAll(orderItems);
        rightToolBar.setTop(null);
        rightToolBar.setCenter(orderItemTable.getTable());
        rightToolBar.setBottom(null);
    }

    @Override
    public void clear() {

    }
}
