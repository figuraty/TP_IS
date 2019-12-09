import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class CustomerConsumer extends Thread{

    private List<String> items;
    private List<String> countries;

    public CustomerConsumer(List<String> items, List<String> countries){
        this.items = items;
        this.countries = countries;
    }

    public void run(){

        String topicName = "DBInfoTopic";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "salesConsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer
                <String, String>(props);
        consumer.subscribe(Collections.singletonList(topicName));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                synchronized (items) {
                    synchronized (countries) {
                        if (records.count() != 0) {
                            for (ConsumerRecord<String, String> record : records) {
                                if (record.key().equals("Item"))
                                    items.add(record.value());
                                else if (record.key().equals("Country"))
                                    countries.add(record.value());
                            }
                        }
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
