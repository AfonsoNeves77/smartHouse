package SmartHome.vo;

public class LocationVO implements ValueObject{

    private AddressVO addressVO;
    private GpsVO gpsVO;

    public LocationVO(AddressVO addressVO, GpsVO gpsVO) throws IllegalArgumentException{
        if (addressVO == null || gpsVO == null)
            throw new IllegalArgumentException("Invalid parameter.");
        this.addressVO = addressVO;
        this.gpsVO = gpsVO;
    }

    public String getDoor(){
        return addressVO.getDoor();
    }

    public String getStreet(){
        return addressVO.getStreet();
    }

    public String getCity(){
        return addressVO.getCity();
    }

    public String getCountry(){
        return addressVO.getCountry();
    }

    public String getPostalCode(){
        return addressVO.getPostalCode();
    }

    public double getLatitude(){
        return gpsVO.getLatitude();
    }

    public double getLongitude(){
        return gpsVO.getLongitude();
    }

    @Override
    public String toString() {
        return "Location: Address - " +
                getStreet() + ", " +
                getDoor() + ". " +
                getPostalCode() + ", " +
                getCity() + ", " +
                getCountry() + ". GPS - latitude: " +
                getLatitude() + ", longitude: " +
                getLongitude();
    }

}
