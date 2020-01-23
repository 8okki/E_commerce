package Database;

public enum Employee {

    ID("Employee_ID"),
    FIRST("FirstName"),
    LAST("LastName"),
    DESIGNATION("Designation"),
    DEPARTMENT("Department_ID"),
    CATEGORY("Category_ID");

    public String colName;
    public static final String name = "Employee";
    public static int count = 0;

    Employee(String colName) { this.colName = colName; }

}
