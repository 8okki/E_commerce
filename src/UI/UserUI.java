package UI;

import Processor.UserProcessor;
import Table.MainTable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;



public abstract class UserUI implements UI{

    protected AppUI             mainUI;
    protected UserProcessor     processor;

    protected Stage             primaryStage;
    protected ToolBar           leftToolBar;
    protected BorderPane        rightToolBar;

    protected MainTable         table;


    public UserUI(AppUI mainUI){
        this.mainUI = mainUI;
        this.primaryStage = mainUI.getPrimaryStage();
        initialize();
        layout();
        setHandlers();
    }

    public UserProcessor getProcessor() { return processor; }

    public ToolBar getLeftToolBar() { return leftToolBar; }

    public BorderPane getRightToolBar() { return rightToolBar; }

    public MainTable getTable() { return table; }

    public abstract void initLeftToolBar();

    public abstract void initRightToolBar();

    public abstract void layoutLeftToolBar();

    public abstract void layoutRightToolBar();
}
