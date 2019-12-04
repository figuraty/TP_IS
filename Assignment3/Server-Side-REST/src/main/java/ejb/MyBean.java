package ejb;

import data.*;
import org.apache.kafka.common.metrics.stats.Count;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@LocalBean
@Stateless
public class MyBean {

    ListCourses lc;
    /**
     * Default constructor.
     */
    public MyBean() {
    }


    public ListCourses getListCourses() {
        return this.lc;
    }

    public Course getCourse(int id) {
        return this.lc.get(id);
    }


    // apagar daqui para cima
    public void addCountry(String country) {
        DataTransactionManager.addCountry(country);
    }

    public ListCountries listCountries() {
        List<Country> countries = DataTransactionManager.listCountries();
        List<CountryXML> listCountriesAux = new ArrayList<>();

        for(Country country : countries){
            listCountriesAux.add(new CountryXML(country.getName()));
        }
        System.out.println(listCountriesAux);
        ListCountries listCountries = new ListCountries(listCountriesAux);
        return listCountries;
    }

    public String addItem(String name) {
        return null;
    }

    public String listItems() {
        return null;
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
