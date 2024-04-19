package smarthome.persistence.jpa.datamodel;

import jakarta.persistence.*;
import smarthome.domain.house.House;
import smarthome.domain.vo.housevo.LocationVO;

@Entity
@Table(name = "house")
public class HouseDataModel {

    @Id
    @Column(name = "id")
    private String id;
    private String door;
    private String street;
    private String city;
    private String country;
    @Column(name = "postal_code")
    private String postalCode;
    private double latitude;
    private double longitude;

    public HouseDataModel() {
    }

    public HouseDataModel(House house) {
        this.id = house.getId().getID();
        LocationVO location = house.getLocation();
        this.door = location.getDoor();
        this.street = location.getStreet();
        this.city = location.getCity();
        this.country = location.getCountry();
        this.postalCode = location.getPostalCode();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public boolean updateFromDomain(House house) {
        this.id = house.getId().getID();
        LocationVO location = house.getLocation();
        this.door = location.getDoor();
        this.street = location.getStreet();
        this.city = location.getCity();
        this.country = location.getCountry();
        this.postalCode = location.getPostalCode();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

        return true;
    }


    public String getId() {
        return id;
    }

    public String getDoor() {
        return door;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
