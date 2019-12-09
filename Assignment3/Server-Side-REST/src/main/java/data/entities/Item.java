package data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;

    public Item(){
        super();
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
