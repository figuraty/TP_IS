import java.util.ArrayList;

public class Schema {
    private String type;
    ArrayList< Object > fields = new ArrayList < Object > ();
    private boolean optional;


    // Getter Methods

    public String getType() {
        return type;
    }

    public boolean getOptional() {
        return optional;
    }

    // Setter Methods

    public void setType(String type) {
        this.type = type;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
