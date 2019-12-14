package ejb;

import data.DataTransactionManager;
import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import data.dtos.ItemTransactionsDTO;
import data.entities.DBEntity;
import data.entities.ItemTransactions;

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
        List<ItemTransactions> itemTransactions = DataTransactionManager.getItemsRevenues();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (ItemTransactions itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getItemName(), String.valueOf(itemTransaction.getRevenues()), null, null, null, null));
        }
        return itemTransactionsDTOS;
    }

    public List<ItemTransactionsDTO> getItemsExpenses() {
        List<ItemTransactions> itemTransactions = DataTransactionManager.getItemsExpenses();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (ItemTransactions itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getItemName(), null, String.valueOf(itemTransaction.getExpenses()), null, null, null));
        }
        return itemTransactionsDTOS;
    }

    public List<ItemTransactionsDTO> getItemsProfits() {
        List<ItemTransactions> itemTransactions = DataTransactionManager.getItemsProfits();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (ItemTransactions itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getItemName(), null, null, String.valueOf(itemTransaction.getProfits()), null, null));
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
        List<ItemTransactions> itemTransactions = DataTransactionManager.getItemAvgAmountEachPurchase();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (ItemTransactions itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getItemName(), null, null, null, String.valueOf(itemTransaction.getAvgPurchaseAmount()), null));
        }
        return itemTransactionsDTOS;
    }

    public int getTotalAvgAmountEachPurchase() {
        String stringTotalAvgAmountEachPurchase = DataTransactionManager.getTotalAvgAmountEachPurchase();
        return stringTotalAvgAmountEachPurchase != null ? Integer.parseInt(stringTotalAvgAmountEachPurchase) : -1;
    }

    public String getItemHighestProfit() {
        return DataTransactionManager.getItemHighestProfit();
    }

    public int getTotalRevenuesLastHour() {
        String stringTotalRevenuesLastHour = DataTransactionManager.getTotalRevenuesLastHour();
        int totalRevenuesLastHour = Integer.parseInt(stringTotalRevenuesLastHour);
        return totalRevenuesLastHour;
    }

    public int getTotalExpensesLastHour() {
        String stringTotalExpensesLastHour = DataTransactionManager.getTotalExpensesLastHour();
        int totalExpensesLastHour = Integer.parseInt(stringTotalExpensesLastHour);
        return totalExpensesLastHour;
    }

    public int getTotalProfitsLastHour() {
        String stringTotalProfitsLastHour = DataTransactionManager.getTotalProfitsLastHour();
        int totalProfitsLastHour = Integer.parseInt(stringTotalProfitsLastHour);
        return totalProfitsLastHour;
    }

    public List<ItemTransactionsDTO> getCountryHighestSalesPerItem() {
        List<ItemTransactions> itemTransactions = DataTransactionManager.getCountryHighestSalesPerItem();
        List<ItemTransactionsDTO> itemTransactionsDTOS = new ArrayList<>();
        for (ItemTransactions itemTransaction : itemTransactions) {
            itemTransactionsDTOS.add(new ItemTransactionsDTO(itemTransaction.getItemName(), String.valueOf(itemTransaction.getRevenues()), null, null, null, itemTransaction.getHighestSalesCountry()));
        }
        return itemTransactionsDTOS;
    }


}
