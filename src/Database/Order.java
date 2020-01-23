package Database;

public enum Order {

    ID("Order_ID"),
    DATE("Order_Date"),
    CUSTOMER("Customer_ID");

    public String colName;
    public static final String name = "E_Order";
    public static int count = 0;

    Order(String colName) { this.colName = colName; }

}
