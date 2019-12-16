package data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class totalRevenues {
    @Id
    private String name;
    private String value;

    public totalRevenues(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}