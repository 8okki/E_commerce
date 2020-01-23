package Processor;

import Elements.*;
import UI.EmployeeUI;
import javafx.collections.ObservableList;

public class EmployeeProcessor extends UserProcessor {

    private EmployeeUI              ui;

    public EmployeeProcessor(EmployeeUI ui) {
        this.ui = ui;
    }

    public ObservableList<Item> handleItems(){
        try {
            return executor.execGetItems();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Customer> handleCustomers(){
        try {
            return executor.execGetCustomers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Seller> handleSellers(){
        try {
            return executor.execGetSellers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public ObservableList<Employee> handleEmployees(){
        try {
            return executor.execGetEmployees();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
