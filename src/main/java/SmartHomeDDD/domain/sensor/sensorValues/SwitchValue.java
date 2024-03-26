package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHomeDDD.vo.ValueObject;

public class SwitchValue implements ValueObject<String> {
    private final String switchValue;

    public SwitchValue(String switchValue) {
        if (!isReadingValid(switchValue)) {
            throw new IllegalArgumentException("Invalid switch value");
        }
        this.switchValue = switchValue;
    }

    public String getValue() {
        return this.switchValue;
    }

    private boolean isReadingValid(String switchValue) {
        if (switchValue == null) return false;
        return switchValue.trim().equalsIgnoreCase("On") || switchValue.trim().equalsIgnoreCase("Off");
    }
}
