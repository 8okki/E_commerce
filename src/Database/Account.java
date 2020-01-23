package Database;

public enum Account {

    ACC_NUM("BankAccountNumber"),
    HOLDER_NAME("Account_Holder_Name"),
    ROUTING_NUM("Routing_Number");

    public String colName;
    public static final String name = "E_Account";

    Account(String colName) { this.colName = colName; }

}
