package Classes;

import java.util.ArrayList;

public class Orders {
    private String id;
    private String date;
    private String customerId;
    private ArrayList<OrderDetails> orderDetails;

    public Orders(String id, String date, String customerId, ArrayList<OrderDetails> orderDetails) {
        this.setId(id);
        this.setDate(date);
        this.setCustomerId(customerId);
        this.setOrderDetails(orderDetails);
    }

    public Orders() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
