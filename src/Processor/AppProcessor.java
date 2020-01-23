package Processor;

import Application.Amason;
import Elements.Category;
import Elements.Department;
import Elements.Item;
import UI.AppUI;
import DB_Executor.Executor;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppProcessor {

    private AppUI               ui;
    private final Executor      executor = Executor.getExecutor();


    public AppProcessor(AppUI ui){
        this.ui = ui;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/E_commerce?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                    "root",
                    "Happytom98"
            );
            executor.initialize(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Item> searchItems(String pattern) {
        try {
            ObservableList items = executor.execSearchItem(pattern);
            return items;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleLogIn(String email, String password){
        try {
            String id = executor.execSearchUser(email, password);
            if(id == null)
                return false;
            else {
                String name;
                if(id.charAt(0) == 'C') {
                    name = executor.execSearchCust(id);
                    ui.customerMode(id, name);
                } else if(id.charAt(0) == 'S') {
                    name = executor.execSearchSell(id);
                    ui.sellerMode(id, name);
                } else if(id.charAt(0) == 'E') {
                    name = executor.execSearchEmpl(id);
                    ui.employeeMode(id, name);
                }
                ui.refreshUI();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleCustomerReg(String[] personalList, String[] cardList, String[] addressList){
        try {
            if(executor.execSearchUser(personalList[2], personalList[4]) != null)
                return false;

            String id               = "C" + Amason.random.nextInt(10000);
            java.sql.Date date      = new java.sql.Date(new Date().getTime());
            SimpleDateFormat sdf    = new SimpleDateFormat("yyyy");
            java.sql.Date expire    = new java.sql.Date(sdf.parse(cardList[3]).getTime());
            int secNum              = Integer.parseInt(cardList[4]);
            int addr_id             = Amason.random.nextInt(10000);

            executor.execInsertUser(id, personalList[2], personalList[4], date);
            executor.execInsertAddr(addr_id, addressList[0], addressList[1], addressList[2],
                    addressList[3], addressList[4], addressList[5], addressList[6], addressList[7]);
            if(!executor.execSearchCard(cardList[0]))
                    executor.execInsertCard(cardList[0], cardList[1], cardList[2], expire, secNum, addr_id);
            executor.execInsertCust(id, personalList[0], personalList[1], personalList[3], addr_id);
            executor.execInsertPay(id, cardList[0]);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleSellerReg(String[] businessList, String[] bankList, String[] addressList){
        try {
            if(executor.execSearchUser(businessList[2], businessList[4]) != null)
                return false;

            String id               = "S" + Amason.random.nextInt(10000);
            java.sql.Date date      = new java.sql.Date(new Date().getTime());

            int routeNum            = Integer.parseInt(bankList[2]);
            int addr_id             = Amason.random.nextInt(10000);

            executor.execInsertUser(id, businessList[2], businessList[4], date);
            if(!executor.execSearchAcc(bankList[0]))
                executor.execInsertAcc(bankList[0], bankList[1], routeNum);
            executor.execInsertAddr(addr_id, addressList[0], addressList[1], addressList[2],
                    addressList[3], addressList[4], addressList[5], addressList[6], addressList[7]);
            executor.execInsertSell(id, businessList[0], businessList[1], businessList[3], addr_id);
            executor.execInsertDeposit(id, bankList[0]);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleEmployeeReg(String firstname, String lastname, String email, String password, String department, String designation, String category){
        try {
            if(executor.execSearchUser(email, password) != null)
                return false;

            String id               = "E" + Amason.random.nextInt(10000);
            java.sql.Date date      = new java.sql.Date(new Date().getTime());

            Department dept = executor.execGetDepartment(department);
            int cateId = executor.execSearchCate(category);
            executor.execInsertUser(id, email, password, date);
            executor.execInsertEmpl(id, firstname, lastname, dept.getId(), designation, cateId);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Category> handleCategory(int deptId) {
        try {
            return executor.execGetCategories(deptId);
        } catch (Exception e) {
            return null;
        }
    }

    public Department handleDepartment(String name) {
        try {
            return executor.execGetDepartment(name);
        } catch (Exception e) {
            return null;
        }
    }
}
