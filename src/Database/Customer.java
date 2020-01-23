package Database;

public enum Customer {

    ID("Customer_ID"),
    FIRST("FirstName"),
    LAST("LastName"),
    PHONE("PhoneNumber"),
    ADDRESS("Address_ID");

    public String colName;
    public static final String name = "E_Customer";
    public static int count = 0;

    Customer(String colName) { this.colName = colName; }

}
