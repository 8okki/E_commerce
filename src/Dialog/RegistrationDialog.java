package Dialog;

import Processor.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrationDialog extends Dialog {

    private static RegistrationDialog   registrationDialog;

    private final static CustomerRegDialog    customerRegDialog = CustomerRegDialog.getCustomerRegDialog();
    private final static SellerRegDialog      sellerRegDialog = SellerRegDialog.getSellerRegDialog();
    private final static EmployeeRegDialog    employeeRegDialog = EmployeeRegDialog.getEmployeeRegDialog();

    private Button      customerButton;
    private Button      sellerButton;
    private Button      employeeButton;


    private RegistrationDialog() {}

    public static RegistrationDialog getRegistrationDialog() {
        if(registrationDialog == null)
            registrationDialog = new RegistrationDialog();

        return registrationDialog;
    }

    @Override
    public void initialize(Stage owner, AppProcessor processor) {

        this.processor = processor;

        // Initialize
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        customerRegDialog.initialize(owner, processor);
        sellerRegDialog.initialize(owner, processor);
        employeeRegDialog.initialize(owner, processor);
        setTitle("Creating an Account");
        initComponent();

        // Layout
        layout();

        // Set Handlers
        setHandlers();
    }

    @Override
    public void initComponent() {
        dialogPane      = new HBox();
        customerButton  = new Button("I'm a Customer");
        sellerButton    = new Button("I'm a Seller");
        employeeButton  = new Button("I'm a Employee");
    }

    @Override
    public void layout() {
        HBox pane = (HBox) dialogPane;
        pane.getChildren().addAll(customerButton, sellerButton, employeeButton);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene dialogScene = new Scene(pane);
        setScene(dialogScene);
    }

    @Override
    public void setHandlers() {
        customerButton.setOnAction(e -> {
            customerRegDialog.show();
            this.close();
        });

        sellerButton.setOnAction(e -> {
            sellerRegDialog.show();
            this.close();
        });

        employeeButton.setOnAction(e -> {
            employeeRegDialog.show();
            this.close();
        });
    }

    @Override
    public void clear() { }
}
