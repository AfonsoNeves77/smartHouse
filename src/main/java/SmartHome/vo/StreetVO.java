package SmartHome.vo;

public class StreetVO implements ValueObject {

        private final String street;

        public StreetVO(String street) {
            if(street == null || street.isBlank()) {
                throw new IllegalArgumentException("Invalid parameters.");
            }
            this.street = street;
        }

        public String getStreet() {
            return this.street;
        }
}
