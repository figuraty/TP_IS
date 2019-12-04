package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXML implements Serializable {
    private  static final long serialVersionUID = 1L;
    @XmlAttribute
    private String name;

    public CountryXML() {
        super();
    }

    public CountryXML(String name){
        this.name = name;
    }



}
