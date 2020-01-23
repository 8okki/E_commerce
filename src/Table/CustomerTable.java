package Table;

import Elements.Customer;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerTable extends MainTable {

    TableColumn<Customer, String>       idCol;
    TableColumn<Customer, String>       firstCol;
    TableColumn<Customer, String>       lastCol;
    TableColumn<Customer, String>       emailCol;
    TableColumn<Customer, String>       phoneCol;

    public CustomerTable(ObservableList<Customer> customers) {
        super(customers);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Customer ID");
        firstCol = new TableColumn("First Name");
        lastCol = new TableColumn("Last Name");
        emailCol = new TableColumn("Email");
        phoneCol = new TableColumn("Phone Number");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(75);
        firstCol.setMinWidth(150);
        lastCol.setMinWidth(150);
        emailCol.setMinWidth(150);
        phoneCol.setMinWidth(125);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("first"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("last"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        table.getColumns().addAll(idCol, firstCol, lastCol, emailCol, phoneCol);
    }
}
