package rest;

import data.dtos.CountryDTO;
import data.dtos.ItemDTO;
import data.dtos.ItemTransactionsCountryDTO;
import data.dtos.ItemTransactionsDTO;
import ejb.Bean;

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
    Bean bean;

    public ServerAPI() {
    }

    //1. Add countries to the database. To simplify countries cannot be deleted and optionally not changed.
    @GET
    @Path("addcountry")
    public void addCountry(@QueryParam("name") String name) {
        bean.addCountry(name);
    }

    //2. List countries from the database.
    @GET
    @Path("listcountries")
    @Produces({MediaType.APPLICATION_JSON})
    public List<CountryDTO> listCountries() {
        return bean.listCountries();
    }

    //Add items for sale in the shop to the database. Again, these cannot be deleted, but may be changed if students wish.
    @GET
    @Path("additem")
    public void addItem(@QueryParam("name") String name) {
        bean.addItem(name);
    }

    //4. List items from the database.
    @GET
    @Path("listitems")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemDTO> listItems() {
        return bean.listItems();
    }

    //5. Get the revenue per item.
    @GET
    @Path("itemsrevenues")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTransactionsDTO> getItemsRevenue() {
        return bean.getItemsRevenues();
    }

    //6. Get the expenses per item.
    @GET
    @Path("itemsexpenses")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTransactionsDTO> getItemExpenses() {
        return bean.getItemsExpenses();
    }

    //7. Get the profit per item.
    @GET
    @Path("itemsprofits")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTransactionsDTO> getItemProfits() {
        return bean.getItemsProfits();
    }

    //8. Get the total revenues.
    @GET
    @Path("totalrevenues")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalRevenues() {
        return bean.getTotalRevenues();
    }

    //9. Get the total expenses.
    @GET
    @Path("totalexpenses")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalExpenses() {
        return bean.getTotalExpenses();
    }

    //10. Get the total profit.
    @GET
    @Path("totalprofits")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalProfits() {
        return bean.getTotalProfits();
    }

    //11. Get the average amount spent in each purchase (separated by item).
    @GET
    @Path("avgamountperitem")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTransactionsDTO> getItemAvgAmountEachPurchase() {
        return bean.getItemAvgAmountEachPurchase();
    }

    //12. Get the average amount spent in each purchase (aggregated for all items).
    @GET
    @Path("avgamountspenteachpurchase")
    @Produces({MediaType.APPLICATION_JSON})
    public double getTotalAvgAmountEachPurchase () {
        return bean.getTotalAvgAmountEachPurchase();
    }

    //13. Get the item with the highest profit of all (only one if there is a tie).
    @GET
    @Path("itemhighestprofit")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemHighestProfit() {
        return bean.getItemHighestProfit();
    }

    //14. Get the total revenue in the last hour 1 (use a tumbling time window).
    @GET
    @Path("totalrevenueslasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalRevenuesLastHour() {
        return bean.getTotalRevenuesLastHour();
    }

    //15. Get the total expenses in the last hour (use a tumbling time window)
    @GET
    @Path("totalexpenseslasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalExpensesLastHour() {
        return bean.getTotalExpensesLastHour();
    }

    //16. Get the total profits in the last hour (use a tumbling time window).
    @GET
    @Path("totalprofitslasthour")
    @Produces({MediaType.APPLICATION_JSON})
    public int getTotalProfitsLastHour() {
        return bean.getTotalProfitsLastHour();
    }

    //17. Get the name of the country with the highest sales per item. Include the value of such sales.
    @GET
    @Path("countryhighestsalesperitem")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ItemTransactionsCountryDTO> getCountryHighestSalePerItem() {
        return bean.getCountryHighestSalePerItem();
    }
}