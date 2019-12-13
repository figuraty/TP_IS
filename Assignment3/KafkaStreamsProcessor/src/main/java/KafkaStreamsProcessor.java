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
        String test2Topic = "testTopic21";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application-15");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> salesData = builder.stream(test2Topic);
        KStream<String, String> purchasesData = builder.stream(testTopic);

        KStream<String, String> profitData = salesData.outerJoin(purchasesData,
                KafkaStreamsProcessor::getProfit,
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

        KTable<String, Integer> revenuesPerItem = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> totalRevenues = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
                .groupBy((k, v) -> "totalRevenues", Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> expensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> totalExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupBy((k, v) -> "totalExpenses", Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> averageExpensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce((v1, v2) -> (v1 + v2)/2 );

        KTable<String, Integer> totalAverageExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getAverageExpensesPerItem)
                .groupBy((k, v) -> "totalAverageExpenses", Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce((v1, v2) -> (v1 + v2)/2 );

        KTable<String, Integer> profitPerItem = profitData.mapValues(KafkaStreamsProcessor::transformStringToInt)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> totalProfit = profitData.mapValues(KafkaStreamsProcessor::transformStringToInt)
                .groupBy((k, v) -> "totalProfit", Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

//        KTable<String, String> testProfitPerItem = itemProfitData
//                .mapValues(KafkaStreamsProcessor::transformData)
//                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
//                .reduce(Integer::sum);





//        revenuesPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalRevenues.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        expensesPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalExpenses.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        averageExpensesPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalAverageExpenses.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        profitPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalProfit.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));


        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }



    private static int getRevenuesPerItem(String message){
        Sale saleObject = new Gson().fromJson(message, Sale.class);
        return saleObject.getNumberOfUnits() * saleObject.getUnitPrice();
    }

    private static int getExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice();
    }

    private static int getAverageExpensesPerItem(String message){
        Purchase purchaseObject = new Gson().fromJson(message, Purchase.class);
        return purchaseObject.getUnitPrice();
    }

    private static String getProfit(String sale, String purchase){
        Sale saleObject = null;
        Purchase purchaseObject = null;

        if (sale != null)
            saleObject = new Gson().fromJson(sale, Sale.class);

        if (purchase != null)
            purchaseObject = new Gson().fromJson(purchase, Purchase.class);

        if (saleObject == null)
            return String.valueOf(purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice() * (-1));
        else if (purchaseObject == null)
            return String.valueOf(saleObject.getNumberOfUnits() * saleObject.getUnitPrice());

        return String.valueOf((saleObject.getNumberOfUnits() * saleObject.getUnitPrice()) - (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice()));
    }

    private static String getItemsProfit(String sale, String purchase){
        Sale saleObject = null;
        Purchase purchaseObject = null;

        if (sale != null)
            saleObject = new Gson().fromJson(sale, Sale.class);

        if (purchase != null)
            purchaseObject = new Gson().fromJson(purchase, Purchase.class);

        if (saleObject == null)
            return String.valueOf(purchaseObject.getItem() + ": " + purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice() * (-1));
        else if (purchaseObject == null)
            return String.valueOf(saleObject.getItem() + ": " + saleObject.getNumberOfUnits() * saleObject.getUnitPrice());

        return String.valueOf(saleObject.getItem() + ": " + ((saleObject.getNumberOfUnits() * saleObject.getUnitPrice()) - (purchaseObject.getNumberOfUnits() * purchaseObject.getUnitPrice())));
    }



    private static int transformStringToInt(String number){
        return Integer.parseInt(number);
    }

    private static String transformData(String number){
        System.out.println(number);
        return "1";
    }

    private static String transformIntToString(int number){
        return String.valueOf(number);
    }


}
