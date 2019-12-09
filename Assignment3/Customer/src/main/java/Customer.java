import java.util.ArrayList;
import java.util.List;

public class Customer {

    public static void main(String[] args){
        List<String> items = new ArrayList<String>();
        List<String> countries = new ArrayList<String>();
        CustomerProducer cp = new CustomerProducer(items, countries);
        CustomerConsumer cc = new CustomerConsumer(items, countries);
        TestProducer tp = new TestProducer();
        tp.start();
        cp.start();
        cc.start();
    }
}