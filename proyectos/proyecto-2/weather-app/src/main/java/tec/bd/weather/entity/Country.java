package tec.bd.weather.entity;

public class Country {

    private Integer id;
    private String countryName;

    public Country(Integer id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public static void validate(Country country) {
        if (country == null) {
            throw new RuntimeException("No country was provided");
        }
        if (country.getId() == null) {
            throw new RuntimeException("No country city code was provided");
        }
    }
}