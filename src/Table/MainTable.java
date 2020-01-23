package Table;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public abstract class MainTable {

    TableView               table;

    public MainTable(ObservableList elements){
        table = new TableView();
        table.setItems(elements);
        table.setEditable(true);
        table.setPrefSize(500, 720);
        initColumns();
        layoutColumns();
    }

    public TableView getTable() { return table; }

    abstract void initColumns();
    abstract void layoutColumns();

}
