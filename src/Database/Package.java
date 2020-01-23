package Database;

public enum Package {

    TRACK_ID("Tracking_ID"),
    ITEM("Item_ID"),
    PRICE("UnitPrice"),
    QTY("Quantity");

    public String colName;
    public static final String name = "Package";

    Package(String colName) { this.colName = colName; }

}
