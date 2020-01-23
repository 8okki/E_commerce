package Elements;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Sales {

    SimpleIntegerProperty id;
    SimpleStringProperty itemName;
    SimpleDoubleProperty price;
    SimpleIntegerProperty qty;
    SimpleDoubleProperty profit;
    SimpleDoubleProperty fee;
    SimpleDoubleProperty net;
    SimpleStringProperty date;

    public Sales(int id, String itemName, double price, int qty, double profit, double fee, double net, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.itemName = new SimpleStringProperty(itemName);
        this.price = new SimpleDoubleProperty(price);
        this.qty = new SimpleIntegerProperty(qty);
        this.profit = new SimpleDoubleProperty(profit);
        this.fee = new SimpleDoubleProperty(fee);
        this.net = new SimpleDoubleProperty(net);
        this.date = new SimpleStringProperty(date);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
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

    public double getProfit() {
        return profit.get();
    }

    public void setProfit(double profit) {
        this.profit.set(profit);
    }

    public double getFee() {
        return fee.get();
    }

    public void setFee(double fee) {
        this.fee.set(fee);
    }

    public double getNet() {
        return net.get();
    }

    public void setNet(double net) {
        this.net.set(net);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

}
