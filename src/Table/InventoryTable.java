package Table;

import Elements.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class InventoryTable extends MainTable {

    TableColumn<Item, Integer>      idCol;
    TableColumn<Item, String>       nameCol;
    TableColumn<Item, Double>       priceCol;
    TableColumn<Item, Integer>      qtyCol;
    TableColumn<Item, String>       categoryCol;
    TableColumn<Item, String>       descriptCol;


    public InventoryTable(ObservableList<Item> items){
        super(items);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Item ID");
        nameCol = new TableColumn("Item Name");
        priceCol = new TableColumn("Unit Price");
        qtyCol = new TableColumn("Quantities");
        categoryCol = new TableColumn<>("Category");
        descriptCol = new TableColumn("Description");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(50);
        nameCol.setMinWidth(150);
        priceCol.setMinWidth(50);
        qtyCol.setMinWidth(50);
        categoryCol.setMinWidth(150);
        descriptCol.setMinWidth(200);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        table.getColumns().addAll(idCol, nameCol, priceCol, qtyCol, categoryCol, descriptCol);
    }
}
