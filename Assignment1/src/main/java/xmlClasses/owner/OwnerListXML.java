package xmlClasses.owner;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="owners")
public class OwnerListXML {
    private List<OwnerXML> owners;

    public OwnerListXML() {
    }

    public OwnerListXML(List<OwnerXML> owners) {
        super();
        this.owners = owners;
    }

    @XmlElement(name="owner")
    public List<OwnerXML> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerXML> owners) {
        this.owners = owners;
    }
}