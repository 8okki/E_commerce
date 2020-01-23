package Dialog;

import Processor.*;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Dialog extends Stage {

    protected Pane dialogPane;
    protected AppProcessor processor;

    public abstract void initialize(Stage owner, AppProcessor processor);

    public abstract void initComponent();

    public abstract void layout();

    public abstract void setHandlers();

    public abstract void clear();
}
