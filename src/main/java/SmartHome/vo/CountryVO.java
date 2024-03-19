package SmartHome.vo;

public class CountryVO {

    private final String country;

    public CountryVO(String country) throws InstantiationException {
        if (country == null || country.trim().isEmpty())
            throw new InstantiationException("Please insert a valid country name.");
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}
