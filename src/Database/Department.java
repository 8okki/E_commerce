package Database;

public enum Department {

    ID("Department_ID"),
    NAME("Department_Name");

    public String colName;
    public static final String name = "Department";
    public static int count = 0;

    Department(String colName) { this.colName = colName; }

}
