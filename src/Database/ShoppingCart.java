package Database;

public enum ShoppingCart {

    CUSTOMER("Customer_ID"),
    ITEM("Item_ID"),
    QTY("Quantity");

    public String colName;
    public static final String name = "ShoppingCart";

    ShoppingCart(String colName) {this.colName = colName;}

}
