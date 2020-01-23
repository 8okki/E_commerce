package Database;

public enum User {

    ID("User_ID"),
    EMAIL("User_Email"),
    PASSWORD("User_Password"),
    REGI_DATE("RegistrationDate");

    public String colName;
    public static final String name = "E_User";

    User(String colName){ this.colName = colName; }

}
