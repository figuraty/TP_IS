import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class parserJSON {

    public static List<CountryDTO> listCountries(String unparsedJSON){
        Gson gson = new Gson();
        CountryDTO[] countries = gson.fromJson(unparsedJSON, CountryDTO[].class);
        List<CountryDTO> countryList = Arrays.asList(countries);
        return countryList;
    }

    public static List<ItemDTO> listItems(String unparsedJSON) {
        Gson gson = new Gson();
        ItemDTO[] items = gson.fromJson(unparsedJSON, ItemDTO[].class);
        List<ItemDTO> itemList = Arrays.asList(items);
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
