package Dialog;

import Processor.CustomerProcessor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CartEditDialog extends Stage {

    private static CartEditDialog       cartEditDialog;

    private CustomerProcessor           processor;
    private int                         item;

    private Text            qtyText;
    private TextField       qtyBar;
    private HBox            qtyBox;
    private Button          editButton;


    public static CartEditDialog getCartEditDialog(){
        if(cartEditDialog == null)
            cartEditDialog = new CartEditDialog();

        return cartEditDialog;
    }

    private CartEditDialog() {}

    public void initialize(Stage owner, CustomerProcessor processor) {
        this.processor = processor;

        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Editing Shopping Cart Item");
        initComponent();
        layout();
        setHandlers();
    }

    public void setItem(int id) {item = id;}

    public void initComponent() {
        qtyText     = new Text("Quantity");
        qtyBar      = new TextField();
        qtyBox      = new HBox(qtyText, qtyBar);
        editButton  = new Button("Edit Item");
    }

    public void layout() {
        qtyBox.setSpacing(5);
        qtyBox.setAlignment(Pos.CENTER);

        VBox pane   = new VBox(qtyBox, editButton);
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public void setHandlers() {
        editButton.setOnAction(e -> {
            int qty = Integer.parseInt(qtyBar.getText());
            processor.handleEditCartItem(qty, item);
            clear();
            this.close();
        });
    }

    public void clear() {
        qtyBar.clear();
    }
}
