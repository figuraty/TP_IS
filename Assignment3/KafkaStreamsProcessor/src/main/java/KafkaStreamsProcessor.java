import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaStreamsProcessor {

    public static void main(String[] args) throws InterruptedException, IOException {
        String purchasesTopic = "purchasesTopic";
        String salesTopic = "salesTopic";
        String revenuesPerItemTopic = "revenuesPerItem";
        String expensesPerItemTopic = "expensesPerItem";
        String profitPerItemTopic = "profitPerItem";
        String totalRevenuesTopic = "totalRevenues";
        String totalExpensesTopic = "totalExpenses";
        String totalProfitTopic = "totalProfit";
        String averageExpensesPerItemTopic = "averageExpensesPerItem";
        String totalAverageExpensesTopic = "totalAverageExpenses";
        String itemHighestProfitTopic = "itemHighestProfit";
        String totalRevenuesLastHourTopic = "totalRevenuesLastHour";
        String totalExpensesLastHourTopic = "totalExpensesLastHour";
        String totalProfitLastHourTopic = "totalProfitLastHour";
        String countryHighestSalesPerItemTopic = "countryHighestSalePerItem";


        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "IS-Assignment-3");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> salesData = builder.stream(salesTopic);
        KStream<String, String> purchasesData = builder.stream(purchasesTopic);

        KStream<String, String> profitData = salesData.outerJoin(purchasesData,
                KafkaStreamsProcessor::getProfitPerItem,
                JoinWindows.of(Duration.ofNanos(1)),
                Joined.with(
                        Serdes.String(), /* key */
                        Serdes.String(),   /* left value */
                        Serdes.String())  /* right value */
        );


        KTable<String, String> revenuesPerItem = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addRevenuesPerItem)
                .mapValues(KafkaStreamsProcessor::mapRevenuesPerItem);

        
        KTable<String, String> expensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addExpensesPerItem)
                .mapValues(KafkaStreamsProcessor::mapExpensesPerItem);


        KTable<String, String> profitPerItem = profitData.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addProfitPerItem)
                .mapValues(KafkaStreamsProcessor::mapProfitPerItem);


        KTable<String, String> totalRevenues = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupBy((k, v) -> "totalRevenues", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalRevenues)
                .mapValues(KafkaStreamsProcessor::mapTotalRevenues);


        KTable<String, String> totalExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupBy((k, v) -> "totalExpenses", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalExpenses)
                .mapValues(KafkaStreamsProcessor::mapTotalExpenses);
        

        KTable<String, String> totalProfit = profitData
                .groupBy((k, v) -> "totalProfit", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalProfit)
                .mapValues(KafkaStreamsProcessor::mapTotalProfit);
        
        
        KTable<String, String> averageExpensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::calculateAverageExpensesPerItem)
                .mapValues(KafkaStreamsProcessor::mapAverageExpensesPerItem);
        

        KTable<String, String> totalAverageExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupBy((k, v) -> "totalAverageExpenses", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::calculateTotalAverageExpenses)
                .mapValues(KafkaStreamsProcessor::mapTotalAverageExpenses);


        KTable<String, String> itemHighestProfit = profitData.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addProfitPerItem)
                .toStream()
                .groupBy((k, v) -> "itemHighestProfit", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::getItemHighestProfit)
                .mapValues(KafkaStreamsProcessor::mapItemHighestProfit);


        KTable<Windowed<String>, String> totalRevenuesLastHour = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupBy((k, v) -> "totalRevenuesLastHour", Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofHours(1L)))
                .reduce(KafkaStreamsProcessor::addTotalRevenuesLastHour)
                .mapValues(KafkaStreamsProcessor::mapTotalRevenuesLastHour);

        KTable<Windowed<String>, String> totalExpensesLastHour = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupBy((k, v) -> "totalExpensesLastHour", Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofHours(1L)))
                .reduce(KafkaStreamsProcessor::addTotalExpensesLastHour)
                .mapValues(KafkaStreamsProcessor::mapTotalExpensesLastHour);

        KTable<Windowed<String>, String> totalProfitLastHour = profitData
                .groupBy((k, v) -> "totalProfitLastHour", Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofHours(1L)))
                .reduce(KafkaStreamsProcessor::addTotalProfitLastHour)
                .mapValues(KafkaStreamsProcessor::mapTotalProfitLastHour);


        KTable<String, String> countryHighestSalesPerItem = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItemPerCountry)
                .groupBy((k, v) -> v.split(":")[4], Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addRevenuesPerCountry)
                .toStream()
                .groupBy((k, v) -> v.split(":")[4].split("-")[0], Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::getCountryHighestSales)
                .mapValues(KafkaStreamsProcessor::mapCountryHighestSales);


        revenuesPerItem.toStream().to(revenuesPerItemTopic, Produced.with(Serdes.String(), Serdes.String()));

        expensesPerItem.toStream().to(expensesPerItemTopic, Produced.with(Serdes.String(), Serdes.String()));

        profitPerItem.toStream().to(profitPerItemTopic, Produced.with(Serdes.String(), Serdes.String()));

        totalRevenues.toStream().to(totalRevenuesTopic, Produced.with(Serdes.String(), Serdes.String()));

        totalExpenses.toStream().to(totalExpensesTopic, Produced.with(Serdes.String(), Serdes.String()));

        totalProfit.toStream().to(totalProfitTopic, Produced.with(Serdes.String(), Serdes.String()));

        averageExpensesPerItem.toStream().to(averageExpensesPerItemTopic, Produced.with(Serdes.String(), Serdes.String()));

        totalAverageExpenses.toStream().to(totalAverageExpensesTopic, Produced.with(Serdes.String(), Serdes.String()));

        itemHighestProfit.toStream().to(itemHighestProfitTopic, Produced.with(Serdes.String(), Serdes.String()));

        totalRevenuesLastHour.toStream().to(totalRevenuesLastHourTopic, Produced.with(WindowedSerdes.sessionWindowedSerdeFrom(String.class), Serdes.String()));

        totalExpensesLastHour.toStream().to(totalExpensesLastHourTopic, Produced.with(WindowedSerdes.sessionWindowedSerdeFrom(String.class), Serdes.String()));

        totalProfitLastHour.toStream().to(totalProfitLastHourTopic, Produced.with(WindowedSerdes.sessionWindowedSerdeFrom(String.class), Serdes.String()));

        countryHighestSalesPerItem.toStream().to(countryHighestSalesPerItemTopic, Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }


    private static String getRevenuesPerItem(String message){
        Sale saleObject = new Gson().fromJson(message, Sale.class);
        return "name:" + saleObject.getItem() + ", revenues:" + (saleObject.getNumberOfUnits() * saleObject.getUnitPrice());
    }

    private static String addRevenuesPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        int revenuesItem1 = Integer.parseInt(item1.split(":")[2]);
        int revenuesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:" + name + ", revenues:" + (revenuesItem1 + revenuesItem2);
    }

    private static String mapRevenuesPerItem(String item){

        String name = item.split(":")[1].split(",")[0];
        int revenuesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(revenuesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String getExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return "name:" + purchaseObject.getItem() + ", expenses:" + (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice());
    }

    private static String addExpensesPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        int expensesItem1 = Integer.parseInt(item1.split(":")[2]);
        int expensesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:" + name + ", expenses:" + (expensesItem1 + expensesItem2);
    }

    private static String mapExpensesPerItem(String item){

        String name = item.split(":")[1].split(",")[0];
        int expensesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(expensesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String getProfitPerItem(String sale, String purchase){
        Sale saleObject = null;
        Purchase purchaseObject = null;

        if (sale != null)
            saleObject = new Gson().fromJson(sale, Sale.class);

        if (purchase != null)
            purchaseObject = new Gson().fromJson(purchase, Purchase.class);

        if (saleObject == null)
            return "name:" + purchaseObject.getItem() + ", profit:" + purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice() * (-1);
        else if (purchaseObject == null)
            return "name:" + saleObject.getItem() + ", profit:" + saleObject.getNumberOfUnits() * saleObject.getUnitPrice();

        return "name:" + saleObject.getItem() + ", profit:" + ((saleObject.getNumberOfUnits() * saleObject.getUnitPrice()) - (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice()));
    }

    private static String addProfitPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        int profitItem1 = Integer.parseInt(item1.split(":")[2]);
        int profitItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:" + name + ", profit:" + (profitItem1 + profitItem2);
    }

    private static String mapProfitPerItem(String item){

        String name = item.split(":")[1].split(",")[0];
        int profitItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(profitItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalRevenues(String item1, String item2){

        int revenuesItem1 = Integer.parseInt(item1.split(":")[2]);
        int revenuesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalRevenues, revenues:" + (revenuesItem1 + revenuesItem2);
    }

    private static String mapTotalRevenues(String item){

        int revenuesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalRevenues");
                add(String.valueOf(revenuesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalExpenses(String item1, String item2){

        int expensesItem1 = Integer.parseInt(item1.split(":")[2]);
        int expensesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalExpenses, expenses:" + (expensesItem1 + expensesItem2);
    }

    private static String mapTotalExpenses(String item){

        int expensesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalExpenses");
                add(String.valueOf(expensesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalProfit(String item1, String item2){

        int profitItem1 = Integer.parseInt(item1.split(":")[2]);
        int profitItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalProfit, profit:" + (profitItem1 + profitItem2);
    }

    private static String mapTotalProfit(String item){

        int profitItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalExpenses");
                add(String.valueOf(profitItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String getAverageExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return "name:" + purchaseObject.getItem() + ", averageExpenses:" + (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice()) + ".0";
    }

    private static String calculateAverageExpensesPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        double expensesItem1 = Double.parseDouble(item1.split(":")[2]);
        double expensesItem2 = Double.parseDouble(item2.split(":")[2]);
        double avgExpenses = (expensesItem1 + expensesItem2) / 2;

        return "name:" + name + ", averageExpenses:" + avgExpenses;
    }

    private static String mapAverageExpensesPerItem(String item){

        String name = item.split(":")[1].split(",")[0];
        double avgExpenses = Double.parseDouble(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(avgExpenses));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String calculateTotalAverageExpenses(String item1, String item2){
        
        double expensesItem1 = Double.parseDouble(item1.split(":")[2]);
        double expensesItem2 = Double.parseDouble(item2.split(":")[2]);
        double avgExpenses = (expensesItem1 + expensesItem2) / 2;

        return "name:TotalAverageExpenses, averageExpenses:" + avgExpenses;
    }

    private static String mapTotalAverageExpenses(String item){

        double avgExpenses = Double.parseDouble(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalAverageExpenses");
                add(String.valueOf(avgExpenses));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String getItemHighestProfit(String item1, String item2){

        String nameItem1 = item1.split(":")[1].split(",")[0];
        String nameItem2 = item1.split(":")[1].split(",")[0];

        int profitItem1 = Integer.parseInt(item1.split(":")[2]);
        int profitItem2 = Integer.parseInt(item2.split(":")[2]);

        if (profitItem2 > profitItem1)
            return "name:" + nameItem2 + ", value:" + profitItem2;
        else
            return "name:" + nameItem1 + ", value:" + profitItem1;
    }

    private static String mapItemHighestProfit(String item){

        String name = item.split(":")[1].split(",")[0];
        int profitItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(profitItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalRevenuesLastHour(String item1, String item2){

        int revenuesItem1 = Integer.parseInt(item1.split(":")[2]);
        int revenuesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalRevenuesLastHour, value:" + (revenuesItem1 + revenuesItem2);
    }

    private static String mapTotalRevenuesLastHour(String item){

        int revenuesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalRevenuesLastHour");
                add(String.valueOf(revenuesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalExpensesLastHour(String item1, String item2){

        int expensesItem1 = Integer.parseInt(item1.split(":")[2]);
        int expensesItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalExpensesLastHour, value:" + (expensesItem1 + expensesItem2);
    }

    private static String mapTotalExpensesLastHour(String item){

        int expensesItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalExpensesLastHour");
                add(String.valueOf(expensesItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String addTotalProfitLastHour(String item1, String item2){

        int profitItem1 = Integer.parseInt(item1.split(":")[2]);
        int profitItem2 = Integer.parseInt(item2.split(":")[2]);

        return "name:TotalProfitLastHour, value:" + (profitItem1 + profitItem2);
    }

    private static String mapTotalProfitLastHour(String item){

        int profitItem = Integer.parseInt(item.split(":")[2]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("value");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add("TotalProfitLastHour");
                add(String.valueOf(profitItem));
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    private static String getRevenuesPerItemPerCountry(String message){
        Sale saleObject = new Gson().fromJson(message, Sale.class);
        return "name:" + saleObject.getItem() + ", revenues:" + (saleObject.getNumberOfUnits() * saleObject.getUnitPrice())
                + ", country:" + saleObject.getCountry() + ", key:" + saleObject.getItem() + "-" + saleObject.getCountry();
    }

    private static String addRevenuesPerCountry(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        String country = item1.split(":")[3].split(",")[0];
        String key = item1.split(":")[4];
        int revenuesItem1 = Integer.parseInt(item1.split(":")[2].split(",")[0]);
        int revenuesItem2 = Integer.parseInt(item2.split(":")[2].split(",")[0]);

        return "name:" + name + ", revenues:" + (revenuesItem1 + revenuesItem2) + ", country:" + country + ", key:" + key;
    }

    private static String getCountryHighestSales(String item1, String item2){

        String nameItem1 = item1.split(":")[1].split(",")[0];
        String nameItem2 = item2.split(":")[1].split(",")[0];

        String country1 = item1.split(":")[3].split(",")[0];
        String country2 = item2.split(":")[3].split(",")[0];

        int salesCountry1 = Integer.parseInt(item1.split(":")[2].split(",")[0]);
        int salesCountry2 = Integer.parseInt(item2.split(":")[2].split(",")[0]);

        if (salesCountry2 > salesCountry1)
            return "name:" + nameItem2 + ", revenues:" + salesCountry2 + ", country:" + country2;
        else
            return "name:" + nameItem1 + ", revenues:" + salesCountry1 + ", country:" + country1;

    }

    private static String mapCountryHighestSales(String item){

        String name = item.split(":")[1].split(",")[0];
        String country = item.split(":")[3].split(",")[0];
        int salesCountry = Integer.parseInt(item.split(":")[2].split(",")[0]);

        ArrayList<String> varType = new ArrayList<String>(){
            {
                add("string");
                add("string");
                add("string");
            }
        };

        ArrayList<String> varName = new ArrayList<String>(){
            {
                add("name");
                add("revenues");
                add("country");
            }
        };

        ArrayList<String> varValue = new ArrayList<String>(){
            {
                add(name);
                add(String.valueOf(salesCountry));
                add(country);
            }
        };

        return StringsToSchema(varType, varName, varValue);
    }

    public static String StringsToSchema(List<String> varType, List<String> varName, List<String> varValue) {

        String types = "";
        for (int i = 0; i < varType.size(); i++) {
            types += "{\"type\":\"" + varType.get(i) + "\",\"optional\":false,\"field\":\"" + varName.get(i) + "\"},";
        }
        //remove last comma
        types = types.substring(0, types.length() - 1);

        String payload = "";
        for (int i = 0; i < varName.size(); i++) {
            payload += "\"" + varName.get(i) + "\":\"" + varValue.get(i) + "\",";
        }
        //remove last comma
        payload = payload.substring(0, payload.length() - 1);

        String finalString = "{\"schema\":{\"type\":\"struct\",\"fields\":[" + types +
                "],\"optional\":false,\"name\":\"total data\"},\"payload\":{" + payload + "}}";

        return finalString;
    }

}
