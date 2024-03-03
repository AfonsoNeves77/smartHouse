package SmartHome.domain.location;

public class Address {
    private String doorReference;
    private String buildingNumber;
    private String streetName;
    private String city;
    private String country;
    private String zipCode;

    public Address(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode){
        if (!areAddressParamsValid(doorReference, buildingNumber, streetName, city, country, zipCode)) {
            throw new IllegalArgumentException("Invalid parameter.");
        }
        this.doorReference = doorReference;
        this.buildingNumber = buildingNumber;
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    private boolean areAddressParamsValid(String... params) {
        for (String param : params) {
            if (param == null || param.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
