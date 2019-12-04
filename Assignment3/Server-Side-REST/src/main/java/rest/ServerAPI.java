package rest;

import data.Course;
import data.ListCountriesXML;
import data.ListCourses;
import ejb.MyBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/project3webservices")
@RequestScoped
public class ServerAPI {

    @Inject
    MyBean db;

    public ServerAPI() throws NamingException {
        System.out.println("Server-Side REST created db = " + this.db);
    }

    //1. Add countries to the database. To simplify countries cannot be deleted and optionally not changed.
    @GET
    @Path("addcountry")
    public void addCountry(@QueryParam("country") String country) {
        db.addCountry(country);
    }

    //2. List countries from the database.
    @GET
    @Path("listcountries")
    @Produces({MediaType.APPLICATION_JSON})
    public ListCountriesXML listCountries() {
        return db.listCountries();
    }

    //Add items for sale in the shop to the database. Again, these cannot be deleted, but may be changed if students wish.
    @PUT
    @Path("additem")
    public String addItem(@QueryParam("name") String name) {
        return db.addItem(name);
    }

    //4. List items from the database.
    @GET
    @Path("listitems")
    @Produces({MediaType.APPLICATION_XML})
    public String listItems() {
        return db.listItems();
    }

    //5. Get the revenue per item.
    @GET
    @Path("getitemrevenue")
    @Produces({MediaType.APPLICATION_JSON})
    public String getItemRevenue(@QueryParam("name") String name) {
        return db.getItemRevenue(name);
    }

    //6. Get the expenses per item.
    @GET
    @Path("getitemexpenses")
    @Produces({MediaType.APPLICATION_XML})
    public String getItemExpenses(@QueryParam("name") String name) {
        return db.getItemExpenses(name);
    }

    //7. Get the profit per item.
    @GET
    @Path("getitemprofit")
    @Produces({MediaType.APPLICATION_XML})
    public String getItemProfit() {
        return db.getItemProfit();
    }

    //8. Get the total revenues.
    @GET
    @Path("gettotalrevenues")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalRevenues() {
        return db.getTotalRevenues();
    }

    //9. Get the total expenses.
    @GET
    @Path("gettotalexpenses")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalExpenses() {
        return db.getTotalExpenses();
    }

    //10. Get the total profit.
    @GET
    @Path("gettotalprofit")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalProfit() {
        return db.getTotalProfit();
    }

    //11. Get the average amount spent in each purchase (separated by item).
    @GET
    @Path("getavgamountperitem")
    @Produces({MediaType.APPLICATION_XML})
    public String getAvgAmountSpentPerItem() {
        return "";
    }

    //12. Get the average amount spent in each purchase (aggregated for all items).
    @GET
    @Path("getavgamountspenteachpurchase")
    @Produces({MediaType.APPLICATION_XML})
    public String getAvgAmountSpentEachPurchase() {
        return db.getAvgAmountSpentEachPurchase();
    }

    //13. Get the item with the highest profit of all (only one if there is a tie).
    @GET
    @Path("getitemhighestprofit")
    @Produces({MediaType.APPLICATION_XML})
    public String getItemHighestProfit() {
        return db.getItemHighestProfit();
    }

    //14. Get the total revenue in the last hour 1 (use a tumbling time window).
    @GET
    @Path("gettotalrevenuelasthour")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalRevenueLastHour() {
        return "";
    }

    //15. Get the total expenses in the last hour (use a tumbling time window)
    @GET
    @Path("gettotalexpenseslasthour")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalExpensesLastHour() {
        return db.getTotalExpensesLastHour();
    }

    //16. Get the total profits in the last hour (use a tumbling time window).
    @GET
    @Path("gettotalprofitslasthour")
    @Produces({MediaType.APPLICATION_XML})
    public String getTotalProfitsLastHour() {
        return db.getTotalProfitsLastHour();
    }

    //17. Get the name of the country with the highest sales per item. Include the value of such sales.
    @GET
    @Path("getcountryhighestsalesperitem")
    @Produces({MediaType.APPLICATION_XML})
    public String getCountryHighestSalesPerItem() {
        return db.getCountryHighestSalesPerItem();
    }


    // http://localhost:8080/play-REST-server/webapi/project3webservices/gettext
    @GET
    @Path("gettext")
    @Produces({MediaType.TEXT_PLAIN})
    public String getText() {
        return "Hello World!";
    }

    // http://localhost:8080/play-REST-server/webapi/project3webservices/getmaterials
    @GET
    @Path("getmaterials")
    @Produces({MediaType.APPLICATION_XML})
    public ListCourses getAllMaterials() {
        return db.getListCourses();
    }


    // http://localhost:8080/play-REST-server/webapi/project3webservices/getstudents?id=1
    @GET
    @Path("getstudents")
    @Produces({MediaType.APPLICATION_JSON})
    public Course getAllStudents(@QueryParam("courseid") int id) {
        return db.getCourse(id);
    }
}