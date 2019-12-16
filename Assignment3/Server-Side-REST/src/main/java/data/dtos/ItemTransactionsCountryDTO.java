package data.dtos;

public class ItemTransactionsCountryDTO {
    private String name;
    private String revenues;
    private String country;

    public ItemTransactionsCountryDTO(String name, String revenues, String country) {
        this.name = name;
        this.revenues = revenues;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getRevenues() {
        return revenues;
    }

    public String getCountry(){
        return country;
    }
}
