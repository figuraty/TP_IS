public class ItemTransactions {
    private String itemName;
    private String revenues;
    private String expenses;
    private String profits;
    private String avgPurchaseAmount;
    private String highestSalesCountry;

    public ItemTransactions(String itemName, String revenues, String expenses, String profits, String avgPurchaseAmount, String highestSalesCountry) {
        this.itemName = itemName;
        this.revenues = revenues;
        this.expenses = expenses;
        this.profits = profits;
        this.avgPurchaseAmount = avgPurchaseAmount;
        this.highestSalesCountry = highestSalesCountry;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRevenues() {
        return revenues;
    }

    public String getExpenses() {
        return expenses;
    }

    public String getProfits() {
        return profits;
    }

    public String getAvgPurchaseAmount() {
        return avgPurchaseAmount;
    }

    public String getHighestSalesCountry() {
        return highestSalesCountry;
    }
}
