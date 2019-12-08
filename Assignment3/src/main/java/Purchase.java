public class Purchase {
    private Item item;
    private int numberOfUnits;
    private int totalPrice;

    public Purchase(Item item, int numberOfUnits) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        totalPrice = numberOfUnits * item.getPrice();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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

    @Override
    public String toString() {
        return "Purchase{" +
                "item=" + item.getName() +
                ", numberOfUnits=" + numberOfUnits +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
