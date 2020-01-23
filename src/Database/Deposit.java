package Database;

public enum Deposit {

    SELLER("Seller_ID"),
    ACC_NUM("BankAccountNumber");

    public String colName;
    public static final String name = "Deposit";

    Deposit(String colName) { this.colName = colName; }

}
