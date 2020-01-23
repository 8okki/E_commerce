package Elements;

import javafx.beans.property.SimpleStringProperty;

public class Seller {

    SimpleStringProperty id;
    SimpleStringProperty name;
    SimpleStringProperty email;
    SimpleStringProperty phone;
    SimpleStringProperty website;
    SimpleStringProperty date;

    public Seller(String id, String name, String email, String phone, String website, String date) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.website = new SimpleStringProperty(website);
        this.date = new SimpleStringProperty(date);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getWebsite() {
        return website.get();
    }

    public void setWebsite(String website) {
        this.website.set(website);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
