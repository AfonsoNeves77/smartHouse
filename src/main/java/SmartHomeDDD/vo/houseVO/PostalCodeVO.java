package SmartHomeDDD.vo.houseVO;

import SmartHomeDDD.vo.ValueObject;

public class PostalCodeVO implements ValueObject<String> {

    private final String postalCode;

    /**
     * Constructor for PostalCodeVO, validates encapsulated value is not null or empty;
     * @param postalCode Postal Code
     */
    public PostalCodeVO(String postalCode){
        if (postalCode == null || postalCode.trim().isEmpty())
            throw new IllegalArgumentException("Please insert a valid country name.");
        this.postalCode = postalCode;
    }

    /**
     * Simple getter method.
     * @return Encapsulated value
     */
    @Override
    public String getValue() {
        return postalCode;
    }
}
