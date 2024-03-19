package SmartHome.vo;

public class ActuatorNameVO implements ValueObject {

    private String actuatorName;

    public ActuatorNameVO(String actuatorName) {
        if(actuatorName == null || actuatorName.isBlank()) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.actuatorName = actuatorName;
    }

    public String getActuatorName() {
        return this.actuatorName;
    }
}
