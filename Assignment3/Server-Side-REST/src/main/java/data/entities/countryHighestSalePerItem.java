package data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class countryHighestSalePerItem {
    @Id
    private String name;
    private String revenues;
    private String country;

    public countryHighestSalePerItem(){
        super();
    }

    public countryHighestSalePerItem(String name, String revenues, String country) {
        this.name = name;
        this.revenues = revenues;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getRevenues() {
        return revenues;
    }

    public String getCountry() {
        return country;
    }
}