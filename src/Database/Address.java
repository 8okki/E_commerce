package Database;

public enum Address {

    ID("Address_ID"),
    NAME("NickName"),
    ST1("StreetAddress1"),
    ST2("StreetAddress2"),
    CITY("City"),
    STATE("State"),
    COUNTRY("Country"),
    ZIP("ZipCode"),
    PHONE("PhoneNumber");

    public String colName;
    public static final String name = "Address";
    public static int count = 0;

    Address(String colName) { this.colName = colName; }
}
