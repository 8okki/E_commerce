package Table;

import Elements.Employee;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeeTable extends MainTable {

    TableColumn<Employee, String>       idCol;
    TableColumn<Employee, String>       firstCol;
    TableColumn<Employee, String>       lastCol;
    TableColumn<Employee, String>       emailCol;
    TableColumn<Employee, String>       designationCol;
    TableColumn<Employee, String>       departmentCol;
    TableColumn<Employee, String>       categoryCol;


    public EmployeeTable(ObservableList<Employee> employees) {
        super(employees);
    }

    @Override
    void initColumns() {
        idCol = new TableColumn("Employee ID");
        firstCol = new TableColumn("First Name");
        lastCol = new TableColumn("Last Name");
        emailCol = new TableColumn("Email");
        designationCol = new TableColumn("Designation");
        departmentCol = new TableColumn("Department");
        categoryCol = new TableColumn<>("Category");
    }

    @Override
    void layoutColumns() {
        idCol.setMinWidth(70);
        firstCol.setMinWidth(100);
        lastCol.setMinWidth(100);
        emailCol.setMinWidth(150);
        designationCol.setMinWidth(125);
        departmentCol.setMinWidth(125);
        categoryCol.setMinWidth(125);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("first"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("last"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        table.getColumns().addAll(idCol, firstCol, lastCol, emailCol, designationCol, departmentCol, categoryCol);
    }
}
