package xmlClasses.car;

import javax.xml.bind.annotation.XmlAttribute;

public class CarXML {
    private int id;
    private String brand;
    private String model;
    private String engine;
    private int size;
    private int power;
    private float consumption;
    private String plate;
    private int ownerId;


    public CarXML() {}

    public CarXML(int id, String brand, String model, String engine, int size, int power, float consumption, String plate, int ownerId) {
        super();
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.engine = engine;
        this.size = size;
        this.power = power;
        this.consumption = consumption;
        this.plate = plate;
        this.ownerId = ownerId;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
