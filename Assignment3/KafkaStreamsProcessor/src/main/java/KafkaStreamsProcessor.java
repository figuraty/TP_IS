import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

public class KafkaStreamsProcessor {

    public static void main(String[] args) throws InterruptedException, IOException {
        String purchasesTopic = "purchasesTopic";
        String salesTopic = "salesTopic";
        String testTopic = "testTopic";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> lines = builder.stream(salesTopic);
//
//        lines.mapValues(message -> message.length()).to(purchasesTopic, Produced.with(Serdes.String(), Serdes.Integer()));


//        KTable<String, Long> outlines = lines.
//                groupByKey().count();
//        outlines.toStream().to(purchasesTopic);

//        KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        streams.start();


        //////////////////////////////////////////////////////////////////////////////////////

//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, Long> lines = builder.stream(salesTopic);
//
//        KTable<String, Long> outlines = (KTable<String, Long>) lines.groupByKey();
//
//        outlines.mapValues(v -> "" + v).toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.String()));
//
//        KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        streams.start();

        ////////////////////////////////////////////////////////////////////////////////////////////

//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<String, String> textLines = builder.stream(salesTopic);
//
//        KTable<String, Long> wordCounts = textLines
//                .flatMapValues(value -> Arrays.asList())
//                .groupBy((key, word) -> word)
//                .count();
//
//        wordCounts.toStream().to(purchasesTopic, Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }
}
