package Dialog;

import Elements.Category;
import Elements.Department;
import Processor.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmployeeRegDialog extends Dialog {

    private static EmployeeRegDialog employeeRegDialog;

    private Text        firstnameText;
    private TextField   firstnameBar;
    private HBox        firstnameBox;
    private Text        lastnameText;
    private TextField   lastnameBar;
    private HBox        lastnameBox;
    private Text        emailText;
    private TextField   emailBar;
    private HBox        emailBox;
    private Text        passwordText;
    private TextField   passwordBar;
    private HBox        passwordBox;

    private MenuBar     departmentBar;
    private Menu        departmentMenu;
    private Label       chosenDepartment;
    private HBox        departmentBox;
    private MenuBar     designationBar;
    private Menu        designationMenu;
    private Label       chosenDesignation;
    private HBox        designationBox;

    private MenuBar     categoryBar;
    private Menu        categoryMenu;
    private Label       chosenCategory;
    private HBox        categoryBox;

    private Button      registerButton;


    private EmployeeRegDialog() {}

    public static EmployeeRegDialog getEmployeeRegDialog() {
        if(employeeRegDialog == null)
            employeeRegDialog = new EmployeeRegDialog();

        return employeeRegDialog;
    }

    @Override
    public void initialize(Stage owner, AppProcessor processor) {

        this.processor = processor;

        // Initialize
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Creating an Employee Account");
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
        passwordText    = new Text("Password:");
        passwordBar     = new TextField();
        passwordBox     = new HBox();
        registerButton  = new Button("Create Account");

        departmentMenu  = new Menu("Department");
        departmentMenu.getItems().addAll(
                new MenuItem("Electronics"),
                new MenuItem("Clothing"),
                new MenuItem("Furniture"),
                new MenuItem("Beauty"));
        departmentBar   = new MenuBar(departmentMenu);
        chosenDepartment= new Label();
        departmentBox   = new HBox();

        designationMenu = new Menu("Designation");
        designationMenu.getItems().addAll(new MenuItem("Department Manager"), new MenuItem("Category Manager"));
        designationBar  = new MenuBar(designationMenu);
        chosenDesignation = new Label();
        designationBox  = new HBox();
        categoryMenu = new Menu("Category");
        categoryBar = new MenuBar(categoryMenu);
        chosenCategory = new Label();
        categoryBox = new HBox();

        registerButton  = new Button("Create Account");
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

        passwordBox.getChildren().addAll(passwordText, passwordBar);
        passwordBox.setSpacing(5);
        passwordBox.setAlignment(Pos.CENTER);

        departmentBox.getChildren().addAll(departmentBar, chosenDepartment);
        departmentBox.setSpacing(5);
        departmentBox.setAlignment(Pos.CENTER);

        designationBox.getChildren().addAll(designationBar, chosenDesignation);
        designationBox.setSpacing(5);
        designationBox.setAlignment(Pos.CENTER);

        categoryBox.getChildren().addAll(categoryBar, chosenCategory);
        categoryBox.setSpacing(5);
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setVisible(false);

        VBox pane = (VBox) dialogPane;

        VBox personalInfo   = new VBox(firstnameBox, lastnameBox, emailBox, passwordBox);
        personalInfo.setAlignment(Pos.CENTER);
        personalInfo.setSpacing(10);
        VBox workInfo       = new VBox(departmentBox, designationBox, categoryBox);
        workInfo.setAlignment(Pos.CENTER);
        workInfo.setSpacing(10);
        pane.getChildren().addAll(personalInfo, workInfo, registerButton);
        pane.setSpacing(30);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene dialogScene = new Scene(pane);
        setScene(dialogScene);
    }

    @Override
    public void setHandlers() {

        for(MenuItem department : departmentMenu.getItems()) {
            department.setOnAction(e -> {
                chosenDepartment.setText(department.getText());
                Department dept = processor.handleDepartment(chosenDepartment.getText());
                categoryMenu.getItems().clear();
                for(Category category : processor.handleCategory(dept.getId())){
                    MenuItem categoryName = new MenuItem(category.getName());
                    categoryName.setOnAction(f -> chosenCategory.setText(categoryName.getText()));
                    categoryMenu.getItems().add(categoryName);
                }
                chosenCategory.setText("");
            });
        }

        for(MenuItem designation : designationMenu.getItems()) {
            String job = designation.getText();
            designation.setOnAction(e -> chosenDesignation.setText(job));
            categoryBox.setVisible(job.equals("Category Manager"));
        }

        registerButton.setOnAction(e -> {

            String firstname    = firstnameBar.getText();
            String lastname     = lastnameBar.getText();
            String email        = emailBar.getText();
            String password     = passwordBar.getText();
            String department   = chosenDepartment.getText();
            String designation  = chosenDesignation.getText();
            String category     = chosenCategory.getText();

            processor.handleEmployeeReg(firstname, lastname, email, password, department, designation, category);

            clear();
            this.close();
        });
    }

    @Override
    public void clear() {
        firstnameBar.clear();
        lastnameBar.clear();
        emailBar.clear();
        passwordBar.clear();
        categoryMenu.getItems().clear();
    }
}
