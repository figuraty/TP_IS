package data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TotalTransactions")
public class TotalTransactions {
    @Id
    private int totalRevenues;
    private int totalExpenses;
    private int totalProfit;
    private int lastHourRevenues;
    private int lastHourExpenses;
    private int lastHourProfits;
    private int averagePurchaseAmount;
    private String itemHighestProfit;

    public TotalTransactions(){
        super();
    }

    public TotalTransactions(int totalRevenues, int totalExpenses, int totalProfit, int lastHourRevenues, int lastHourExpenses, int lastHourProfits, int averagePurchaseAmount, String itemHighestProfit) {
        this.totalRevenues = totalRevenues;
        this.totalExpenses = totalExpenses;
        this.totalProfit = totalProfit;
        this.lastHourRevenues = lastHourRevenues;
        this.lastHourExpenses = lastHourExpenses;
        this.lastHourProfits = lastHourProfits;
        this.averagePurchaseAmount = averagePurchaseAmount;
        this.itemHighestProfit = itemHighestProfit;
    }

    public int getTotalRevenues() {
        return totalRevenues;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public int getLastHourRevenues() {
        return lastHourRevenues;
    }

    public int getLastHourExpenses() {
        return lastHourExpenses;
    }

    public int getLastHourProfits() {
        return lastHourProfits;
    }

    public int getAveragePurchaseAmount() {
        return averagePurchaseAmount;
    }

    public String getItemHighestProfit() {
        return itemHighestProfit;
    }
}
