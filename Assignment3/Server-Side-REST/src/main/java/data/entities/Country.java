package data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(nullable = false)
    String name;

    public Country() {
        super();
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
