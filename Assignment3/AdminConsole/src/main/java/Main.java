import com.google.gson.Gson;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

    private static void pressAnyKeyToContinue()
    {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
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
            System.out.println("13. Get the item with the highest profit of all (only one if there is a tie).");
            System.out.println("14. Get the total revenue in the last hour 1 (use a tumbling time window).");
            System.out.println("15. Get the total expenses in the last hour (use a tumbling time window)");
            System.out.println("16. Get the total profits in the last hour (use a tumbling time window).");
            System.out.println("17. Get the name of the country with the highest sales per item. Include the value of such sales.");

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

        String url = Main.applicationPath + "/addcountry?name=" + countryName;
        OperationsHTTP.HttpRequestPost(url);
        System.out.println("\nCountry [" + countryName + "] added to the database");
        Main.pressAnyKeyToContinue();
    }

    public static void listCountries() {
        String unparsedJSON;
        List<Country> countryList;

        String url = Main.applicationPath + "/listcountries";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        countryList = parserJSON.listCountries(unparsedJSON);
        System.out.println("[Countries]: ");
        for(int i = 0; i < countryList.size(); i++){
            System.out.println(" - " + countryList.get(i).getName());
        }
//        System.out.println("\n");
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

        System.out.println(" - Price: ");
        try {
            itemPrice = scanner.nextInt();
        } catch (Exception e){
            System.out.println("[ERRO] Type an integer");
            Main.pressAnyKeyToContinue();
            return;
        }

        String url = Main.applicationPath + "/additem?name=" + itemName + "&price=" + itemPrice;
        OperationsHTTP.HttpRequestPost(url);
        System.out.println("\nItem [" + itemName + "] worth [" + itemPrice + "$] added to the database");
        Main.pressAnyKeyToContinue();

    }

    private static void listItems() {
        String unparsedJSON;
        List<Item> itemList;

        String url = Main.applicationPath + "/listitems";

        unparsedJSON = OperationsHTTP.HttpRequestGet(url);
        itemList = parserJSON.listItems(unparsedJSON);

        System.out.println("[Items]");
        for(int i = 0; i < itemList.size(); i++){
            System.out.println(" - Name: " + itemList.get(i).getName() + ", Price: " + itemList.get(i).getPrice());
        }
//        System.out.println("\n");
        Main.pressAnyKeyToContinue();
    }
}
