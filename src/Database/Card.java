package Database;

public enum Card {

    CARD_NUM("CardNum"),
    TYPE("PaymentType"),
    HOLDER_NAME("CardHolder_Name"),
    EXP_DATE("CardExpireDate"),
    SEC_NUM("SecurityNumber"),
    ADDRESS("Address_ID");

    public String colName;
    public static final String name = "Card";

    Card(String colName) { this.colName = colName; }

}
