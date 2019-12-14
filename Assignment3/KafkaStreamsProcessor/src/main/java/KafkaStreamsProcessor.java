import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class KafkaStreamsProcessor {

    public static void main(String[] args) throws InterruptedException, IOException {
        String purchasesTopic = "purchasesTopic";
        String salesTopic = "salesTopic";
        String testTopic = "testTopic20";
        String test2Topic = "testTopic22";
        String test3Topic = "testTopic30";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application-35");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> salesData = builder.stream(test2Topic);
        KStream<String, String> purchasesData = builder.stream(testTopic);

        KStream<String, String> profitData = salesData.outerJoin(purchasesData,
                KafkaStreamsProcessor::getProfitPerItem,
                JoinWindows.of(Duration.ofNanos(1)),
                Joined.with(
                        Serdes.String(), /* key */
                        Serdes.String(),   /* left value */
                        Serdes.String())  /* right value */
        );

        KStream<String, String> itemProfitData = salesData.outerJoin(purchasesData,
                KafkaStreamsProcessor::getItemsProfit,
                JoinWindows.of(Duration.ofNanos(1)),
                Joined.with(
                        Serdes.String(), /* key */
                        Serdes.String(),   /* left value */
                        Serdes.String())  /* right value */
        );


        KTable<String, String> revenuesPerItem = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addRevenuesPerItem);

        
        KTable<String, String> expensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addExpensesPerItem);


        KTable<String, String> profitPerItem = profitData.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addProfitPerItem);


        KTable<String, String> totalRevenues = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupBy((k, v) -> "totalRevenues", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalRevenues);


        KTable<String, String> totalExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupBy((k, v) -> "totalExpenses", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalExpenses);
        

        KTable<String, String> totalProfit = profitData
                .groupBy((k, v) -> "totalProfit", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::addTotalProfit);
        
        
        KTable<String, String> averageExpensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::calculateAverageExpensesPerItem);
        

        KTable<String, String> totalAverageExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupBy((k, v) -> "totalAverageExpenses", Grouped.with(Serdes.String(), Serdes.String()))
                .reduce(KafkaStreamsProcessor::calculateTotalAverageExpenses);



//        KTable<String, String> testProfitPerItem = itemProfitData
//                .mapValues(KafkaStreamsProcessor::transformData)
//                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
//                .reduce(Integer::sum);





//        revenuesPerItem.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        expensesPerItem.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        profitPerItem.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        totalRevenues.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        totalExpenses.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        totalProfit.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        averageExpensesPerItem.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));

//        totalAverageExpenses.toStream().to(test3Topic, Produced.with(Serdes.String(), Serdes.String()));







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

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:" + name + ", revenues:" + String.valueOf(revenuesItem1 + revenuesItem2);
    }

    private static String getExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return "name:" + purchaseObject.getItem() + ", expenses:" + (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice());
    }

    private static String addExpensesPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        int expensesItem1 = Integer.parseInt(item1.split(":")[2]);
        int expensesItem2 = Integer.parseInt(item2.split(":")[2]);

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:" + name + ", expenses:" + String.valueOf(expensesItem1 + expensesItem2);
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

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:" + name + ", profit:" + (profitItem1 + profitItem2);
    }

    private static String addTotalRevenues(String item1, String item2){

        int revenuesItem1 = Integer.parseInt(item1.split(":")[2]);
        int revenuesItem2 = Integer.parseInt(item2.split(":")[2]);

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:TotalRevenues, revenues:" + (revenuesItem1 + revenuesItem2);
    }

    private static String addTotalExpenses(String item1, String item2){

        int expensesItem1 = Integer.parseInt(item1.split(":")[2]);
        int expensesItem2 = Integer.parseInt(item2.split(":")[2]);

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:TotalExpenses, expenses:" + (expensesItem1 + expensesItem2);
    }

    private static String addTotalProfit(String item1, String item2){

        int profitItem1 = Integer.parseInt(item1.split(":")[2]);
        int profitItem2 = Integer.parseInt(item2.split(":")[2]);

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:TotalProfit, profit:" + (profitItem1 + profitItem2);
    }

    private static String getAverageExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return "name:" + purchaseObject.getItem() + ", averageExpenses:" + (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice() + ".0");
    }

    private static String calculateAverageExpensesPerItem(String item1, String item2){

        String name = item1.split(":")[1].split(",")[0];
        double expensesItem1 = Double.parseDouble(item1.split(":")[2]);
        double expensesItem2 = Double.parseDouble(item2.split(":")[2]);
        double avgExpenses = (expensesItem1 + expensesItem2) / 2;

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:" + name + ", averageExpenses:" + avgExpenses;
    }

    private static String calculateTotalAverageExpenses(String item1, String item2){
        
        double expensesItem1 = Double.parseDouble(item1.split(":")[2]);
        double expensesItem2 = Double.parseDouble(item2.split(":")[2]);
        double avgExpenses = (expensesItem1 + expensesItem2) / 2;

        //ALTERAR O RETORNO PARA UM OBJETO JSON
        return "name:TotalAverageExpenses, averageExpenses:" + avgExpenses;
    }





//    private static int getAverageExpensesPerItem(String message){
//        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
//        return purchaseObject.getUnitPrice();
//    }

    private static String getItemsProfit(String sale, String purchase){
        Sale saleObject = null;
        Purchase purchaseObject = null;

        if (sale != null)
            saleObject = new Gson().fromJson(sale, Sale.class);

        if (purchase != null)
            purchaseObject = new Gson().fromJson(purchase, Purchase.class);

        if (saleObject == null)
            return purchaseObject.getItem() + ": " + purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice() * (-1);
        else if (purchaseObject == null)
            return saleObject.getItem() + ": " + saleObject.getNumberOfUnits() * saleObject.getUnitPrice();

        return saleObject.getItem() + ": " + ((saleObject.getNumberOfUnits() * saleObject.getUnitPrice()) - (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice()));
    }
}
