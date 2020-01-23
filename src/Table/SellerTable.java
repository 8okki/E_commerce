package Table;

import Elements.Seller;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class SellerTable extends MainTable{

    TableColumn<Seller, String>       idCol;
    TableColumn<Seller, String>       nameCol;
    TableColumn<Seller, String>       emailCol;
    TableColumn<Seller, String>       phoneCol;
    TableColumn<Seller, String>       websiteCol;

    public SellerTable(ObservableList<Seller> sellers) {
        super(sellers);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Seller ID");
        nameCol = new TableColumn("Business Name");
        emailCol = new TableColumn("Email");
        phoneCol = new TableColumn("Phone Number");
        websiteCol = new TableColumn("Website");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(50);
        nameCol.setMinWidth(200);
        emailCol.setMinWidth(150);
        phoneCol.setMinWidth(150);
        websiteCol.setMinWidth(100);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        websiteCol.setCellValueFactory(new PropertyValueFactory<>("website"));

        table.getColumns().addAll(idCol, nameCol, emailCol, phoneCol, websiteCol);
    }
}
