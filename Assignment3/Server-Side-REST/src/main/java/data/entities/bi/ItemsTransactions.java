package data.entities.bi;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ItemsTransactions")
public class ItemsTransactions {
    @Id
    private String itemName;
    private int revenues;
    private int expenses;
    private int profits;

    public ItemsTransactions(){
        super();
    }

    public ItemsTransactions(String itemName, int revenues, int expenses, int profits) {
        this.itemName = itemName;
        this.revenues = revenues;
        this.expenses = expenses;
        this.profits = profits;
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
}

