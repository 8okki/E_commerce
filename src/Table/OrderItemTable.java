package Table;

import Elements.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderItemTable {

    TableView<Item>     table;

    TableColumn<Item, String>       nameCol;
    TableColumn<Item, String>       sellerCol;
    TableColumn<Item, Double>       priceCol;
    TableColumn<Item, Integer>      qtyCol;
    TableColumn<Item, Double>       subtotalCol;
    TableColumn<Item, String>       trackIDCol;

    public TableView<Item> getTable() { return table; }

    public OrderItemTable(ObservableList<Item> items){
        table = new TableView();
        table.setItems(items);
        table.setEditable(true);
        table.setPrefSize(500, 300);
        initColumns();
        layoutColumns();
    }

    void initColumns() {
        nameCol = new TableColumn("Item Name");
        sellerCol = new TableColumn("Seller");
        priceCol = new TableColumn("Unit Price");
        qtyCol = new TableColumn("Quantities");
        subtotalCol = new TableColumn<>("Subtotal");
        trackIDCol = new TableColumn<>("Tracking ID");
    }

    void layoutColumns() {
        nameCol.setMinWidth(50);
        sellerCol.setMinWidth(100);
        priceCol.setMinWidth(50);
        qtyCol.setMinWidth(50);
        subtotalCol.setMinWidth(75);
        trackIDCol.setMinWidth(75);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellerCol.setCellValueFactory(new PropertyValueFactory<>("seller"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        subtotalCol.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        trackIDCol.setCellValueFactory(new PropertyValueFactory<>("trackID"));

        table.getColumns().addAll(nameCol, sellerCol, priceCol, qtyCol, subtotalCol, trackIDCol);
    }

}
