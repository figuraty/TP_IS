package data.dtos;

public class ItemTransactionsDTO {
    private String name;
    private String value;

    public ItemTransactionsDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
