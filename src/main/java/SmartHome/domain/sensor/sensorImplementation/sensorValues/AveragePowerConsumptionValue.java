package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Double.parseDouble;


public class AveragePowerConsumptionValue implements Value<Double> {
    private final double primitiveValue;

    public AveragePowerConsumptionValue(String reading) throws InstantiationException {
        if(!validateReading(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseDouble(reading);
    }

    private boolean validateReading(String reading) {
        double valueOfReading;
        try {
            valueOfReading = parseDouble(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0;
    }

    @Override
    public Double getValue() {
        return this.primitiveValue;
    }

    @Override
    public String getValueAsString() {
        return primitiveValue + "";
    }
}
