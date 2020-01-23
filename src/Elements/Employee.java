package Elements;

import javafx.beans.property.SimpleStringProperty;

public class Employee {

    SimpleStringProperty id;
    SimpleStringProperty first;
    SimpleStringProperty last;
    SimpleStringProperty email;
    SimpleStringProperty designation;
    SimpleStringProperty department;
    SimpleStringProperty category;
    SimpleStringProperty date;

    public Employee(String id, String first, String last, String email, String designation, String department, String category, String date) {
        this.id = new SimpleStringProperty(id);
        this.first = new SimpleStringProperty(first);
        this.last = new SimpleStringProperty(last);
        this.email = new SimpleStringProperty(email);
        this.designation = new SimpleStringProperty(designation);
        this.department = new SimpleStringProperty(department);
        this.category = new SimpleStringProperty(category);
        this.date = new SimpleStringProperty(date);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getFirst() {
        return first.get();
    }

    public void setFirst(String first) {
        this.first.set(first);
    }

    public String getLast() {
        return last.get();
    }

    public void setLast(String last) {
        this.last.set(last);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDesignation() {
        return designation.get();
    }

    public void setDesignation(String designation) {
        this.designation.set(designation);
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
