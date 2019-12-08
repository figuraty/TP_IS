package ejb;

import data.*;
import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import data.entities.Country;
import data.entities.Item;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@LocalBean
@Stateless
public class Bean {
    public Bean() { }

    public void addCountry(String country) {
        DataTransactionManager.addCountry(country);
    }

    public List<CountryDTO> listCountries(){
        List<Country> listcountries = DataTransactionManager.listCountries();

        List<CountryDTO> countryDTOList = new ArrayList<>();
        for(Country country : listcountries){
            countryDTOList.add(new CountryDTO(country.getName()));
        }
        return countryDTOList;
    }

    public void addItem(String name, int price) {
        DataTransactionManager.addItem(name, price);
    }

    public List<ItemDTO> listItems() {
        List<Item> listitems = DataTransactionManager.listItems();

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(Item item : listitems){
            itemDTOList.add(new ItemDTO(item.getName(), item.getPrice()));
        }
        return itemDTOList;
    }

    public String getItemRevenue(String name) {
        return null;
    }

    public String getItemExpenses(String name) {
        return null;
    }

    public String getItemProfit() {
        return null;
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

    public int getTotalProfit() {
        String stringTotalProfit = DataTransactionManager.getTotalProfit();
        int totalProfit = Integer.parseInt(stringTotalProfit);
        return totalProfit;
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
