package SmartHome.vo;

import SmartHome.ddd.DomainID;

import java.util.UUID;

public class SensorTypeIDVO implements DomainID {

    private final UUID sensorTypeID;

    public SensorTypeIDVO(UUID sensorTypeID) {
        if (sensorTypeID == null) throw new IllegalArgumentException("SensorTypeID cannot be null");
        this.sensorTypeID = sensorTypeID;
    }


    public String getID() {
        return sensorTypeID.toString();
    }
}
