import com.google.gson.Gson;
import org.apache.kafka.common.protocol.types.Field;

import java.util.ArrayList;
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

    public static int intParser(String unparsedJSON) {
        Gson gson = new Gson();
        int integer = gson.fromJson(unparsedJSON, int.class);
        return integer;
    }

    public static String stringParser(String unparsedJSON){
        Gson gson = new Gson();
        String string = gson.fromJson(unparsedJSON, String.class);
        return string;
    }

    public static List<ItemTransactions> listItemTransactions(String unparsedJSON){
        Gson gson = new Gson();
        ItemTransactions[] itemTransactions = gson.fromJson(unparsedJSON, ItemTransactions[].class);
        List<ItemTransactions> itemTransactionsList = Arrays.asList(itemTransactions);
        return itemTransactionsList;
    }
}
