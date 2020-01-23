package UI;


import Elements.Item;
import Processor.*;
import Dialog.*;
import Table.ItemTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AppUI implements UI {

    // Major Components
    private UserUI          currentUI;
    private CustomerUI      customerUI;
    private SellerUI        sellerUI;
    private EmployeeUI      employeeUI;
    private AppProcessor    processor;

    // Main UI Frame
    private Stage           primaryStage;
    private Scene           primaryScene;
    private BorderPane      primaryPane;
    private int             windowWidth;
    private int             windowHeight;
    private String          applicationTitle;

    // Top UI
    private BorderPane      topToolBar;
    private ToolBar         leftToolBar;
    private BorderPane      rightToolBar;
    private Pane            workspace;

    private ImageView       logoImage;
    private String          logoImagePath;

    private HBox            searchBox;
    private Text            searchText;
    private TextField       searchBar;
    private Button          searchButton;

    private VBox            logAndRegBox;
    private Button          logInButton;
    private Button          registerButton;

    private VBox            accountBox;
    private Text            accountText;
    private Button          logOutButton;

    // Right UI
    private Text            itemNameText;
    private Text            sellerNameText;
    private Text            unitpriceText;
    private VBox            itemInfoBox;
    private Text            descriptionText;
    private VBox            descriptBox;

    // Workspace UI
    private ItemTable       itemTable;
    private ObservableList<Item> items;

    // Dialogs
    private final LogInDialog logInDialog                 = LogInDialog.getLogInDialog();
    private final RegistrationDialog registrationDialog   = RegistrationDialog.getRegistrationDialog();


    public AppUI(Stage primaryStage){
        this.primaryStage = primaryStage;
        processor = new AppProcessor(this);
        initDialogs(primaryStage);

        customerUI      = new CustomerUI(this);
        sellerUI        = new SellerUI(this);
        employeeUI      = new EmployeeUI(this);

        initialize();
        layout();
        setHandlers();

        displayItems("");
        this.primaryStage.show();
    }

    public Stage getPrimaryStage() { return primaryStage; }

    public ItemTable getTable() { return itemTable; }

    @Override
    public void initialize() {
        windowWidth = 1300;
        windowHeight = 750;
        applicationTitle = "Amason";

        initTopToolBar();
        initLeftToolBar();
        initRightToolBar();
        initWorkspace();

        primaryPane = new BorderPane();
        primaryScene = new Scene(primaryPane, windowWidth, windowHeight);
    }

    public void initDialogs(Stage primaryStage){
        logInDialog.initialize(primaryStage, processor);
        registrationDialog.initialize(primaryStage, processor);
    }

    public void initTopToolBar(){
        logoImagePath = "amason.jpeg";
        logoImage = new ImageView();

        searchBox = new HBox();
        searchText = new Text("Search:");
        searchBar = new TextField();
        searchButton = new Button("Search");

        logAndRegBox = new VBox();
        logInButton = new Button("Log In");
        registerButton = new Button("Create an Account");

        accountText     = new Text("");
        logOutButton    = new Button("Log Out");
        accountBox      = new VBox();

        topToolBar = new BorderPane();
    }

    public void initLeftToolBar(){
        leftToolBar = new ToolBar();
    }

    public void initRightToolBar(){
        rightToolBar = new BorderPane();

        itemNameText        = new Text();
        sellerNameText      = new Text();
        unitpriceText       = new Text();
        itemInfoBox         = new VBox();
        descriptionText     = new Text();
        descriptBox         = new VBox();
    }

    public void initWorkspace(){
        items = FXCollections.observableArrayList();
        itemTable = new ItemTable(items);
        workspace = new VBox(itemTable.getTable());
    }

    @Override
    public void layout() {

        layoutTopToolBar();

        layoutLeftToolBar();

        layoutRightToolBar();

        layoutWorkspace();

        primaryStage.setTitle(applicationTitle);
        primaryStage.setScene(primaryScene);
    }

    public void layoutTopToolBar(){
        logoImage.setImage(new Image(logoImagePath));
        logoImage.setFitHeight(70);
        logoImage.setFitWidth(150);

        searchBar.setPrefSize(700, 30);
        searchBox.getChildren().addAll(searchText, searchBar, searchButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(8,10,8,50));

        logAndRegBox.getChildren().addAll(logInButton, registerButton);
        logAndRegBox.setAlignment(Pos.CENTER_RIGHT);
        logAndRegBox.setSpacing(7);

        accountBox.getChildren().addAll(accountText, logOutButton);
        accountBox.setAlignment(Pos.CENTER_RIGHT);
        accountBox.setSpacing(7);

        topToolBar.setLeft(logoImage);
        topToolBar.setCenter(searchBox);
        topToolBar.setRight(logAndRegBox);
        topToolBar.setStyle("-fx-background-color: coral;");
        topToolBar.setPadding(new Insets(8,10,8,10));

        primaryPane.setTop(topToolBar);
    }

    public void layoutLeftToolBar(){
        leftToolBar.setOrientation(Orientation.VERTICAL);
        leftToolBar.setPrefWidth(175);
        leftToolBar.setPadding(new Insets(8,10,8,10));
        leftToolBar.setStyle("-fx-background-color: lightgray;");
        primaryPane.setLeft(leftToolBar);
    }

    public void layoutRightToolBar(){
        itemNameText.setFont(Font.font(30));
        sellerNameText.setFont(Font.font(15));
        unitpriceText.setFont(Font.font(20));
        itemInfoBox.setSpacing(5);
        itemInfoBox.setAlignment(Pos.TOP_LEFT);
        itemInfoBox.setPadding(new Insets(8));
        itemInfoBox.getChildren().addAll(itemNameText, sellerNameText, unitpriceText);

        descriptionText.setFont(Font.font(15));
        descriptBox.setSpacing(10);
        descriptBox.setAlignment(Pos.CENTER_LEFT);
        descriptBox.setPadding(new Insets(8));
        descriptBox.getChildren().addAll(descriptionText);

        rightToolBar.setPrefWidth(400);
        rightToolBar.setPadding(new Insets(5,5,5,5));
        rightToolBar.setStyle("-fx-background-color: lightgray;");
        primaryPane.setRight(rightToolBar);
    }

    public void layoutWorkspace(){
        primaryPane.setCenter(workspace);
    }

    @Override
    public void setHandlers() {
        searchButton.setOnAction(e -> displayItems(searchBar.getText()));

        logInButton.setOnAction(e -> logInDialog.showAndWait());

        registerButton.setOnAction(e -> registrationDialog.showAndWait());

        logOutButton.setOnAction(e -> {
            clear();
            currentUI = employeeUI;
            topToolBar.setRight(logAndRegBox);
            workspace.getChildren().add(itemTable.getTable());
        });

        itemTable.getTable().setOnMouseClicked(e -> {
            Item item = (Item) itemTable.getTable().getSelectionModel().getSelectedItem();
            if(item != null) {
                itemNameText.setText(item.getName());
                sellerNameText.setText(item.getSeller());
                unitpriceText.setText("$ " + item.getPrice());
                descriptionText.setText(item.getDescription());

                rightToolBar.setTop(itemInfoBox);
                rightToolBar.setCenter(descriptBox);
                if(currentUI instanceof CustomerUI)
                    rightToolBar.setBottom(((CustomerUI)currentUI).getToCartInfoBox());
                else
                    rightToolBar.setBottom(null);
            }
        });
    }

    @Override
    public void clear() {
        leftToolBar.getItems().clear();
        rightToolBar.getChildren().clear();
        workspace.getChildren().clear();
    }

    public void customerMode(String id, String name){
        currentUI = customerUI;
        currentUI.getProcessor().setCurrentID(id);
        accountText.setText("Hello, " + name);
        rightToolBar.setPrefWidth(400);
    }

    public void sellerMode(String id, String name){
        currentUI = sellerUI;
        currentUI.getProcessor().setCurrentID(id);
        accountText.setText("Hello, " + name);
        rightToolBar.setPrefWidth(400);
    }

    public void employeeMode(String id, String name){
        currentUI = employeeUI;
        currentUI.getProcessor().setCurrentID(id);
        accountText.setText("Hello, " + name);
        rightToolBar.setPrefWidth(300);
    }

    public void refreshUI(){
        clear();
        topToolBar.setRight(accountBox);
        leftToolBar.getItems().addAll(currentUI.getLeftToolBar().getItems());
        rightToolBar.setTop(currentUI.getRightToolBar().getTop());
        rightToolBar.setCenter(currentUI.getRightToolBar().getCenter());
        rightToolBar.setBottom(currentUI.getRightToolBar().getBottom());
        workspace.getChildren().add(currentUI.getTable().getTable());
    }

    public void displayItems(String pattern) {
        items.setAll(processor.searchItems(pattern));
        workspace.getChildren().clear();
        workspace.getChildren().add(itemTable.getTable());
    }
}