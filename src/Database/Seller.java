package Database;

public enum Seller {

    ID("Seller_ID"),
    NAME("BusinessName"),
    PHONE("BusinessPhoneNum"),
    WEBSITE("WebsiteLink"),
    ADDRESS("Address_ID");

    public String colName;
    public static final String name = "E_Seller";
    public static int count = 0;

    Seller(String colName) { this.colName = colName; }

}
