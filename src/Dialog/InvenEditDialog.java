package Dialog;

import Processor.SellerProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvenEditDialog extends Stage {

    private static InvenEditDialog       invenEditDialog;

    private SellerProcessor             processor;
    private int                         item;

    private Label                       nameLabel;
    private TextField                   nameBar;
    private HBox                        nameBox;
    private Label                       priceLabel;
    private TextField                   priceBar;
    private HBox                        priceBox;
    private Label                       qtyLabel;
    private TextField                   qtyBar;
    private HBox                        qtyBox;
    private Label                       descriptLabel;
    private TextArea                    descriptArea;
    private VBox                        descriptBox;

    private Button editButton;

    public static InvenEditDialog getInvenEditDialog() {
        if(invenEditDialog == null)
            invenEditDialog = new InvenEditDialog();

        return invenEditDialog;
    }

    private InvenEditDialog() {}

    public void initialize(Stage owner, SellerProcessor processor) {
        this.processor = processor;

        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Editing Item");
        initComponent();
        layout();
        setHandlers();
    }

    public void setItem(int id) {item = id;}

    public void initComponent() {
        nameLabel           = new Label("Item Name:");
        nameBar             = new TextField();
        nameBox             = new HBox(nameLabel, nameBar);
        priceLabel          = new Label("Unit Price:");
        priceBar            = new TextField();
        priceBox            = new HBox(priceLabel, priceBar);
        qtyLabel            = new Label("Quantities:");
        qtyBar              = new TextField();
        qtyBox              = new HBox(qtyLabel, qtyBar);
        descriptLabel       = new Label("Description:");
        descriptArea        = new TextArea();
        descriptBox         = new VBox(descriptLabel, descriptArea);

        editButton           = new Button("Edit Item");
    }

    public void layout() {
        descriptArea.setPrefSize(100, 100);
        descriptBox.setAlignment(Pos.TOP_LEFT);
        descriptBox.setSpacing(5);

        VBox pane = new VBox(nameBox, priceBox, qtyBox);
        for(Node node : pane.getChildren()){
            HBox box = (HBox) node;
            box.setSpacing(5);
            box.setAlignment(Pos.CENTER);
        }
        pane.getChildren().addAll(descriptBox, editButton);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public void setHandlers() {
        editButton.setOnAction(e -> {
            String name = nameBar.getText();
            double price = Double.parseDouble(priceBar.getText());
            int qty = Integer.parseInt(qtyBar.getText());
            String description = descriptArea.getText();
            processor.handleEditItem(item, name, description, price, qty);

            clear();
            this.close();
        });
    }

    public void clear() {
        nameBar.clear();
        priceBar.clear();
        qtyBar.clear();
        descriptArea.clear();
    }

}
