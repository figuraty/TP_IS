public class Sale {
    private String item;
    private int numberOfUnits;
    private int unitPrice;
    private String country;

    public Sale(String item, int numberOfUnits, int unitPrice, String country) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
