package Elements;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    SimpleIntegerProperty id;
    SimpleStringProperty date;
    SimpleStringProperty cardNum;

    public Order(int id, String date, String cardNum) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.cardNum = new SimpleStringProperty(cardNum);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getCardNum() {
        return cardNum.get();
    }

    public void setCardNum(String cardNum) {
        this.cardNum.set(cardNum);
    }
}
