package Elements;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category {

    SimpleIntegerProperty id;
    SimpleIntegerProperty deptID;
    SimpleStringProperty name;

    public Category(int id, int deptID, String name){
        this.id = new SimpleIntegerProperty(id);
        this.deptID = new SimpleIntegerProperty(deptID);
        this.name = new SimpleStringProperty(name);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getDeptID() {
        return deptID.get();
    }

    public void setDeptID(int deptID) {
        this.deptID.set(deptID);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
