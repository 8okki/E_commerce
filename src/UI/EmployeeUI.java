package UI;

import Processor.EmployeeProcessor;
import Table.CustomerTable;
import Table.*;
import Elements.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class EmployeeUI extends UserUI {

    // Left ToolBar Components
    private Button      itemsButton;
    private ImageView   itemsImage;
    private String      itemsImagePath;
    private Text        itemsText;
    private VBox        itemsBox;

    private Button      customersButton;
    private ImageView   customersImage;
    private String      customersImagePath;
    private Text        customersText;
    private VBox        customersBox;

    private Button      sellersButton;
    private ImageView   sellersImage;
    private String      sellersImagePath;
    private Text        sellersText;
    private VBox        sellersBox;

    private Button      employeesButton;
    private ImageView   employeesImage;
    private String      employeesImagePath;
    private Text        employeesText;
    private VBox        employeesBox;

    // Workspace - Tables
    private ItemTable           itemTable;
    private CustomerTable       customerTable;
    private SellerTable         sellerTable;
    private EmployeeTable       employeeTable;

    // Workspace - Lists
    private ObservableList<Item>        items;
    private ObservableList<Customer>    customers;
    private ObservableList<Seller>      sellers;
    private ObservableList<Employee>    employees;

    public EmployeeUI(AppUI mainUI) {
        super(mainUI);
        processor = new EmployeeProcessor(this);
    }

    @Override
    public void initialize() {
        initLeftToolBar();
        initRightToolBar();
        initWorkspace();
    }

    @Override
    public void initLeftToolBar(){
        itemsImagePath        = "items.png";
        itemsText             = new Text("View Items");
        itemsImage            = new ImageView();
        itemsButton           = new Button();
        itemsBox              = new VBox();

        customersImagePath    = "customers.png";
        customersText         = new Text("View Customer");
        customersImage        = new ImageView();
        customersButton       = new Button();
        customersBox          = new VBox();

        sellersImagePath      = "sellers.png";
        sellersText           = new Text("View Sellers");
        sellersImage          = new ImageView();
        sellersButton         = new Button();
        sellersBox            = new VBox();

        employeesImagePath    = "employees.png";
        employeesText         = new Text("View Employees");
        employeesImage        = new ImageView();
        employeesButton       = new Button();
        employeesBox          = new VBox();

        leftToolBar           = new ToolBar();
    }

    @Override
    public void initRightToolBar() {
        rightToolBar = new BorderPane();
    }

    public void initWorkspace() {
        items = FXCollections.observableArrayList();
        customers = FXCollections.observableArrayList();
        sellers = FXCollections.observableArrayList();
        employees = FXCollections.observableArrayList();
        itemTable = new ItemTable(items);
        customerTable = new CustomerTable(customers);
        sellerTable = new SellerTable(sellers);
        employeeTable = new EmployeeTable(employees);
        table = itemTable;
    }

    @Override
    public void layout() {
        layoutLeftToolBar();
        layoutRightToolBar();
    }

    @Override
    public void layoutLeftToolBar(){
        itemsImage.setImage(new Image(itemsImagePath));
        itemsImage.setFitHeight(50);
        itemsImage.setFitWidth(50);
        itemsButton.setGraphic(itemsImage);
        itemsBox.getChildren().addAll(itemsButton, itemsText);
        itemsBox.setAlignment(Pos.CENTER);
        itemsBox.setSpacing(3);
        itemsBox.setPadding(new Insets(30));

        customersImage.setImage(new Image(customersImagePath));
        customersImage.setFitHeight(50);
        customersImage.setFitWidth(50);
        customersButton.setGraphic(customersImage);
        customersBox.getChildren().addAll(customersButton, customersText);
        customersBox.setAlignment(Pos.CENTER);
        customersBox.setSpacing(3);
        customersBox.setPadding(new Insets(30));

        sellersImage.setImage(new Image(sellersImagePath));
        sellersImage.setFitHeight(50);
        sellersImage.setFitWidth(50);
        sellersButton.setGraphic(sellersImage);
        sellersBox.getChildren().addAll(sellersButton, sellersText);
        sellersBox.setAlignment(Pos.CENTER);
        sellersBox.setSpacing(3);
        sellersBox.setPadding(new Insets(30));

        employeesImage.setImage(new Image(employeesImagePath));
        employeesImage.setFitHeight(50);
        employeesImage.setFitWidth(50);
        employeesButton.setGraphic(employeesImage);
        employeesBox.getChildren().addAll(employeesButton, employeesText);
        employeesBox.setAlignment(Pos.CENTER);
        employeesBox.setSpacing(3);
        employeesBox.setPadding(new Insets(30));

        leftToolBar.setOrientation(Orientation.VERTICAL);
        leftToolBar.setPrefWidth(175);
        leftToolBar.setPadding(new Insets(8,10,8,10));
        leftToolBar.getItems().addAll(itemsBox, sellersBox, customersBox, employeesBox);
    }

    @Override
    public void layoutRightToolBar() {

    }

    @Override
    public void setHandlers() {
        itemsButton.setOnAction(e -> showItems());
        customersButton.setOnAction(e -> showCustomers());
        sellersButton.setOnAction(e -> showSellers());
        employeesButton.setOnAction(e -> showEmployees());
    }

    public void showItems() {
        ObservableList<Item> items = ((EmployeeProcessor)processor).handleItems();
        if(items != null)
            this.items.setAll(items);
        table = itemTable;
        mainUI.refreshUI();
    }

    public void showCustomers() {
        ObservableList<Customer> customers = ((EmployeeProcessor)processor).handleCustomers();
        if(customers != null)
            this.customers.setAll(customers);
        table = customerTable;
        mainUI.refreshUI();
    }

    public void showSellers() {
        ObservableList<Seller> sellers = ((EmployeeProcessor)processor).handleSellers();
        if(sellers != null)
            this.sellers.setAll(sellers);
        table = sellerTable;
        mainUI.refreshUI();
    }

    public void showEmployees() {
        ObservableList<Employee> employees = ((EmployeeProcessor)processor).handleEmployees();
        if(employees != null)
            this.employees.setAll(employees);
        table = employeeTable;
        mainUI.refreshUI();
    }

    @Override
    public void clear() {

    }
}
