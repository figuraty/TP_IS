import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.io.IOException;
import java.util.Properties;

public class KafkaStreamsProcessor {

    public static void main(String[] args) throws InterruptedException, IOException {
        String purchasesTopic = "purchasesTopic";
        String salesTopic = "salesTopic";
        String testTopic = "testTopic8";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application-7");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> salesData = builder.stream(testTopic);
        KStream<String, String> purchasesData = builder.stream(testTopic);

//        KTable<String, Integer> revenuesPerItem = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
//                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
//                .reduce(Integer::sum);
//
//        KTable<String, Integer> totalRevenues = salesData.mapValues(KafkaStreamsProcessor::getRevenuesPerItem)
//                .groupBy((k, v) -> "totalRevenues", Grouped.with(Serdes.String(), Serdes.Integer()))
//                .reduce(Integer::sum);

        KTable<String, Integer> expensesPerItem = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);

        KTable<String, Integer> totalExpenses = purchasesData.mapValues(KafkaStreamsProcessor::getExpensesPerItem)
                .groupBy((k, v) -> "totalExpenses", Grouped.with(Serdes.String(), Serdes.Integer()))
                .reduce(Integer::sum);






//        revenuesPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalRevenues.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

        expensesPerItem.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));

//        totalExpenses.mapValues(KafkaStreamsProcessor::transformIntToString).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));




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


    private static String transformIntToString(int number){
        return String.valueOf(number);
    }


}
