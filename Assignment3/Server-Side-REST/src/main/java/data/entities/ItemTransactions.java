package data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ItemTransactions")
public class ItemTransactions {
    @Id
    private String itemName;
    private int revenues;
    private int expenses;
    private int profits;
    private int avgPurchaseAmount;
    private String highestSalesCountry;

    public ItemTransactions(String itemName, int revenues, int expenses, int profits, int avgPurchaseAmount, String highestSalesCountry) {
        this.itemName = itemName;
        this.revenues = revenues;
        this.expenses = expenses;
        this.profits = profits;
        this.avgPurchaseAmount = avgPurchaseAmount;
        this.highestSalesCountry = highestSalesCountry;
    }

    public ItemTransactions(){
        super();
    }

    public String getItemName() {
        return itemName;
    }

    public int getRevenues() {
        return revenues;
    }

    public int getExpenses() {
        return expenses;
    }

    public int getProfits() {
        return profits;
    }

    public int getAvgPurchaseAmount() {
        return avgPurchaseAmount;
    }

    public String getHighestSalesCountry() {
        return highestSalesCountry;
    }
}

