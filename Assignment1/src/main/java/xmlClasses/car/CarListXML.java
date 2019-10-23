package xmlClasses.car;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="cars")
public class CarListXML {
    private List<CarXML> cars;

    public CarListXML() {
    }

    public CarListXML(List<CarXML> cars) {
        super();
        this.cars = cars;
    }

    @XmlElement(name="car")
    public List<CarXML> getCars() {
        return cars;
    }

    public void setCars(List<CarXML> cars) {
        this.cars = cars;
    }
}