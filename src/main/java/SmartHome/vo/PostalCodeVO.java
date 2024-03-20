package SmartHome.vo;

public class PostalCodeVO implements ValueObject {

    private final String postalCode;

    public PostalCodeVO(String postalCode){
        if (postalCode == null || postalCode.trim().isEmpty())
            throw new IllegalArgumentException("Please insert a valid country name.");
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
