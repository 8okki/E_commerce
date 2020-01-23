package Dialog;

import Processor.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SellerRegDialog extends Dialog {

    private static SellerRegDialog    sellerRegDialog;

    private Text        businessNameText;
    private TextField   businessNameBar;
    private HBox        businessNameBox;
    private Text        emailText;
    private TextField   emailBar;
    private HBox        emailBox;
    private Text        phoneText;
    private TextField   phoneBar;
    private HBox        phoneBox;
    private Text        websiteLinkText;
    private TextField   websiteLinkBar;
    private HBox        websiteLinkBox;
    private Text        passwordText;
    private TextField   passwordBar;
    private HBox        passwordBox;

    private Text        bankNumberText;
    private TextField   bankNumberBar;
    private HBox        bankNumberBox;
    private Text        accHolderNameText;
    private TextField   accHolderNameBar;
    private HBox        accHolderNameBox;
    private Text        routingNumText;
    private TextField   routingNumBar;
    private HBox        routingNumBox;

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

    private Button registerButton;

    private SellerRegDialog() { }

    public static SellerRegDialog getSellerRegDialog(){
        if(sellerRegDialog == null)
            sellerRegDialog = new SellerRegDialog();

        return sellerRegDialog;
    }

    @Override
    public void initialize(Stage owner, AppProcessor processor) {

        this.processor = processor;

        // Initialize
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Creating a Seller Account");
        initComponent();

        // Layout
        layout();

        // Set Handler
        setHandlers();
    }

    @Override
    public void initComponent() {

        dialogPane          = new VBox();

        businessNameText    = new Text("Business Name:");
        businessNameBar     = new TextField();
        businessNameBox     = new HBox();
        emailText           = new Text("Email:");
        emailBar            = new TextField();
        emailBox            = new HBox();
        phoneText           = new Text("Phone Number:");
        phoneBar            = new TextField();
        phoneBox            = new HBox();
        websiteLinkText     = new Text("Website Link:");
        websiteLinkBar      = new TextField();
        websiteLinkBox      = new HBox();
        passwordText        = new Text("Password:");
        passwordBar         = new TextField();
        passwordBox         = new HBox();

        bankNumberText      = new Text("Bank Account Number:");
        bankNumberBar       = new TextField();
        bankNumberBox       = new HBox();
        accHolderNameText   = new Text("AccountHolder Name:");
        accHolderNameBar    = new TextField();
        accHolderNameBox    = new HBox();
        routingNumText      = new Text("Routing Number:");
        routingNumBar       = new TextField();
        routingNumBox       = new HBox();

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

        registerButton      = new Button("Create Account");
    }

    @Override
    public void layout(){
        businessNameBox.getChildren().addAll(businessNameText, businessNameBar);
        businessNameBox.setSpacing(5);
        businessNameBox.setAlignment(Pos.CENTER);

        emailBox.getChildren().addAll(emailText, emailBar);
        emailBox.setSpacing(5);
        emailBox.setAlignment(Pos.CENTER);

        phoneBox.getChildren().addAll(phoneText, phoneBar);
        phoneBox.setSpacing(5);
        phoneBox.setAlignment(Pos.CENTER);

        websiteLinkBox.getChildren().addAll(websiteLinkText, websiteLinkBar);
        websiteLinkBox.setSpacing(5);
        websiteLinkBox.setAlignment(Pos.CENTER);

        passwordBox.getChildren().addAll(passwordText, passwordBar);
        passwordBox.setSpacing(5);
        passwordBox.setAlignment(Pos.CENTER);

        bankNumberBox.getChildren().addAll(bankNumberText, bankNumberBar);
        bankNumberBox.setSpacing(5);
        bankNumberBox.setAlignment(Pos.CENTER);

        accHolderNameBox.getChildren().addAll(accHolderNameText, accHolderNameBar);
        accHolderNameBox.setSpacing(5);
        accHolderNameBox.setAlignment(Pos.CENTER);

        routingNumBox.getChildren().addAll(routingNumText, routingNumBar);
        routingNumBox.setSpacing(5);
        routingNumBox.setAlignment(Pos.CENTER);

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

        VBox businessInfo   = new VBox(businessNameBox, emailBox, phoneBox, websiteLinkBox, passwordBox);
        VBox bankInfo       = new VBox(bankNumberBox, accHolderNameBox, routingNumBox);
        VBox addressInfo    = new VBox(nicknameBox, street1Box, street2Box, cityBox, stateBox, countryBox, zipBox, addressPhoneBox);

        HBox infoPane = new HBox(businessInfo, bankInfo, addressInfo);
        for(Node node : infoPane.getChildren()){
            VBox box = (VBox) node;
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
        }
        infoPane.setSpacing(15);
        infoPane.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(infoPane, registerButton);
        pane.setSpacing(30);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene dialogScene = new Scene(pane);
        setScene(dialogScene);
    }

    @Override
    public void setHandlers(){
        registerButton.setOnAction(e -> {

            String[] businessInfo = {businessNameBar.getText(), phoneBar.getText(), emailBar.getText(), websiteLinkBar.getText(), passwordBar.getText()};
            String[] bankInfo = {bankNumberBar.getText(), accHolderNameBar.getText(), routingNumBar.getText()};
            String[] addressInfo = {nicknameBar.getText(), street1Bar.getText(), street2Bar.getText(), cityBar.getText(),
                                    stateBar.getText(), countryBar.getText(), zipBar.getText(), addressPhoneBar.getText()};

            processor.handleSellerReg(businessInfo, bankInfo, addressInfo);

            clear();
            this.close();
        });
    }

    @Override
    public void clear() {
        businessNameBar.clear();
        emailBar.clear();
        phoneBar.clear();
        websiteLinkBar.clear();
        passwordBar.clear();

        bankNumberBar.clear();
        accHolderNameBar.clear();
        routingNumBar.clear();

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
