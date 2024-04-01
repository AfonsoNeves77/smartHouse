package SmartHomeDDD.dto;

public class LocationDTO {

        private String door;
        private String street;
        private String city;

        private String country;
        private String postalCode;

        private String latitude;
        private String longitude;

        /**
         * Constructor for LocationDTO
         * @param door
         * @param street
         * @param city
         * @param country
         * @param postalCode
         * @param latitude
         * @param longitude
         */
        public LocationDTO(String door, String street, String city, String country, String postalCode, String latitude, String longitude) {
            this.door = door;
            this.street = street;
            this.city = city;
            this.country = country;
            this.postalCode = postalCode;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        /**
         * Getter method to retrieve door
         * @return door in string format
         */
        public String getDoor() {
            return door;
        }

        /**
         * Getter method to retrieve street
         * @return street
         */
        public String getStreet() {
            return street;
        }

        /**
         * Getter method to retrieve city
         * @return city
         */
        public String getCity() {
            return city;
        }

        /**
         * Getter method to retrieve country
         * @return country
         */
        public String getCountry() {
            return country;
        }

        /**
         * Getter method to retrieve postalCode
         * @return postalCode
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         * Getter method to retrieve latitude
         * @return latitude
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         * Getter method to retrieve longitude
         * @return longitude
         */
        public String getLongitude() {
            return longitude;
        }
    }
