package data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class totalAverageExpenses {
    @Id
    private String name;
    private String value;

    public totalAverageExpenses(String name, String value) {
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