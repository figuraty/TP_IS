import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {

    public static void main(String[] args){
        List<String> items = new ArrayList<String>();
        PurchaseOrderProducer pop = new PurchaseOrderProducer(items);
        PurchaseOrderConsumer poc = new PurchaseOrderConsumer(items);
        pop.start();
        poc.start();
    }
}