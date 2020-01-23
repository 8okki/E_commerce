package Database;

public enum Shipment {

    TRACK_ID("Tracking_ID"),
    ORDER("Order_ID"),
    DEPART_DATE("Depart_Date"),
    ARRIVAL_DATE("Arrival_Date"),
    CARRIER("Shipment_Carrier"),
    FEE("Shipping_Fee"),
    DESTINATION("Destination_ID"),
    SOURCE("Source_ID");

    public String colName;
    public static final String name = "Shipment";
    public static int count = 1111;

    Shipment(String colName) {this.colName = colName;}

}
