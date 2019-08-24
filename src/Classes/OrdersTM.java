package Classes;

import com.jfoenix.controls.JFXButton;

public class OrdersTM {

    private String ItemCode;
    private String Description;
    private String Quantity;
    private String UnitePrice;
    private String Total;
    private JFXButton delete;

    public OrdersTM(String itemCode, String description, String quantity, String unitePrice, String total, JFXButton delete) {
        setItemCode(itemCode);
        setDescription(description);
        setQuantity(quantity);
        setUnitePrice(unitePrice);
        setTotal(total);
        this.setDelete(delete);
    }

    public OrdersTM() {
    }


    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUnitePrice() {
        return UnitePrice;
    }

    public void setUnitePrice(String unitePrice) {
        UnitePrice = unitePrice;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public JFXButton getDelete() {
        return delete;
    }

    public void setDelete(JFXButton delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "OrdersTM{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", UnitePrice='" + UnitePrice + '\'' +
                ", Total='" + Total + '\'' +
                ", delete=" + delete +
                '}';
    }
}
