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
public class MyBean {
    public MyBean() { }

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

    public String getTotalRevenues() {
        return null;
    }

    public String getTotalExpenses() {
        return null;
    }

    public String getTotalProfit() {
        return null;
    }

    public String getAvgAmountSpentEachPurchase() {
        return null;
    }

    public String getItemHighestProfit() {
        return null;
    }

    public String getTotalExpensesLastHour() {
        return null;
    }

    public String getTotalProfitsLastHour() {
        return null;
    }

    public String getCountryHighestSalesPerItem() {
        return null;
    }
}
