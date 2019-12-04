package data;

import javax.persistence.*;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "name")
    String name;

    public Country(){
        super();
    }

    public Country(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
