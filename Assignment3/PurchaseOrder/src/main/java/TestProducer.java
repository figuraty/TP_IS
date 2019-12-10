import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TestProducer extends Thread {

    public TestProducer(){}

    public void run(){

        String topicName = "DBInfoTopic";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer
                <String, String>(props);

        producer.send(new ProducerRecord<String, String>(topicName, "Item", "Tomate"));
        producer.send(new ProducerRecord<String, String>(topicName, "Item", "Cebola"));
        producer.send(new ProducerRecord<String, String>(topicName, "Country", "Portugal"));
        producer.send(new ProducerRecord<String, String>(topicName, "Country", "Espanha"));
    }
}
