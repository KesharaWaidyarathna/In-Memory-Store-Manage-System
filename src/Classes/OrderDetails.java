package Classes;

public class OrderDetails {
    private String code;
    private String quantity;
    private double unitPrice;

    public OrderDetails(String code, String quantity, double unitPrice) {
        this.setCode(code);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
    }

    public OrderDetails() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "code='" + code + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
