import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;
import java.util.Random;

public class CustomerProducer extends Thread {

    private List<String> items;
    private List<String> countries;

    private int MAX_ITEMS = 10000;
    private int MAX_PRICE = 100;
    private int MIN_PRICE = 10;

    public CustomerProducer(List<String> items, List<String> countries){
        this.items = items;
        this.countries = countries;
    }

    public void run(){

//        String topicName = "salesTopic";
        String topicName = "testTopic5";
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
        try {
            while(true){
                synchronized (items){
                    synchronized (countries) {
                        if (!items.isEmpty() && !countries.isEmpty()) {
                            Random rand = new Random();
                            String item = items.get(rand.nextInt(items.size()));
                            int numberOfUnits = rand.nextInt(MAX_ITEMS) + 1;
                            int unitPrice = rand.nextInt((MAX_PRICE - MIN_PRICE) + 1) + 1;
                            String country = countries.get(rand.nextInt(countries.size()));
                            Sale sale = new Sale(item, numberOfUnits, unitPrice, country);
                            Gson gson = new Gson();
                            String purchaseObject = gson.toJson(sale);
                            producer.send(new ProducerRecord<String, String>(topicName, sale.getItem(), purchaseObject));
                        }
                    }
                }
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
