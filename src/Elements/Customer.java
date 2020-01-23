package Elements;

import javafx.beans.property.SimpleStringProperty;

public class Customer {

    SimpleStringProperty id;
    SimpleStringProperty first;
    SimpleStringProperty last;
    SimpleStringProperty email;
    SimpleStringProperty phone;
    SimpleStringProperty date;


    public Customer(String id, String first, String last, String email, String phone, String date) {
        this.id = new SimpleStringProperty(id);
        this.first = new SimpleStringProperty(first);
        this.last = new SimpleStringProperty(last);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
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

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
