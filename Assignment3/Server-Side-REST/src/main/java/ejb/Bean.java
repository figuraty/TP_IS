package ejb;

import data.DataTransactionManager;
import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import data.dtos.ItemTransactionsCountryDTO;
import data.dtos.ItemTransactionsDTO;
import data.entities.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@LocalBean
@Stateless
public class Bean {
    public Bean() {
    }

    public void addCountry(String country) {
        DataTransactionManager.addCountry(country);
    }

    public List<CountryDTO> listCountries() {
        List<DBEntity> countryList = DataTransactionManager.listCountries();

        List<CountryDTO> countryDTOList = new ArrayList<>();
        for (DBEntity country : countryList) {
            countryDTOList.add(new CountryDTO(country.getName()));
        }
        return countryDTOList;
    }

    public void addItem(String name) {
        DataTransactionManager.addItem(name);
    }

    public List<ItemDTO> listItems() {
        List<DBEntity> itemList = DataTransactionManager.listItems();

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (DBEntity item : itemList) {
            itemDTOList.add(new ItemDTO(item.getName()));
        }
        return itemDTOList;
    }

    public List<ItemTransactionsDTO> getItemsRevenues() {
        List<revenuesPerItem> itemTransactions = DataTransactionManager.getItemsRevenues();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (revenuesPerItem itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getName(), String.valueOf(itemTransaction.getValue())));
        }
        return itemTransactionsDTOS;
    }

    public List<ItemTransactionsDTO> getItemsExpenses() {
        List<expensesPerItem> itemTransactions = DataTransactionManager.getItemsExpenses();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (expensesPerItem itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getName(), String.valueOf(itemTransaction.getValue())));
        }
        return itemTransactionsDTOS;
    }

    public List<ItemTransactionsDTO> getItemsProfits() {
        List<profitPerItem> itemTransactions = DataTransactionManager.getItemsProfits();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (profitPerItem itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getName(), String.valueOf(itemTransaction.getValue())));
        }
        return itemTransactionsDTOS;
    }

    public int getTotalRevenues() {
        String stringTotalRevenues = DataTransactionManager.getTotalRevenues();
        return stringTotalRevenues != null ? Integer.parseInt(stringTotalRevenues) : -1;
    }

    public int getTotalExpenses() {
        String stringTotalExpenses = DataTransactionManager.getTotalExpenses();
        return stringTotalExpenses != null ? Integer.parseInt(stringTotalExpenses) : -1;
    }

    public int getTotalProfits() {
        String stringTotalProfits = DataTransactionManager.getTotalProfits();
        return stringTotalProfits != null ? Integer.parseInt(stringTotalProfits) : -1;
    }

    public List<ItemTransactionsDTO> getItemAvgAmountEachPurchase() {
        List<averageExpensesPerItem> itemTransactions = DataTransactionManager.getItemAvgAmountEachPurchase();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (averageExpensesPerItem itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getName(), String.valueOf(itemTransaction.getValue())));
        }
        return itemTransactionsDTOS;
    }

    public double getTotalAvgAmountEachPurchase() {
        String stringTotalAvgAmountEachPurchase = DataTransactionManager.getTotalAvgAmountEachPurchase();
        return stringTotalAvgAmountEachPurchase != null ? Double.parseDouble(stringTotalAvgAmountEachPurchase) : -1;
    }

    public String getItemHighestProfit() {
        return DataTransactionManager.getItemHighestProfit();
    }

    public int getTotalRevenuesLastHour() {
        String stringTotalRevenuesLastHour = DataTransactionManager.getTotalRevenuesLastHour();
        return stringTotalRevenuesLastHour != null ? Integer.parseInt(stringTotalRevenuesLastHour) : -1;
    }

    public int getTotalExpensesLastHour() {
        String stringTotalExpensesLastHour = DataTransactionManager.getTotalExpensesLastHour();
        return stringTotalExpensesLastHour != null ? Integer.parseInt(stringTotalExpensesLastHour) : -1;

    }

    public int getTotalProfitsLastHour() {
        String stringTotalProfitsLastHour = DataTransactionManager.getTotalProfitsLastHour();
        return stringTotalProfitsLastHour != null ? Integer.parseInt(stringTotalProfitsLastHour) : -1;
    }

    public List<ItemTransactionsCountryDTO> getCountryHighestSalePerItem() {
        List<countryHighestSalePerItem> itemTransactions = DataTransactionManager.getCountryHighestSalesPerItem();
        List<ItemTransactionsCountryDTO> itemTransactionsCountryDTOS = new ArrayList<>();
        for (countryHighestSalePerItem itemTransaction : itemTransactions) {
            itemTransactionsCountryDTOS.add(new ItemTransactionsCountryDTO(itemTransaction.getName(), String.valueOf(itemTransaction.getRevenues()), itemTransaction.getCountry()));
        }
        return itemTransactionsCountryDTOS;
    }


}
