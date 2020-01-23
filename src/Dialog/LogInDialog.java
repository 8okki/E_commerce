package Dialog;

import Processor.*;
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


public class LogInDialog extends Dialog {

    private static LogInDialog logInDialog;

    private Text        emailText;
    private TextField   emailBar;
    private HBox        emailBox;
    private Text        passwordText;
    private TextField   passwordBar;
    private HBox        passwordBox;

    private Button      logInButton;

    private String      errorMessage;


    private LogInDialog() {}

    public static LogInDialog getLogInDialog() {
        if (logInDialog == null)
            logInDialog = new LogInDialog();

        return logInDialog;
    }

    @Override
    public void initialize(Stage owner, AppProcessor processor) {

        this.processor = processor;

        // Initialize
        initModality(Modality.WINDOW_MODAL);
        initOwner(owner);
        setTitle("Log In");
        initComponent();

        // Layout
        layout();

        // Set Handlers
        setHandlers();
    }

    @Override
    public void initComponent() {
        dialogPane      = new VBox();
        errorMessage    = "Incorrect Email or Password. Please Try Again";
        emailBox        = new HBox();
        emailText       = new Text("Email:");
        emailBar        = new TextField();
        passwordBox     = new HBox();
        passwordText    = new Text("Password:");
        passwordBar     = new TextField();
        logInButton     = new Button("Log In");
    }

    @Override
    public void layout() {
        emailBox.getChildren().addAll(emailText, emailBar);
        emailBox.setSpacing(5);
        emailBox.setAlignment(Pos.CENTER);

        passwordBox.getChildren().addAll(passwordText, passwordBar);
        passwordBox.setSpacing(5);
        passwordBox.setAlignment(Pos.CENTER);

        VBox pane = (VBox) dialogPane;

        pane.getChildren().addAll(emailBox, passwordBox, logInButton);
        pane.setSpacing(20);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(80, 60, 80, 60));

        Scene dialogScene = new Scene(pane);
        setScene(dialogScene);
    }

    @Override
    public void setHandlers() {
        logInButton.setOnAction(e -> {

            String email    = emailBar.getText();
            String password = passwordBar.getText();

            processor.handleLogIn(email, password);

            clear();
            this.close();
        });
    }

    @Override
    public void clear() {
        emailBar.clear();
        passwordBar.clear();
    }
}
