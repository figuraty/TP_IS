package data.entities.bi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TotalTransactions")
public class TotalTransactions {
    @Id
    @Column(name = "totalRevenues")
    private int totalRevenues;
    @Column(name = "totalExpenses")
    private int totalExpenses;
    @Column(name = "totalProfit")
    private int totalProfit;
    @Column(name = "lastHourRevenues")
    private int lastHourRevenues;
    @Column(name = "lastHourExpenses")
    private int lastHourExpenses;
    @Column(name = "lastHourProfit")
    private int lastHourProfits;

    public TotalTransactions(){
        super();
    }

    public TotalTransactions(int totalRevenues, int totalExpenses, int totalProfit, int lastHourRevenues, int lastHourExpenses, int lastHourProfits) {
        this.totalRevenues = totalRevenues;
        this.totalExpenses = totalExpenses;
        this.totalProfit = totalProfit;
        this.lastHourRevenues = lastHourRevenues;
        this.lastHourExpenses = lastHourExpenses;
        this.lastHourProfits = lastHourProfits;
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
}
