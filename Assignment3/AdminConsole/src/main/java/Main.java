import org.apache.commons.lang3.SystemUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String applicationPath =  "http://localhost:8080/play-REST-server/webapi/project3webservices";

    public static void main(String[] args) {
        initialMenu();
    }

    private static void clearConsole(){
        try {
            if(SystemUtils.IS_OS_LINUX){
                Runtime.getRuntime().exec("clear");
            }
            else{
                Runtime.getRuntime().exec("cls");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void initialMenu() {

        while (true){
            System.out.println("1. Add country");
            System.out.println("2. List countries");
            System.out.println("3. Add item");
            System.out.println("4. List items");
            System.out.println("5. Get revenue per item");
            System.out.println("6. Get expenses per item");
            System.out.println("7. Get profit per item");
            System.out.println("8. Get total revenues");
            System.out.println("9. Get total expenses");
            System.out.println("10. Get total profit");
            System.out.println("11. Get average amount spent in each purchase (separated by item)");
            System.out.println("12. Get average amount spent in each purchase (aggregated for all items)");
            System.out.println("13. Get the item with the highest profit of all (only one if there is a tie)");
            System.out.println("14. Get the total revenue in the last hour 1 (use a tumbling time window)");
            System.out.println("15. Get the total expenses in the last hour (use a tumbling time window)");
            System.out.println("16. Get the total profits in the last hour (use a tumbling time window)");
            System.out.println("17. Get the name of the country with the highest sales per item. Include the value of such sales");

            System.out.println("Option: ");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    listCountries();
                    break;
                case 3:
                    addItem();
                    break;
                case 4:
                    listItems();
                    break;
                case 5:
                    getItemsRevenues();
                    break;
                case 6:
                    getItemsExpenses();
                    break;
                case 7:
                    getItemsProfits();
                    break;
                case 8:
                    getTotalRevenues();
                    break;
                case 9:
                    getTotalExpenses();
                    break;
                case 10:
                    getTotalProfits();
                    break;
                case 11:
                    getItemAvgAmountEachPurchase();
                    break;
                case 12:
                    getTotalAvgAmountEachPurchase();
                    break;
                case 13:
                    getItemHighestProfit();
                    break;
                case 14:
                    getTotalRevenuesLastHour();
                    break;
                case 15:
                    getTotalExpensesLastHour();
                    break;
                case 16:
                    getTotalProfitsLastHour();
                    break;
                case 17:
                    getCountryHighestSalesPerItem();
                    break;
            }
            System.out.println("\n");
        }
    }

    private static void addCountry() {
        //Main.clearConsole();
        System.out.println("[Add Country]\n");

        System.out.println(" - Name: ");

        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();

        if(countryName.equals("") || countryName.equals(" ")){
            System.out.println("[ERROR] Insert a valid country name.");
        }
        else {
            String url = Main.applicationPath + "/addcountry?name=" + countryName;
            boolean isValid = OperationsHTTP.HttpRequestPost(url);
            if (!isValid) {
                System.out.println("[ERROR] This country is already in the database.");
            } else {
                System.out.println("\nCountry [" + countryName + "] added to the database");
            }
        }
        Main.pressAnyKeyToContinue();
    }

    public static void listCountries() {
        String unparsedJSON;
        List<Country> countryList;
        String url = Main.applicationPath + "/listcountries";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        if(unparsedJSON == null){
            System.out.println("There are no countries in the database");
        }
        else {
            countryList = parserJSON.listCountries(unparsedJSON);
            System.out.println("[Countries]: ");
            for (int i = 0; i < countryList.size(); i++) {
                System.out.println(" - " + countryList.get(i).getName());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void addItem() {
        //Main.clearConsole();
        String itemName;
        int itemPrice;

        System.out.println("[Add Item]\n");

        System.out.println(" - Name: ");
        Scanner scanner = new Scanner(System.in);
        itemName = scanner.nextLine();
        if (itemName.equals("") || itemName.equals(" ")){
            System.out.println("[ERROR] Insert a valid item name");
        }
        else {
            System.out.println(" - Price: ");
            try {
                itemPrice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("[ERROR] Type an integer");
                Main.pressAnyKeyToContinue();
                return;
            }
            String url = Main.applicationPath + "/additem?name=" + itemName + "&price=" + itemPrice;
            OperationsHTTP.HttpRequestPost(url);
            System.out.println("\nItem [" + itemName + "] worth [" + itemPrice + "$] added to the database");
        }
        Main.pressAnyKeyToContinue();

    }

    private static void listItems() {
        String unparsedJSON;
        List<Item> itemList;
        String url = Main.applicationPath + "/listitems";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemList = parserJSON.listItems(unparsedJSON);

        if(itemList.size() == 0){
            System.out.println("There are no items in the database");
        }
        else {
            System.out.println("[Items]");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println(" - Name: " + itemList.get(i).getName() + ", Price: " + itemList.get(i).getPrice());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void getItemsRevenues() {
        String unparsedJSON;
        List<ItemTransactions> itemTransactions;
        String url = Main.applicationPath + "/itemsrevenues";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemTransactions = parserJSON.listItemTransactions(unparsedJSON);

        if(itemTransactions.size() == 0){
            System.out.println("There are no item transactions in the database");
        }
        else {
            System.out.println("[Items]");
            for (ItemTransactions itemTransaction : itemTransactions) {
                System.out.println(" - Name: " + itemTransaction.getItemName() + ", Revenues: " + itemTransaction.getRevenues());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void getItemsExpenses() {
        String unparsedJSON;
        List<ItemTransactions> itemTransactions;
        String url = Main.applicationPath + "/itemsexpenses";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemTransactions = parserJSON.listItemTransactions(unparsedJSON);

        if(itemTransactions.size() == 0){
            System.out.println("There are no item transactions in the database");
        }
        else {
            System.out.println("[Items]");
            for (ItemTransactions itemTransaction : itemTransactions) {
                System.out.println(" - Name: " + itemTransaction.getItemName() + ", Expenses: " + itemTransaction.getExpenses());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void getItemsProfits() {
        String unparsedJSON;
        List<ItemTransactions> itemTransactions;
        String url = Main.applicationPath + "/itemsprofits";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemTransactions = parserJSON.listItemTransactions(unparsedJSON);

        if(itemTransactions.size() == 0){
            System.out.println("There are no item transactions in the database");
        }
        else {
            System.out.println("[Items]");
            for (ItemTransactions itemTransaction : itemTransactions) {
                System.out.println(" - Name: " + itemTransaction.getItemName() + ", Profits: " + itemTransaction.getProfits());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalRevenues(){
        String unparsedJSON;
        int totalRevenues;
        String url = Main.applicationPath + "/totalrevenues";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalRevenues = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Revenues]: " + totalRevenues);
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalExpenses(){
        String unparsedJSON;
        int totalExpenses;
        String url = Main.applicationPath + "/totalexpenses";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalExpenses = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Expenses]: " + totalExpenses);
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalProfits(){
        String unparsedJSON;
        int totalProfit;
        String url = Main.applicationPath + "/totalprofits";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalProfit = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Profits]: " + totalProfit);
        Main.pressAnyKeyToContinue();
    }

    private static void getItemAvgAmountEachPurchase() {
        String unparsedJSON;
        List<ItemTransactions> itemTransactions;
        String url = Main.applicationPath + "/avgamountperitem";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemTransactions = parserJSON.listItemTransactions(unparsedJSON);

        if(itemTransactions.size() == 0){
            System.out.println("There are no item transactions in the database");
        }
        else {
            System.out.println("[Items]");
            for (ItemTransactions itemTransaction : itemTransactions) {
                System.out.println(" - Name: " + itemTransaction.getItemName() + ", Average Amount Purchases: " + itemTransaction.getAvgPurchaseAmount());
            }
        }
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalAvgAmountEachPurchase() {
        String unparsedJSON;
        int totalAvgAmountEachPurchase;
        String url = Main.applicationPath + "/avgamountspenteachpurchase";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalAvgAmountEachPurchase = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Average Amount of Each Purchases]: " + totalAvgAmountEachPurchase);
        Main.pressAnyKeyToContinue();
    }

    private static void getItemHighestProfit() {
        String unparsedJSON;
        String itemHighestProfit;
        String url = Main.applicationPath + "/itemhighestprofit";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemHighestProfit = parserJSON.stringParser(unparsedJSON);

        System.out.println("[Item with Highest Profit]: " + itemHighestProfit);
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalRevenuesLastHour(){
        String unparsedJSON;
        int totalRevenueLastHour;
        String url = Main.applicationPath + "/totalrevenueslasthour";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalRevenueLastHour = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Revenues Last Hour]: " + totalRevenueLastHour);
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalExpensesLastHour(){
        String unparsedJSON;
        int totalExpensesLastHour;
        String url = Main.applicationPath + "/totalexpenseslasthour";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalExpensesLastHour = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Expenses Last Hour]: " + totalExpensesLastHour);
        Main.pressAnyKeyToContinue();
    }

    private static void getTotalProfitsLastHour() {
        String unparsedJSON;
        int totalProfitsLastHour;
        String url = Main.applicationPath + "/totalprofitslasthour";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        totalProfitsLastHour = parserJSON.intParser(unparsedJSON);

        System.out.println("[Total Profits Last Hour]: " + totalProfitsLastHour);
        Main.pressAnyKeyToContinue();
    }

    private static void getCountryHighestSalesPerItem(){
        String unparsedJSON;
        List<ItemTransactions> itemTransactions;
        String url = Main.applicationPath + "/countryhighestsalesperitem";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemTransactions = parserJSON.listItemTransactions(unparsedJSON);

        if(itemTransactions.size() == 0){
            System.out.println("There are no item transactions in the database");
        }
        else {
            System.out.println("[Items]");
            for(ItemTransactions itemTransaction : itemTransactions){
                System.out.println(" - Name: " + itemTransaction.getItemName() + ", Country with Highest Sales: " + itemTransaction.getHighestSalesCountry() + " , Amount: " + itemTransaction.getRevenues());
            }
        }
        Main.pressAnyKeyToContinue();
    }
}
