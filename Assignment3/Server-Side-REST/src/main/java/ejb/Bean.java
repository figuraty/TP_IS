package ejb;

import data.DataTransactionManager;
import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import data.entities.Country;
import data.entities.Item;
import data.entities.bi.ItemsTransactions;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@LocalBean
@Stateless
public class Bean {
    public Bean() { }

    public void addCountry(String country) {
        DataTransactionManager.addCountry(country);
    }

    public List<CountryDTO> listCountries(){
        List<Country> countryList = DataTransactionManager.listCountries();

        List<CountryDTO> countryDTOList = new ArrayList<>();
        for(Country country : countryList){
            countryDTOList.add(new CountryDTO(country.getName()));
        }
        return countryDTOList;
    }

    public void addItem(String name, int price) {
        DataTransactionManager.addItem(name, price);
    }

    public List<ItemDTO> listItems() {
        List<Item> itemList = DataTransactionManager.listItems();

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(Item item : itemList){
            itemDTOList.add(new ItemDTO(item.getName(), item.getPrice()));
        }
        return itemDTOList;
    }

    public Map<String, Integer> getItemsRevenues() {
        List<ItemsTransactions> itemsTransactions = DataTransactionManager.getItemsRevenues();
        Map<String, Integer> hashMap = new HashMap<>();
        for(ItemsTransactions item : itemsTransactions){
            hashMap.put(item.getItemName(), item.getRevenues());
        }
        return hashMap;
    }

    public Map<String, Integer> getItemsExpenses() {
        List<ItemsTransactions> itemsTransactions = DataTransactionManager.getItemsExpenses();
        Map<String, Integer> hashMap = new HashMap<>();
        for(ItemsTransactions item : itemsTransactions){
            hashMap.put(item.getItemName(), item.getExpenses());
        }
        return hashMap;
    }

    public Map<String, Integer> getItemsProfits() {
        List<ItemsTransactions> itemsTransactions = DataTransactionManager.getItemsProfits();
        Map<String, Integer> hashMap = new HashMap<>();
        for(ItemsTransactions item : itemsTransactions){
            hashMap.put(item.getItemName(), item.getProfits());
        }
        return hashMap;
    }

    public int getTotalRevenues() {
        String  stringTotalRevenues = DataTransactionManager.getTotalRevenues();
        int totalRevenues = Integer.parseInt(stringTotalRevenues);
        return totalRevenues;
    }

    public int getTotalExpenses() {
        String stringTotalExpenses = DataTransactionManager.getTotalExpenses();
        int totalExpenses = Integer.parseInt(stringTotalExpenses);
        return totalExpenses;
    }

    public int getTotalProfits() {
        String stringTotalProfits = DataTransactionManager.getTotalProfits();
        int totalProfits = Integer.parseInt(stringTotalProfits);
        return totalProfits;
    }

    public String getAvgAmountSpentEachPurchase() {
        return null;
    }

    public String getItemHighestProfit() {
        return null;
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

    public String getCountryHighestSalesPerItem() {
        return null;
    }


}
