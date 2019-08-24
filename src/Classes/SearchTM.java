package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchTM {

    private String orderID;
    private String date;
    private String total;
    private String customerId;
    private String customerName;

    public SearchTM(String orderID, String date, String total, String customerId, String customerName) {

        this.setOrderID(orderID);
        this.setDate(date);
        this.setTotal(total);
        this.setCustomerId(customerId);
        this.setCustomerName(customerName);
    }


    public SearchTM() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
