package Application;


import UI.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Random;


public class Amason extends Application {

    private AppUI ui;
    public static Random random = new Random();

    public AppUI getUi() { return ui; }

    @Override
    public void start(Stage primaryStage) {
        ui = new AppUI(primaryStage);
    }
}
