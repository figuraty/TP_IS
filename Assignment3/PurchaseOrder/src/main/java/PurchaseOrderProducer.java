import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;
import java.util.Random;

public class PurchaseOrderProducer extends Thread {

    private List<String> items;
    private int MAX_ITEMS = 10000;
    private int MAX_PRICE = 100;
    private int MIN_PRICE = 10;

    public PurchaseOrderProducer(List<String> items){
        this.items = items;
    }

    public void run(){

        String topicName = "purchasesTopic";
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
                    if (!items.isEmpty()){
                        Random rand = new Random();
                        String item = items.get(rand.nextInt(items.size()));
                        int numberOfUnits = rand.nextInt(MAX_ITEMS) + 1;
                        int price = rand.nextInt((MAX_PRICE - MIN_PRICE) + 1 ) + 1;
                        Purchase purchase = new Purchase(item, numberOfUnits, price);
                        Gson gson = new Gson();
                        String purchaseObject = gson.toJson(purchase);
                        System.out.println(purchaseObject);
                        producer.send(new ProducerRecord<String, String>(topicName, "Purchase", purchaseObject));
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}