package SmartHome.domain.location;

public class Address {
    private String doorReference;
    private String buildingNumber;
    private String streetName;
    private String city;
    private String country;
    private String zipCode;

    /**
     * Creates new address object if entry parameters are valid.
     * @param doorReference Door reference
     * @param buildingNumber Build number
     * @param streetName Street name
     * @param city City
     * @param country Country
     * @param zipCode Zipcode
     */
    public Address(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode) throws InstantiationException {
        if (!areAddressParamsValid(doorReference, buildingNumber, streetName, city, country, zipCode)) {
            throw new InstantiationException("Invalid parameter.");
        }
        this.doorReference = doorReference;
        this.buildingNumber = buildingNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    /**
     * Receives a number of parameters and validates that they are not null or empty.
     * @param params Door reference, building number, street name, city, country, zipcode
     * @return True or false.
     */
    private boolean areAddressParamsValid(String... params) {
        for (String param : params) {
            if (param == null || param.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
