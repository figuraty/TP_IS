public class Purchase {
    private String item;
    private int numberOfUnits;
    private int totalPrice;

    public Purchase(String item, int numberOfUnits, int price) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        totalPrice = numberOfUnits * price;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
