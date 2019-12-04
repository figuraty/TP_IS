package data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "PurchaseList")
public class PurchaseList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Purchase> purchases;

    public PurchaseList() {
        this.purchases = new ArrayList<>();
    }
}
