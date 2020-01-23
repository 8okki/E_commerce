package Processor;

import Application.Amason;
import Elements.Item;
import UI.CustomerUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.*;

public class CustomerProcessor extends UserProcessor{

    private CustomerUI          ui;

    public CustomerProcessor(CustomerUI ui){

        this.ui = ui;
    }

    public ObservableList handleShopCart(){
        try {
            ArrayList<HashMap<String, Object>> result = executor.execSearchCart(currentID);
            ObservableList shopCart = FXCollections.observableArrayList();
            for(HashMap row : result){
                Item item = new Item();
                item.setId((Integer) row.get("Item_ID"));
                item.setName((String) row.get("Item_Name"));
                item.setSeller((String) row.get("BusinessName"));
                item.setCategory((String) row.get("Category_Name"));
                item.setPrice((Double) row.get("UnitPrice"));
                item.setQty((Integer) row.get("Quantity"));

                shopCart.add(item);
            }
            return shopCart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList handleOrder() {
        try {
            return executor.execSearchOrder(currentID);
        } catch (Exception e){
            return null;
        }
    }

    public ObservableList handleOrderItems(int orderID) {
        try {
            return executor.execSearchOrderItems(orderID);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleToCart(int itemID, int qty) {
        try {
            executor.execInsertCart(currentID, itemID, qty);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleToOrder() {
        try {
            // Insert Order
            int orderID             = Amason.random.nextInt(10000);
            Date date               = new Date(new java.util.Date().getTime());
            executor.execInsertOrder(orderID, date, currentID);

            // Find Seller-Item List
            Map<String, ArrayList<Integer>> sellers  = new HashMap<>();
            Map<Integer, Integer> quantities         = new HashMap<>();
            Map<Integer, Double> prices              = new HashMap<>();
            ArrayList<HashMap<String, Object>> result = executor.execSearchCart(currentID);
            for(HashMap row : result){
                String seller           = (String) row.get("Seller_ID");
                int itemID              = (Integer) row.get("Item_ID");
                int quantity            = (Integer) row.get("Quantity");
                double price            = (Double) row.get("UnitPrice");
                quantities.put(itemID, quantity);
                prices.put(itemID, price);

                if(!sellers.containsKey(seller))
                    sellers.put(seller, new ArrayList<>());

                sellers.get(seller).add(itemID);
            }

            // For each Seller, create Shipment, Packages, & Transaction
            // Reduce quantities from inventory
            for(String seller : sellers.keySet()){
                ArrayList infos             = executor.execPrepareOrder(currentID, seller);
                if (infos != null){
                    // Shipment
                    int trackID             = Amason.random.nextInt(10000);
                    Date depart             = new Date(date.getTime());
                    Date arrival            = new Date((long)(date.getTime() + 1.728e+8));
                    String carrier          = "FedEx";
                    double fee              = 2.5;
                    int destination         = (Integer) infos.get(0);
                    int source              = (Integer) infos.get(1);
                    executor.execInsertShip(trackID, orderID, depart, arrival, carrier, fee, destination, source);

                    // Transaction
                    double total = 0;
                    for(int itemID : sellers.get(seller)) total += quantities.get(itemID) * prices.get(itemID);
                    int transID             = Amason.random.nextInt(10000);
                    double amount           = total + fee;
                    String cardNum          = (String) infos.get(2);
                    String accountNum       = (String) infos.get(3);
                    executor.execInsertTrans(transID, orderID, date, amount, cardNum, accountNum);

                    // Packages & Reduce Quantity & Remove from Shopping Cart
                    for(int itemID : sellers.get(seller)) {
                        executor.execInsertPack(trackID, itemID, prices.get(itemID), quantities.get(itemID));
                        Item item = executor.execSearchItem(itemID);
                        int newQty = item.getQty() - quantities.get(itemID);
                        executor.execUpdateItem(item.getName(), item.getDescription(), item.getPrice(), newQty, item.getId());
                        handleDeleteCartItem(itemID);
                    }
                }
            }
            ui.getShopCart().clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleEditCartItem(int id, int qty){
        try {
            executor.execUpdateCartItem(id, qty);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleDeleteCartItem(int id){
        try {
            executor.execDeleteCartItem(currentID, id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
