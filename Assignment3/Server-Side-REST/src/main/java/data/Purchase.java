package data;

import data.entities.Item;

import java.io.Serializable;

public class Purchase implements Serializable{
    private Item item;
    private int numberOfUnits;
    private int unitPrice;
    private int totalPrice;


}
