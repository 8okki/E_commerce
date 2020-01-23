package Database;

public enum Pay {

    CUSTOMER("Customer_ID"),
    CARD_NUM("CardNum");

    public String colName;
    public static final String name = "Pay";

    Pay(String colName) { this.colName = colName; }

}
