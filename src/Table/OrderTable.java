package Table;

import Elements.Order;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class OrderTable extends MainTable {

    TableColumn<Order, Integer>      idCol;
    TableColumn<Order, String>       dateCol;
    TableColumn<Order, String>       cardCol;

    public OrderTable(ObservableList<Order> orders){
        super(orders);
        table.setPrefSize(300, 720);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Order ID");
        dateCol = new TableColumn("Order Date");
        cardCol = new TableColumn("Paid With");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(100);
        dateCol.setMinWidth(150);
        cardCol.setMinWidth(100);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        cardCol.setCellValueFactory(new PropertyValueFactory<>("cardNum"));

        table.getColumns().addAll(idCol, dateCol, cardCol);
    }
}
