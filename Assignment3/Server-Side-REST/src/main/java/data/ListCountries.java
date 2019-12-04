package data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="ListCountries")
public class ListCountries implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<CountryXML> countries;

    public ListCountries(List<CountryXML> countries) {
        this.countries = countries;
    }
}
