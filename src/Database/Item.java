package Database;

public enum Item {

    ID("Item_ID"),
    SELLER("SellerID"),
    CATEGORY("Category_ID"),
    NAME("Item_Name"),
    DESCRIPT("Item_Description"),
    PRICE("UnitPrice"),
    QTY("Quantity");

    public String colName;
    public static final String name = "Item";
    public static int count = 0;

    Item(String colName) { this.colName = colName; }

}
