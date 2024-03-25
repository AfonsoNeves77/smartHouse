package SmartHomeDDD.vo.houseVO;

import SmartHomeDDD.vo.ValueObject;

public class CountryVO implements ValueObject<String> {

    private final String country;

    /**
     * Constructor for CountryVO. Ensures value is not null or empty before creating the object.
     * @param country Country
     */
    public CountryVO(String country){
        if (country == null || country.trim().isEmpty())
            throw new IllegalArgumentException("Please insert a valid country name.");
        this.country = country;
    }

    /**
     * Simple getter method
     * @return Encapsulated value
     */
    @Override
    public String getValue() {
        return this.country;
    }
}
