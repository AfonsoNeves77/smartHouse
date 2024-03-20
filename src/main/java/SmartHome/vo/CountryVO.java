package SmartHome.vo;

public class CountryVO implements ValueObject {

    private final String country;

    public CountryVO(String country){
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("Please insert a valid country name.");
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}
