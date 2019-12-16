import java.util.ArrayList;

public class Schema {
    private String type;
    ArrayList< Field > fields;
    private boolean optional;


    // Getter Methods

    public String getType() {
        return type;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public boolean isOptional() {
        return optional;
    }


    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
