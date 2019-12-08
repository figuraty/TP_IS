package rest;

import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import ejb.MyBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/project3webservices")
@RequestScoped
public class ServerAPI {

    @Inject
    MyBean my;

    public ServerAPI() {
    }

    //1. Add countries to the database. To simplify countries cannot be deleted and optionally not changed.
    @GET
    @Path("addcountry")
    public void addCountry(@QueryParam("name") String name) {
        my.addCountry(name);
    }

    //2. List countries from the database.
    @GET
    @Path("listcountries")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CountryDTO> listCountries() {
        return my.listCountries();
    }

    //Add items for sale in the shop to the database. Again, these cannot be deleted, but may be changed if students wish.
    @GET
    @Path("additem")
    public void addItem(@QueryParam("name") String name, @QueryParam("price") int price) {
        my.addItem(name, price);
    }

    //4. List items from the database.
    @GET
    @Path("listitems")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemDTO> listItems() {
        return my.listItems();
    }

    //5. Get the revenue per item.
    @GET
    @Path("getitemrevenue")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemRevenue(@QueryParam("name") String name) {
        return my.getItemRevenue(name);
    }

    //6. Get the expenses per item.
    @GET
    @Path("getitemexpenses")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemExpenses(@QueryParam("name") String name) {
        return my.getItemExpenses(name);
    }

    //7. Get the profit per item.
    @GET
    @Path("getitemprofit")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemProfit() {
        return my.getItemProfit();
    }

    //8. Get the total revenues.
    @GET
    @Path("gettotalrevenues")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalRevenues() {
        return my.getTotalRevenues();
    }

    //9. Get the total expenses.
    @GET
    @Path("gettotalexpenses")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalExpenses() {
        return my.getTotalExpenses();
    }

    //10. Get the total profit.
    @GET
    @Path("gettotalprofit")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalProfit() {
        return my.getTotalProfit();
    }

    //11. Get the average amount spent in each purchase (separated by item).
    @GET
    @Path("getavgamountperitem")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAvgAmountSpentPerItem() {
        return "";
    }

    //12. Get the average amount spent in each purchase (aggregated for all items).
    @GET
    @Path("getavgamountspenteachpurchase")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAvgAmountSpentEachPurchase() {
        return my.getAvgAmountSpentEachPurchase();
    }

    //13. Get the item with the highest profit of all (only one if there is a tie).
    @GET
    @Path("getitemhighestprofit")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemHighestProfit() {
        return my.getItemHighestProfit();
    }

    //14. Get the total revenue in the last hour 1 (use a tumbling time window).
    @GET
    @Path("gettotalrevenuelasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalRevenueLastHour() {
        return "";
    }

    //15. Get the total expenses in the last hour (use a tumbling time window)
    @GET
    @Path("gettotalexpenseslasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalExpensesLastHour() {
        return my.getTotalExpensesLastHour();
    }

    //16. Get the total profits in the last hour (use a tumbling time window).
    @GET
    @Path("gettotalprofitslasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTotalProfitsLastHour() {
        return my.getTotalProfitsLastHour();
    }

    //17. Get the name of the country with the highest sales per item. Include the value of such sales.
    @GET
    @Path("getcountryhighestsalesperitem")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCountryHighestSalesPerItem() {
        return my.getCountryHighestSalesPerItem();
    }
}