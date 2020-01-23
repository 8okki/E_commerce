package Table;

import Elements.Sales;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesTable extends MainTable {

    TableColumn<Sales, String>       nameCol;
    TableColumn<Sales, Double>       priceCol;
    TableColumn<Sales, Integer>      qtyCol;
    TableColumn<Sales, Double>       profitCol;
    TableColumn<Sales, Double>       feeCol;
    TableColumn<Sales, Double>       netCol;
    TableColumn<Sales, String>       dateCol;

    public SalesTable(ObservableList<Sales> sales){
        super(sales);
    }

    @Override
    void initColumns() {
        nameCol = new TableColumn("Item Name");
        priceCol = new TableColumn("Unit Price");
        qtyCol = new TableColumn("Quantities");
        profitCol = new TableColumn("Profit");
        feeCol = new TableColumn("Shopping Fee");
        netCol = new TableColumn("Net Profit");
        dateCol = new TableColumn("Date");
    }

    @Override
    void layoutColumns() {
        nameCol.setMinWidth(100);
        priceCol.setMinWidth(50);
        qtyCol.setMinWidth(50);
        profitCol.setMinWidth(100);
        feeCol.setMinWidth(100);
        netCol.setMinWidth(100);
        dateCol.setMinWidth(100);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        profitCol.setCellValueFactory(new PropertyValueFactory<>("profit"));
        feeCol.setCellValueFactory(new PropertyValueFactory<>("fee"));
        netCol.setCellValueFactory(new PropertyValueFactory<>("net"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.getColumns().addAll(nameCol, priceCol, qtyCol, profitCol, feeCol, netCol, dateCol);
    }
}
