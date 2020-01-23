package Dialog;

import Processor.SellerProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvenAddDialog extends Stage {

    private static InvenAddDialog       invenAddDialog;

    private SellerProcessor             processor;

    private Label                       nameLabel;
    private TextField                   nameBar;
    private HBox                        nameBox;
    private Label                       categoryLabel;
    private TextField                   categoryBar;
    private HBox                        categoryBox;
    private Label                       priceLabel;
    private TextField                   priceBar;
    private HBox                        priceBox;
    private Label                       qtyLabel;
    private TextField                   qtyBar;
    private HBox                        qtyBox;
    private Label                       descriptLabel;
    private TextArea                    descriptArea;
    private VBox                        descriptBox;

    private Button                      addButton;

    public static InvenAddDialog getInvenAddDialog() {
        if(invenAddDialog == null)
            invenAddDialog = new InvenAddDialog();

        return invenAddDialog;
    }

    private InvenAddDialog() {}

    public void initialize(Stage owner, SellerProcessor processor) {
        this.processor = processor;

        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Adding new Item");
        initComponent();
        layout();
        setHandlers();
    }

    public void initComponent() {
        nameLabel           = new Label("Item Name:");
        nameBar             = new TextField();
        nameBox             = new HBox(nameLabel, nameBar);
        categoryLabel           = new Label("Category:");
        categoryBar             = new TextField();
        categoryBox             = new HBox(categoryLabel, categoryBar);
        priceLabel          = new Label("Unit Price:");
        priceBar            = new TextField();
        priceBox            = new HBox(priceLabel, priceBar);
        qtyLabel            = new Label("Quantities:");
        qtyBar              = new TextField();
        qtyBox              = new HBox(qtyLabel, qtyBar);
        descriptLabel       = new Label("Description:");
        descriptArea        = new TextArea();
        descriptBox         = new VBox(descriptLabel, descriptArea);

        addButton           = new Button("Add Item");
    }

    public void layout() {
        descriptArea.setPrefSize(100, 100);
        descriptBox.setAlignment(Pos.TOP_LEFT);
        descriptBox.setSpacing(5);

        VBox pane = new VBox(nameBox, categoryBox, priceBox, qtyBox);
        for(Node node : pane.getChildren()){
            HBox box = (HBox) node;
            box.setSpacing(5);
            box.setAlignment(Pos.CENTER);
        }
        pane.getChildren().addAll(descriptBox, addButton);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public void setHandlers() {
        addButton.setOnAction(e -> {
            String name = nameBar.getText();
            String category = categoryBar.getText();
            double price = Double.parseDouble(priceBar.getText());
            int qty = Integer.parseInt(qtyBar.getText());
            String description = descriptArea.getText();
            processor.handleAddItem(name, category, description, price, qty);

            clear();
            this.close();
        });
    }

    public void clear() {
        nameBar.clear();
        categoryBar.clear();
        priceBar.clear();
        qtyBar.clear();
        descriptArea.clear();
    }
}
