package Database;

public enum Category {

    ID("Category_ID"),
    DEPT("Department_ID"),
    NAME("Category_Name");

    public String colName;
    public static final String name = "Category";
    public static int count = 0;

    Category(String colName) { this.colName = colName; }

}
