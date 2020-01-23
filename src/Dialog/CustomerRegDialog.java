package Dialog;

import Processor.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerRegDialog extends Dialog {

    private static CustomerRegDialog    customerRegDialog;

    private Text        firstnameText;
    private TextField   firstnameBar;
    private HBox        firstnameBox;
    private Text        lastnameText;
    private TextField   lastnameBar;
    private HBox        lastnameBox;
    private Text        emailText;
    private TextField   emailBar;
    private HBox        emailBox;
    private Text        phoneText;
    private TextField   phoneBar;
    private HBox        phoneBox;
    private Text        passwordText;
    private TextField   passwordBar;
    private HBox        passwordBox;

    private Text        cardNumText;
    private TextField   cardNumBar;
    private HBox        cardNumBox;
    private Text        typeText;
    private TextField   typeBar;
    private HBox        typeBox;
    private Text        nameText;
    private TextField   nameBar;
    private HBox        nameBox;
    private Text        expireText;
    private TextField   expireBar;
    private HBox        expireBox;
    private Text        securityText;
    private TextField   securityBar;
    private HBox        securityBox;

    private Text        nicknameText;
    private TextField   nicknameBar;
    private HBox        nicknameBox;
    private Text        street1Text;
    private TextField   street1Bar;
    private HBox        street1Box;
    private Text        street2Text;
    private TextField   street2Bar;
    private HBox        street2Box;
    private Text        cityText;
    private TextField   cityBar;
    private HBox        cityBox;
    private Text        stateText;
    private TextField   stateBar;
    private HBox        stateBox;
    private Text        countryText;
    private TextField   countryBar;
    private HBox        countryBox;
    private Text        zipText;
    private TextField   zipBar;
    private HBox        zipBox;
    private Text        addressPhoneText;
    private TextField   addressPhoneBar;
    private HBox        addressPhoneBox;

    private Button      registerButton;

    private CustomerRegDialog() { }

    public static CustomerRegDialog getCustomerRegDialog(){
        if(customerRegDialog == null)
            customerRegDialog = new CustomerRegDialog();

        return customerRegDialog;
    }

    @Override
    public void initialize(Stage owner, AppProcessor processor) {

        this.processor = processor;

        // Initialize
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Creating a Customer Account");
        initComponent();

        // Layout
        layout();

        // Set Handler
        setHandlers();
    }

    @Override
    public void initComponent() {

        dialogPane      = new VBox();

        firstnameText   = new Text("First Name:");
        firstnameBar    = new TextField();
        firstnameBox    = new HBox();
        lastnameText    = new Text("Last Name:");
        lastnameBar     = new TextField();
        lastnameBox     = new HBox();
        emailText       = new Text("Email:");
        emailBar        = new TextField();
        emailBox        = new HBox();
        phoneText       = new Text("Phone Number:");
        phoneBar        = new TextField();
        phoneBox        = new HBox();
        passwordText    = new Text("Password:");
        passwordBar     = new TextField();
        passwordBox     = new HBox();
        registerButton  = new Button("Create Account");

        cardNumText     = new Text("Card Number:");
        cardNumBar      = new TextField();
        cardNumBox      = new HBox();
        typeText        = new Text("Payment Type:");
        typeBar         = new TextField();
        typeBox         = new HBox();
        nameText        = new Text("CardHolder Name:");
        nameBar         = new TextField();
        nameBox         = new HBox();
        expireText      = new Text("Expire Year:");
        expireBar       = new TextField();
        expireBox       = new HBox();
        securityText    = new Text("Security Number:");
        securityBar     = new TextField();
        securityBox     = new HBox();

        nicknameText        = new Text("Address Nickname:");
        nicknameBar         = new TextField();
        nicknameBox         = new HBox();
        street1Text         = new Text("Street 1:");
        street1Bar          = new TextField();
        street1Box          = new HBox();
        street2Text         = new Text("Street 2:");
        street2Bar          = new TextField();
        street2Box          = new HBox();
        cityText            = new Text("City:");
        cityBar             = new TextField();
        cityBox             = new HBox();
        stateText           = new Text("State:");
        stateBar            = new TextField();
        stateBox            = new HBox();
        countryText         = new Text("Country:");
        countryBar          = new TextField();
        countryBox          = new HBox();
        zipText             = new Text("Zip:");
        zipBar              = new TextField();
        zipBox              = new HBox();
        addressPhoneText    = new Text("Phone Number:");
        addressPhoneBar     = new TextField();
        addressPhoneBox     = new HBox();
    }

    @Override
    public void layout() {
        firstnameBox.getChildren().addAll(firstnameText, firstnameBar);
        firstnameBox.setSpacing(5);
        firstnameBox.setAlignment(Pos.CENTER);

        lastnameBox.getChildren().addAll(lastnameText, lastnameBar);
        lastnameBox.setSpacing(5);
        lastnameBox.setAlignment(Pos.CENTER);

        emailBox.getChildren().addAll(emailText, emailBar);
        emailBox.setSpacing(5);
        emailBox.setAlignment(Pos.CENTER);

        phoneBox.getChildren().addAll(phoneText, phoneBar);
        phoneBox.setSpacing(5);
        phoneBox.setAlignment(Pos.CENTER);

        passwordBox.getChildren().addAll(passwordText, passwordBar);
        passwordBox.setSpacing(5);
        passwordBox.setAlignment(Pos.CENTER);

        cardNumBox.getChildren().addAll(cardNumText, cardNumBar);
        cardNumBox.setSpacing(5);
        cardNumBox.setAlignment(Pos.CENTER);

        typeBox.getChildren().addAll(typeText, typeBar);
        typeBox.setSpacing(5);
        typeBox.setAlignment(Pos.CENTER);

        nameBox.getChildren().addAll(nameText, nameBar);
        nameBox.setSpacing(5);
        nameBox.setAlignment(Pos.CENTER);

        expireBox.getChildren().addAll(expireText, expireBar);
        expireBox.setSpacing(5);
        expireBox.setAlignment(Pos.CENTER);

        securityBox.getChildren().addAll(securityText, securityBar);
        securityBox.setSpacing(5);
        securityBox.setAlignment(Pos.CENTER);

        nicknameBox.getChildren().addAll(nicknameText, nicknameBar);
        nicknameBox.setSpacing(5);
        nicknameBox.setAlignment(Pos.CENTER);

        street1Box.getChildren().addAll(street1Text, street1Bar);
        street1Box.setSpacing(5);
        street1Box.setAlignment(Pos.CENTER);

        street2Box.getChildren().addAll(street2Text, street2Bar);
        street2Box.setSpacing(5);
        street2Box.setAlignment(Pos.CENTER);

        cityBox.getChildren().addAll(cityText, cityBar);
        cityBox.setSpacing(5);
        cityBox.setAlignment(Pos.CENTER);

        stateBox.getChildren().addAll(stateText, stateBar);
        stateBox.setSpacing(5);
        stateBox.setAlignment(Pos.CENTER);

        countryBox.getChildren().addAll(countryText, countryBar);
        countryBox.setSpacing(5);
        countryBox.setAlignment(Pos.CENTER);

        zipBox.getChildren().addAll(zipText, zipBar);
        zipBox.setSpacing(5);
        zipBox.setAlignment(Pos.CENTER);

        addressPhoneBox.getChildren().addAll(addressPhoneText, addressPhoneBar);
        addressPhoneBox.setSpacing(5);
        addressPhoneBox.setAlignment(Pos.CENTER);


        VBox pane = (VBox) dialogPane;

        VBox personalInfo = new VBox(firstnameBox, lastnameBox, emailBox, phoneBox, passwordBox);
        VBox cardInfo     = new VBox(cardNumBox, typeBox, nameBox, expireBox, securityBox);
        VBox addressInfo    = new VBox(nicknameBox, street1Box, street2Box, cityBox, stateBox, countryBox, zipBox, addressPhoneBox);

        HBox infoPane = new HBox(personalInfo, cardInfo, addressInfo);
        for(Node node : infoPane.getChildren()){
            VBox box = (VBox) node;
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
        }
        infoPane.setSpacing(15);
        infoPane.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(infoPane, registerButton);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene dialogScene = new Scene(pane);
        setScene(dialogScene);
    }

    @Override
    public void setHandlers() {
        registerButton.setOnAction(e -> {

            String[] personalInfo = {firstnameBar.getText(), lastnameBar.getText(), emailBar.getText(), phoneBar.getText(), passwordBar.getText()};
            String[] cardInfo = {cardNumBar.getText(), typeBar.getText(), nameBar.getText(), expireBar.getText(), securityBar.getText()};
            String[] addressInfo = {nicknameBar.getText(), street1Bar.getText(), street2Bar.getText(), cityBar.getText(),
                    stateBar.getText(), countryBar.getText(), zipBar.getText(), addressPhoneBar.getText()};

            processor.handleCustomerReg(personalInfo, cardInfo, addressInfo);

            clear();
            this.close();
        });
    }

    @Override
    public void clear() {
        firstnameBar.clear();
        lastnameBar.clear();
        emailBar.clear();
        phoneBar.clear();
        passwordBar.clear();

        cardNumBar.clear();
        typeBar.clear();
        nameBar.clear();
        expireBar.clear();
        securityBar.clear();

        nicknameBar.clear();
        street1Bar.clear();
        street2Bar.clear();
        cityBar.clear();
        stateBar.clear();
        countryBar.clear();
        zipBar.clear();
        addressPhoneBar.clear();
    }
}
