import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PurchaseOrderConsumer extends Thread{

    private List<String> items;

    public PurchaseOrderConsumer(List<String> items){
        this.items = items;
    }

    public void run(){

        String topicName = "DBInfoTopic";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "purchasesConsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "10000");
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
                    if (records.count() != 0) {
                        for (ConsumerRecord<String, String> record : records) {
                            ReadTopicObject ReadTopicObject = new Gson().fromJson(record.value(), ReadTopicObject.class);
                            if (ReadTopicObject.payload.getType().equals("Item"))
                                addItem(ReadTopicObject.payload.getName(), items);
                        }
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void addItem(String item, List<String> items){
        if (!items.contains(item))
            items.add(item);
    }
}
