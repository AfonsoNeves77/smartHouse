package SmartHome.vo;

public class PostalCodeVO {

    private final String postalCode;

    public PostalCodeVO(String postalCode) throws InstantiationException {
        if (postalCode == null || postalCode.trim().isEmpty())
            throw new InstantiationException("Please insert a valid country name.");
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

}
