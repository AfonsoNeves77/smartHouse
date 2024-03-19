package SmartHome.vo;

public class CityVO implements ValueObject {

        private String city;

        public CityVO(String city) {
            if(city == null || city.isBlank()) {
                throw new IllegalArgumentException("Invalid parameters.");
            }
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }
}
