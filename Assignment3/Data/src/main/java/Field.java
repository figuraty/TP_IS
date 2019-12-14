public class Field {

    private String type;
    private boolean optional;
    private String field;

    public Field(String type, boolean optional, String field) {
        this.type = type;
        this.optional = optional;
        this.field = field;
    }

    // Getter Methods

    public String getType() {
        return type;
    }

    public boolean isOptional() {
        return optional;
    }

    public String getField() {
        return field;
    }


    // Setter Methods


    public void setType(String type) {
        this.type = type;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public void setField(String field) {
        this.field = field;
    }
}
