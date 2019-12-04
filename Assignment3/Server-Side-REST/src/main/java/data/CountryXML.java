package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "country")
@XmlAccessorType (XmlAccessType.FIELD)
public class CountryXML implements Serializable {
    private  static final long serialVersionUID = 1L;

    private String name;

    public CountryXML() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public CountryXML() {
//        super();
//    }

//    public CountryXML(String name){
//        this.name = name;
//    }



}
