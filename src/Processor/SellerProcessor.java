package Processor;


import Application.Amason;
import UI.SellerUI;
import javafx.collections.ObservableList;


public class SellerProcessor extends UserProcessor{

    private SellerUI            ui;

    public SellerProcessor(SellerUI ui) {
        this.ui = ui;
    }

    public ObservableList handleInventory() {
        try {
            return executor.execSearchInven(currentID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList handleSales(){
        try {
            return executor.execSearchSales(currentID);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean handleAddItem(String name, String category, String description, double price, int qty) {
        try {
            int id = Amason.random.nextInt(10000);
            int cateId = executor.execSearchCate(category);
            executor.execInsertItem(id, currentID, name, cateId, description, price, qty);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleEditItem(int id, String name, String description, double price, int qty) {
        try {
            executor.execUpdateItem(name, description, price, qty, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean handleRemoveItem(int id){
        try {
            executor.execDeleteItem(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
