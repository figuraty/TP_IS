package data;

import data.entities.Item;

public class Purchases {
    private Item item;
    private int numberOfUnits;
    private int unitPrice;
    private int totalPrice;

    public Purchases(Item item, int numberOfUnits, int unitPrice, int totalPrice) {
        this.item = item;
        this.numberOfUnits = numberOfUnits;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
