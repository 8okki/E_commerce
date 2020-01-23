package Elements;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {

    SimpleIntegerProperty id;
    SimpleStringProperty name;
    SimpleStringProperty seller;
    SimpleStringProperty category;
    SimpleDoubleProperty price;
    SimpleIntegerProperty qty;
    SimpleStringProperty description;
    SimpleDoubleProperty subtotal;
    SimpleStringProperty trackID;

    public Item(){
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        seller = new SimpleStringProperty();
        category = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        qty = new SimpleIntegerProperty();
        description = new SimpleStringProperty();
        subtotal = new SimpleDoubleProperty();
        trackID = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSeller() {
        return seller.get();
    }

    public void setSeller(String seller) {
        this.seller.set(seller);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public int getQty() {
        return qty.get();
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public String getTrackID() {
        return trackID.get();
    }

    public void setTrackID(String trackID) {
        this.trackID.set(trackID);
    }
}
