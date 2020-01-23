package DB_Executor;

import Database.*;
import Database.Item;
import Database.Package;
import Elements.*;
import Elements.Category;
import Elements.Customer;
import Elements.Employee;
import Elements.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Executor {

    private static Executor     executor;

    private Connection          connection;

    private String              searchUser;
    private String              searchCust;
    private String              searchSell;
    private String              searchEmpl;
    private String              searchItem;
    private String              searchItemName;
    private String              searchCard;
    private String              searchAcc;
    private String              searchCart;
    private String              searchOrder;
    private String              searchOrderItem;
    private String              prepareOrder;
    private String              searchInven;
    private String              searchSales;
    private String              getItems;
    private String              getCustomers;
    private String              getSellers;
    private String              getEmployees;
    private String              getCategories;
    private String              getDepartment;
    private String              searchCate;

    private String              insertUser;
    private String              insertCust;
    private String              insertSell;
    private String              insertEmpl;
    private String              insertOrder;
    private String              insertShip;
    private String              insertTrans;
    private String              insertPackage;
    private String              insertCard;
    private String              insertAcc;
    private String              insertCart;
    private String              insertAddr;
    private String              insertPay;
    private String              insertDeposit;
    private String              insertItem;

    private String              deleteCart;
    private String              deleteItem;

    private String              updateCart;
    private String              updateItem;


    public static Executor getExecutor() {
        if(executor == null)
            executor = new Executor();
        return executor;
    }

    private Executor() {}

    public void initialize(Connection connection) {
        this.connection = connection;
        initSearch();
        initInsert();
        initDelete();
        initUpdate();
    }

    private void initSearch() {
        searchUser = String.format("SELECT %s FROM %s WHERE %s = ? AND %s = ?;",
                User.ID.colName, User.name, User.EMAIL.colName, User.PASSWORD.colName);

        searchCust = "SELECT * " +
                "FROM E_Customer " +
                "WHERE Customer_ID = ?";

        searchSell = "SELECT * " +
                "FROM E_Seller " +
                "WHERE Seller_ID = ?";

        searchEmpl = "SELECT * " +
                "FROM Employee " +
                "WHERE Employee_ID = ?";

        searchItem = "SELECT I.Item_ID, I.Item_Name, S.BusinessName, I.Item_Description, I.UnitPrice, I.Quantity " +
                "FROM Item I, E_Seller S " +
                "WHERE I.Item_ID = ? AND I.SellerID = S.Seller_ID";

        searchItemName = "SELECT I.Item_ID, I.Item_Name, I.Quantity, I.UnitPrice, S.BusinessName, C.Category_Name, I.Item_Description " +
                "FROM Item I, E_Seller S, Category C " +
                "WHERE I.Item_Name LIKE ? AND I.SellerID = S.Seller_ID AND I.Category_ID = C.Category_ID;";

        searchCard = "SELECT CardNum " +
                "FROM Card " +
                "WHERE CardNum = ?;";

        searchAcc = String.format("SELECT %s FROM %s WHERE %s = ?;",
                Account.ACC_NUM.colName, Account.name, Account.ACC_NUM.colName);

        searchCart = "SELECT C.Item_ID, I.Item_Name, C.Quantity, I.UnitPrice, S.Seller_ID, T.Category_Name, S.BusinessName " +
                "FROM ShoppingCart C, Item I, E_Seller S, Category T " +
                "WHERE C.Customer_ID = ? AND C.Item_ID = I.Item_ID AND I.SellerID = S.Seller_ID AND I.Category_ID = T.Category_ID;";

        searchOrder = "SELECT O.Order_ID, O.Order_Date, C.CardNum, A.NickName, A.StreetAddress1, A.StreetAddress2, A.City, A.State, A.Country, A.ZipCode " +
                "FROM E_Order O,  E_Transaction T, Shipment S, Card C, Address A " +
                "WHERE O.Customer_ID = ? AND O.Order_ID = T.Order_ID AND O.Order_ID = S.Order_ID AND T.CardNum = C.CardNum AND S.Destination_ID = A.Address_ID;";

        prepareOrder = "SELECT C.Address_ID AS Destination, P.CardNum, S.Address_ID AS Source, D.BankAccountNumber " +
                "FROM E_Customer C, Pay P, E_Seller S, Deposit D " +
                "WHERE C.Customer_ID = ? AND S.Seller_ID = ? AND C.Customer_ID = P.Customer_ID AND S.Seller_ID = D.Seller_ID;";

        searchOrderItem = "SELECT I.Item_Name, B.BusinessName, P.UnitPrice, P.Quantity, S.Tracking_ID " +
                "FROM Shipment S, Package P, Item I, E_Seller B " +
                "WHERE S.Order_ID = ? AND S.Tracking_ID = P.Tracking_ID AND P.Item_ID = I.Item_ID AND I.SellerID = B.Seller_ID;";

        searchInven = "SELECT I.Item_ID, I.Item_Name, I.Item_Description, I.UnitPrice, I.Quantity, C.Category_Name " +
                "FROM Item I, Category C " +
                "WHERE I.SellerID = ? AND I.Category_ID = C.Category_ID;";

        searchSales = "SELECT I.Item_ID, I.Item_Name, P.UnitPrice, P.Quantity, S.Shipping_Fee, O.Order_Date " +
                "FROM Item I, Package P, Shipment S, E_Order O " +
                "WHERE I.SellerID = ? AND I.Item_ID = P.Item_ID AND P.Tracking_ID = S.Tracking_ID AND S.Order_ID = O.Order_ID;";

        getItems = "SELECT I.Item_ID, I.Item_Name, I.Item_Description, S.BusinessName, C.Category_Name, I.UnitPrice, I.Quantity " +
                "FROM Item I, E_Seller S, Category C " +
                "WHERE I.SellerID = S.Seller_ID AND I.Category_ID = C.Category_ID;";

        getCustomers = "SELECT C.Customer_ID, C.FirstName, C.LastName, U.User_Email, C.PhoneNumber, U.RegistrationDate " +
                "FROM E_Customer C, E_User U " +
                "WHERE C.Customer_ID = U.User_ID;";

        getSellers = "SELECT S.Seller_ID, S.BusinessName, U.User_Email, S.BusinessPhoneNum, S.WebsiteLink, U.RegistrationDate " +
                "FROM E_Seller S, E_User U " +
                "WHERE S.Seller_ID = U.User_ID;";

        getEmployees = "SELECT E.Employee_ID, E.FirstName, E.LastName, U.User_Email, E.Designation, D.Department_Name, C.Category_Name, U.RegistrationDate " +
                "FROM Employee E, E_User U, Department D, Category C " +
                "WHERE E.Employee_ID = U.User_ID AND E.Department_ID = D.Department_ID AND E.Category_ID = C.Category_ID;";

        getCategories = "SELECT * FROM Category C WHERE C.Department_ID = ?";

        getDepartment = "SELECT * FROM Department D WHERE D.Department_Name = ?";

        searchCate = "SELECT C.Category_ID FROM Category C WHERE C.Category_Name = ?";
    }

    private void initInsert() {
        insertUser = String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?);",
                User.name, User.ID.colName, User.EMAIL.colName, User.PASSWORD.colName, User.REGI_DATE.colName);

        insertCust = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
                Database.Customer.name, Database.Customer.ID.colName, Database.Customer.FIRST.colName,
                Database.Customer.LAST.colName, Database.Customer.PHONE.colName, Database.Customer.ADDRESS.colName);

        insertSell = String.format("INSERT INTO %s(%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?);",
                Database.Seller.name, Database.Seller.ID.colName, Database.Seller.NAME.colName, Database.Seller.PHONE.colName,
                Database.Seller.WEBSITE.colName, Database.Seller.ADDRESS.colName);

        insertEmpl = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);",
                Database.Employee.name, Database.Employee.ID.colName, Database.Employee.FIRST.colName, Database.Employee.LAST.colName,
                Database.Employee.DEPARTMENT.colName, Database.Employee.DESIGNATION.colName, Database.Employee.CATEGORY.colName);

        insertItem = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?);",
                Database.Item.name, Database.Item.ID.colName, Database.Item.SELLER.colName, Item.CATEGORY.colName, Database.Item.NAME.colName,
                Database.Item.DESCRIPT.colName, Database.Item.PRICE.colName, Database.Item.QTY.colName);

        insertOrder = String.format("INSERT INTO %s(%s, %s, %s) VALUES (?, ?, ?);",
                Database.Order.name, Database.Order.ID.colName, Database.Order.DATE.colName, Database.Order.CUSTOMER.colName);

        insertShip  = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                Shipment.name, Shipment.TRACK_ID.colName, Shipment.ORDER.colName, Shipment.DEPART_DATE.colName, Shipment.ARRIVAL_DATE.colName,
                Shipment.CARRIER.colName, Shipment.FEE.colName, Shipment.DESTINATION.colName, Shipment.SOURCE.colName);

        insertTrans = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);",
                Transaction.name, Transaction.ID.colName, Transaction.ORDER.colName, Transaction.DATE.colName,
                Transaction.AMOUNT.colName, Transaction.CARD_NUM.colName, Transaction.ACC_NUM.colName);

        insertPackage = String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?);",
                Package.name, Package.TRACK_ID.colName, Package.ITEM.colName, Package.PRICE.colName, Package.QTY.colName);

        insertCard = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?);",
                Card.name, Card.CARD_NUM.colName, Card.TYPE.colName, Card.HOLDER_NAME.colName,
                Card.EXP_DATE.colName, Card.SEC_NUM.colName, Card.ADDRESS.colName);

        insertAcc = String.format("INSERT INTO %s(%s, %s, %s) VALUES (?, ?, ?);",
                Account.name, Account.ACC_NUM.colName, Account.HOLDER_NAME.colName, Account.ROUTING_NUM.colName);

        insertCart = String.format("INSERT INTO %s(%s, %s, %s) VALUES (?, ?, ?);",
                ShoppingCart.name, ShoppingCart.CUSTOMER.colName, ShoppingCart.ITEM.colName, ShoppingCart.QTY.colName);

        insertAddr = String.format("INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                Address.name, Address.ID.colName, Address.NAME.colName, Address.ST1.colName, Address.ST2.colName,
                Address.CITY.colName, Address.STATE.colName, Address.COUNTRY.colName, Address.ZIP.colName, Address.PHONE.colName);

        insertPay = String.format("INSERT INTO %s(%s, %s) VALUES (?, ?);",
                Pay.name, Pay.CUSTOMER.colName, Pay.CARD_NUM.colName);

        insertDeposit = String.format("INSERT INTO %s(%s, %s) VALUES (?, ?);",
                Deposit.name, Deposit.SELLER.colName, Deposit.ACC_NUM.colName);

    }

    private void initUpdate() {
        updateCart      =   "UPDATE ShoppingCart " +
                            "SET Quantity = ? " +
                            "WHERE Item_ID = ?";

        updateItem      =   "UPDATE Item " +
                            "SET Item_Name = ?, Item_Description = ?, UnitPrice = ?, Quantity = ? " +
                            "WHERE Item_ID = ?";
    }

    private void initDelete() {
        deleteCart      =   "DELETE FROM ShoppingCart " +
                "WHERE Customer_ID = ? AND Item_ID = ?;";

        deleteItem      =   "DELETE FROM ITEM " +
                "WHERE Item_ID = ?";
    }

    public String execSearchUser(String email, String password) throws SQLException {
        try(
                PreparedStatement stmt = connection.prepareStatement(searchUser)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                String id = result.getString(User.ID.colName);
                return id;
            }
            return null;
        } catch (SQLException e){
            throw e;
        }
    }

    public String execSearchCust(String id) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchCust)
                ) {
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            if(result.next())
                return result.getString("FirstName");
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String execSearchSell(String id) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchSell)
        ) {
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            if(result.next())
                return result.getString("BusinessName");
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String execSearchEmpl(String id) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchEmpl)
        ) {
            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();
            if(result.next())
                return result.getString("FirstName");
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execSearchItem(String pattern) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchItemName)
                ) {
            stmt.setString(1, "%" + pattern + "%");
            ResultSet result = stmt.executeQuery();
            ObservableList items = FXCollections.observableArrayList();
            while(result.next()){
                Elements.Item item = new Elements.Item();
                item.setId(result.getInt("Item_ID"));
                item.setName(result.getString("Item_Name"));
                item.setSeller(result.getString("BusinessName"));
                item.setCategory(result.getString("Category_Name"));
                item.setPrice(result.getDouble("UnitPrice"));
                item.setQty(result.getInt("Quantity"));
                item.setDescription(result.getString("Item_Description"));

                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Elements.Item execSearchItem(int id) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchItem)
        ) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            Elements.Item item = new Elements.Item();
            if(result.next()){
                item.setId(result.getInt("Item_ID"));
                item.setName(result.getString("Item_Name"));
                item.setSeller(result.getString("BusinessName"));
                item.setPrice(result.getDouble("UnitPrice"));
                item.setQty(result.getInt("Quantity"));
                item.setDescription(result.getString("Item_Description"));
            }
            return item;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean execSearchCard(String cardNum) throws SQLException {
        try (
            PreparedStatement stmt = connection.prepareStatement(searchCard)
            ) {
            stmt.setString(1, cardNum);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean execSearchAcc(String accNum) throws SQLException {
        try(
                PreparedStatement stmt = connection.prepareStatement(searchAcc)
        ) {
            stmt.setString(1, accNum);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList<HashMap<String, Object>> execSearchCart(String customer) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchCart)
        ) {
            stmt.setString(1, customer);
            ResultSet result = stmt.executeQuery();


            ArrayList<HashMap<String, Object>> shopCart = new ArrayList<>();
            while(result.next()){
                HashMap<String, Object> row = new HashMap<>();
                row.put("Item_ID", result.getInt("Item_ID"));
                row.put("Item_Name", result.getString("Item_Name"));
                row.put("Quantity", result.getInt("Quantity"));
                row.put("UnitPrice", result.getDouble("UnitPrice"));
                row.put("Seller_ID", result.getString("Seller_ID"));
                row.put("Category_Name", result.getString("Category_Name"));
                row.put("BusinessName", result.getString("BusinessName"));
                shopCart.add(row);
            }
            return shopCart;
        } catch (SQLException e){
            throw e;
        }
    }

    public ObservableList execSearchOrder(String customer) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchOrder)
        ) {
            stmt.setString(1, customer);
            ResultSet result = stmt.executeQuery();
            ObservableList orders = FXCollections.observableArrayList();
            while(result.next()){
                int id              = result.getInt("Order_ID");
                Date date           = result.getDate("Order_Date");
                String cardNum      = result.getString("CardNum");
                String nickname     = result.getString("NickName");
                String st1          = result.getString("StreetAddress1");
                String st2          = result.getString("StreetAddress2");
                String city         = result.getString("City");
                String state        = result.getString("State");
                String country      = result.getString("Country");
                String zip          = result.getString("ZipCode");

                Elements.Order order = new Elements.Order(id, date.toString(), cardNum);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execSearchOrderItems(int order) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchOrderItem)
                ) {
            stmt.setInt(1, order);
            ResultSet result = stmt.executeQuery();
            ObservableList orderItems = FXCollections.observableArrayList();
            while(result.next()){
                Elements.Item orderItem = new Elements.Item();
                orderItem.setName(result.getString("Item_Name"));
                orderItem.setSeller(result.getString("BusinessName"));
                orderItem.setPrice(result.getDouble("UnitPrice"));
                orderItem.setQty(result.getInt("Quantity"));
                orderItem.setTrackID(""+result.getInt("Tracking_ID"));
                orderItem.setSubtotal(orderItem.getPrice() * orderItem.getQty());

                orderItems.add(orderItem);
            }
            return orderItems;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ArrayList execPrepareOrder(String customer, String seller) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(prepareOrder)
        ) {
            stmt.setString(1, customer);
            stmt.setString(2, seller);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                ArrayList infos = new ArrayList();
                infos.add(result.getInt("Destination"));
                infos.add(result.getInt("Source"));
                infos.add(result.getString("CardNum"));
                infos.add(result.getString("BankAccountNumber"));
                return infos;
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execSearchInven(String seller) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchInven)
                ) {
            stmt.setString(1, seller);
            ResultSet result = stmt.executeQuery();
            ObservableList inventory = FXCollections.observableArrayList();
            while(result.next()){
                Elements.Item item = new Elements.Item();
                item.setId(result.getInt("Item_ID"));
                item.setName(result.getString("Item_Name"));
                item.setPrice(result.getDouble("UnitPrice"));
                item.setQty(result.getInt("Quantity"));
                item.setCategory(result.getString("Category_Name"));
                item.setDescription(result.getString("Item_Description"));

                inventory.add(item);
            }
            return inventory;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execSearchSales(String seller) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchSales)
                ) {
            stmt.setString(1, seller);
            ResultSet result = stmt.executeQuery();
            ObservableList salesHistory = FXCollections.observableArrayList();
            while(result.next()){
                int id              = result.getInt("Item_ID");
                String name         = result.getString("Item_Name");
                double price        = result.getDouble("UnitPrice");
                int qty             = result.getInt("Quantity");
                double profit       = price * qty;
                double fee          = result.getDouble("Shipping_Fee");
                double net          = profit - fee;
                Date date           = result.getDate("Order_Date");

                Sales sales = new Sales(id, name, price, qty, profit, fee, net, date.toString());
                salesHistory.add(sales);
            }
            return salesHistory;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execGetItems() throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getItems)
                ) {
            ResultSet result = stmt.executeQuery();
            ObservableList items = FXCollections.observableArrayList();
            while(result.next()){
                Elements.Item item = new Elements.Item();
                item.setId(result.getInt("Item_ID"));
                item.setName(result.getString("Item_Name"));
                item.setDescription(result.getString("Item_Description"));
                item.setSeller(result.getString("BusinessName"));
                item.setCategory(result.getString("Category_Name"));
                item.setPrice(result.getDouble("UnitPrice"));
                item.setQty(result.getInt("Quantity"));
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execGetCustomers() throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getCustomers)
        ) {
            ResultSet result = stmt.executeQuery();
            ObservableList customers = FXCollections.observableArrayList();
            while(result.next()){
                String id       = result.getString("Customer_ID");
                String first    = result.getString("FirstName");
                String last     = result.getString("LastName");
                String email    = result.getString("User_Email");
                String phone    = result.getString("PhoneNumber");
                String date     = result.getDate("RegistrationDate").toString();
                Customer customer = new Customer(id, first, last, email, phone, date);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execGetSellers() throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getSellers)
        ) {
            ResultSet result = stmt.executeQuery();
            ObservableList sellers = FXCollections.observableArrayList();
            while(result.next()){
                String id       = result.getString("Seller_ID");
                String name     = result.getString("BusinessName");
                String email    = result.getString("User_Email");
                String phone    = result.getString("BusinessPhoneNum");
                String website  = result.getString("WebsiteLink");
                String date     = result.getDate("RegistrationDate").toString();
                Seller seller = new Seller(id, name, email, phone, website, date);
                sellers.add(seller);
            }
            return sellers;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execGetEmployees() throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getEmployees)
        ) {
            ResultSet result = stmt.executeQuery();
            ObservableList employees = FXCollections.observableArrayList();
            while(result.next()){
                String id               = result.getString("Employee_ID");
                String first            = result.getString("FirstName");
                String last             = result.getString("LastName");
                String email            = result.getString("User_Email");
                String designation      = result.getString("Designation");
                String department       = result.getString("Department_Name");
                String category         = result.getString("Category_Name");
                String date             = result.getDate("RegistrationDate").toString();
                Elements.Employee employee = new Employee(id, first, last, email, designation, department, category, date);
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw e;
        }
    }

    public Elements.Department execGetDepartment(String name) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getDepartment)
        ) {
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
               int id = result.getInt("Department_ID");
               String deptName = result.getString("Department_Name");
               return new Elements.Department(id, deptName);
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    public ObservableList execGetCategories(int deptID) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(getCategories)
                ) {
            stmt.setInt(1, deptID);
            ResultSet result = stmt.executeQuery();
            ObservableList categories = FXCollections.observableArrayList();
            while(result.next()) {
                int id              = result.getInt("Category_ID");
                int department      = result.getInt("Department_ID");
                String name         = result.getString("Category_Name");
                Category category = new Category(id, department, name);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw e;
        }
    }

    public int execSearchCate(String name) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(searchCate)
        ) {
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            if(result.next())
                return result.getInt("Category_ID");
            return -1;
        } catch (SQLException e) {
            throw e;
        }
    }



    public void execInsertUser(String id, String email, String password, Date date) throws SQLException {
        try (

                PreparedStatement stmt = connection.prepareStatement(insertUser)
                ) {
            stmt.setString(1, id);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setDate(4, date);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertCust(String id, String firstname, String lastname, String phone, int address) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertCust)
        ) {
            stmt.setString(1, id);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, phone);
            stmt.setInt(5, address);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertSell(String id, String name, String phone, String website, int address) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertSell)
        ) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            stmt.setString(4, website);
            stmt.setInt(5, address);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertEmpl(String id, String firstname, String lastname, int department, String designation, int category) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertEmpl)
                ) {
            stmt.setString(1, id);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setInt(4, department);
            stmt.setString(5, designation);
            stmt.setInt(6, category);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertItem(int item, String seller, String name, int category, String description, double price, int qty) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertItem)
                ) {
            stmt.setInt(1, item);
            stmt.setString(2, seller);
            stmt.setInt(3, category);
            stmt.setString(4, name);
            stmt.setString(5, description);
            stmt.setDouble(6, price);
            stmt.setInt(7, qty);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertOrder(int order, Date date, String customer) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertOrder)
                ) {
            stmt.setInt(1, order);
            stmt.setDate(2, date);
            stmt.setString(3, customer);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertShip(int track, int order, Date depart, Date arrival, String carrier, double fee, int destination, int source) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertShip)
        ) {
            stmt.setInt(1, track);
            stmt.setInt(2, order);
            stmt.setDate(3, depart);
            stmt.setDate(4, arrival);
            stmt.setString(5, carrier);
            stmt.setDouble(6, fee);
            stmt.setInt(7, destination);
            stmt.setInt(8, source);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertTrans(int trans, int order, Date date, double amount, String cardNum, String accNum) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertTrans)
        ) {
            stmt.setInt(1, trans);
            stmt.setInt(2, order);
            stmt.setDate(3, date);
            stmt.setDouble(4, amount);
            stmt.setString(5, cardNum);
            stmt.setString(6, accNum);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertPack(int track, int item, double price, int qty) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertPackage)
        ) {
            stmt.setInt(1, track);
            stmt.setInt(2, item);
            stmt.setDouble(3, price);
            stmt.setInt(4, qty);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertCard(String cardNum, String cardType, String holderName, Date expire, int secNum, int addr_id) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertCard)
                ) {
            stmt.setString(1, cardNum);
            stmt.setString(2, cardType);
            stmt.setString(3, holderName);
            stmt.setDate(4, expire);
            stmt.setInt(5, secNum);
            stmt.setInt(6, addr_id);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertAcc(String accNum, String name, int routeNum) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertAcc)
        ) {
            stmt.setString(1, accNum);
            stmt.setString(2, name);
            stmt.setInt(3, routeNum);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertCart(String id, int item, int qty) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertCart)
                ) {
            stmt.setString(1, id);
            stmt.setInt(2, item);
            stmt.setInt(3, qty);
            stmt.execute();
        } catch (SQLException e){
            throw e;
        }
    }

    public void execInsertAddr(int id, String name, String st1, String st2, String city, String state, String country, String zip, String phone) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertAddr)
        ) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setString(3, st1);
            stmt.setString(4, st2);
            stmt.setString(5, city);
            stmt.setString(6, state);
            stmt.setString(7, country);
            stmt.setString(8, zip);
            stmt.setString(9, phone);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertPay(String customerId, String cardNum) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertPay)
        ) {
            stmt.setString(1, customerId);
            stmt.setString(2, cardNum);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execInsertDeposit(String sellerId, String accNum) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(insertDeposit)
                ) {
            stmt.setString(1, sellerId);
            stmt.setString(2, accNum);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execUpdateCartItem(int qty, int item) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(updateCart)
                ) {
            stmt.setInt(1, qty);
            stmt.setInt(2, item);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execUpdateItem(String name, String description, double price, int qty, int item) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(updateItem)
                ) {
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, qty);
            stmt.setInt(5, item);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execDeleteCartItem(String customer, int item) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(deleteCart)
        ) {
            stmt.setString(1, customer);
            stmt.setInt(2, item);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void execDeleteItem(int item) throws SQLException {
        try (
                PreparedStatement stmt = connection.prepareStatement(deleteItem)
        ) {
            stmt.setInt(1, item);
            stmt.execute();
        } catch (SQLException e) {
            throw e;
        }
    }
}