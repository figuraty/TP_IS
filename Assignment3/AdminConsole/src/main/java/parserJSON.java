import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class parserJSON {

    public static List<Country> listCountries(String unparsedJSON){
        Gson gson = new Gson();
        Country[] countries = gson.fromJson(unparsedJSON, Country[].class);
        List<Country> countryList = Arrays.asList(countries);
        return countryList;
    }

    public static List<Item> listItems(String unparsedJSON) {
        Gson gson = new Gson();
        Item[] items = gson.fromJson(unparsedJSON, Item[].class);
        List<Item> itemList = Arrays.asList(items);
        return itemList;
    }
}
