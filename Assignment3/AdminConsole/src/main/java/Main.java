import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        initialMenu();
    }

    public static void initialMenu() {

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
                try {
                    listCountries();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    }

    private static void HttpRequestGet(String Url) {
        BufferedReader reader;
        java.lang.String line;
        StringBuffer responseContent = new StringBuffer();
        HttpURLConnection connection = null;

        try {

            URL url = new URL("http://localhost:8080/play-REST-server/webapi/project3webservices/listcountries");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            //int status = connection.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            parse(responseContent.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    private static String parse(String responseBody){
        JSONArray jsonArray = new JSONArray(responseBody);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            System.out.println(name);
        }

        return null;
    }
    private static void addCountry() {
    }

    public static void listCountries() throws IOException {
        String url = "http://localhost:8080/play-REST-server/webapi/project3webservices/listcountries";
        HttpRequestGet(url);
    }

    private static void addItem() {
    }

    private static void listItems() {
    }
}
