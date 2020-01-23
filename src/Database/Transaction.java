package Database;

public enum Transaction {

    ID("Transaction_ID"),
    ORDER("Order_ID"),
    DATE("Transaction_Date"),
    AMOUNT("Amount"),
    CARD_NUM("CardNum"),
    ACC_NUM("BankAccountNumber");

    public String colName;
    public static final String name = "E_Transaction";
    public static int count = 1111;

    Transaction(String colName) {this.colName = colName;}

}
