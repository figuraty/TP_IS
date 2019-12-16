public class Purchase {
    private String item;
    private int numberOfUnits;
    private int unitPrice;

    public Purchase(String item, int numberOfUnits, int unitPrice) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        this.unitPrice = unitPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
