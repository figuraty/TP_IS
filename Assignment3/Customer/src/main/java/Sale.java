public class Sale {
    private String item;
    private int numberOfUnits;
    private int totalPrice;
    private String country;

    public Sale(String item, int numberOfUnits, int price, String country) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
