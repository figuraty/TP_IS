package data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "countries")
@XmlAccessorType (XmlAccessType.FIELD)
public class ListCountriesXML implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "country")
    private List<CountryXML> countries = null;

    public ListCountriesXML(){
        this.countries = new ArrayList<>();
    }

    public List<CountryXML> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryXML> countries) {
        this.countries = countries;
    }
//
//    public ListCountries(List<CountryXML> countries) {
//        this.countries = countries;
//    }
}
