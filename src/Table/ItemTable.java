package Table;

import Elements.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ItemTable extends MainTable {

    TableColumn<Item, Integer>      idCol;
    TableColumn<Item, String>       nameCol;
    TableColumn<Item, String>       sellerCol;
    TableColumn<Item, String>       categoryCol;
    TableColumn<Item, Double>       priceCol;
    TableColumn<Item, Integer>      qtyCol;

    public ItemTable(ObservableList<Item> items) {
        super(items);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Item ID");
        nameCol = new TableColumn("Item Name");
        sellerCol = new TableColumn("Seller");
        categoryCol = new TableColumn<>("Category");
        priceCol = new TableColumn("Unit Price");
        qtyCol = new TableColumn("Quantities");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(70);
        nameCol.setMinWidth(200);
        sellerCol.setMinWidth(100);
        categoryCol.setMinWidth(150);
        priceCol.setMinWidth(100);
        qtyCol.setMinWidth(100);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellerCol.setCellValueFactory(new PropertyValueFactory<>("seller"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));

        table.getColumns().addAll(idCol, nameCol, sellerCol, categoryCol, priceCol, qtyCol);
    }
}
