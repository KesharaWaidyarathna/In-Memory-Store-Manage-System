package Classes;

public class ItemsTM {
    private String itemId;
    private String itemDiscripition;
    private String handsOnQuantity;
    private double unitPrice;

    public ItemsTM(String itemId, String itemDiscripition, String handsOnQuantity, double unitPrice) {
        this.setItemId(itemId);
        this.setItemDiscripition(itemDiscripition);
        this.setHandsOnQuantity(handsOnQuantity);
        this.setUnitPrice(unitPrice);
    }

    public ItemsTM() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDiscripition() {
        return itemDiscripition;
    }

    public void setItemDiscripition(String itemDiscripition) {
        this.itemDiscripition = itemDiscripition;
    }

    public String getHandsOnQuantity() {
        return handsOnQuantity;
    }

    public void setHandsOnQuantity(String handsOnQuantity) {
        this.handsOnQuantity = handsOnQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
