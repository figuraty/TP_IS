package data;

import data.entities.Item;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "ListItems")
public class ListItems implements Serializable {
    private List<Item> items;
}
