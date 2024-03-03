package SmartHome.domain.sensor.sensorImplementation.sensorValues;

public class WindValue implements Value {
    private final int windSpeed;
    private final String windDirection;
    String[] permittedDirections = {"N", "S", "E", "W", "NW", "NE", "SW", "SE"};

    /**
     * Constructor for Wind value. The windspeed will be the primary value to be stored, however,
     * as a windDirection is required, the Wind Value will also hold a char for windDirection.
     * @param windspeed Wind speed value.
     * @param windDirection Wind Direction.
     * @throws InstantiationException On invalid parameters.
     */
    public WindValue(int windspeed, String windDirection) throws InstantiationException { // "40-N"
        if (windspeed < 0 || !isDirectionValid(windDirection)){
            throw new InstantiationException("Invalid reading");
        }
        this.windSpeed = windspeed;
        this.windDirection = windDirection;
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type. Wind speed is the primary value
     * stored unto this value, so in order to utilize the Value interface, only this first int will be
     * returned by getValue.
     * @return Encapsulated primitive value.
     */
    public Integer getValue() {
        return this.windSpeed;
    }


    /* In order to call the following method on a class that receives a general "Value" and not a windValue, it will have
    that specific Value cast as WindValue. This could all be returned as String, including the windSpeed, but as it is
    unclear if the actual primitive values will be used for calculations, this is the interim solution.
     */
    /**
     * Returns the encapsulated primitive value, maintaining its type. It provides the optional wind direction
     * @return Encapsulated primitive value.
     */
    public String getWindDirection(){
        return this.windDirection;
    }

    /**
     * Returns the primitive value after conversion to String format. Combines both wind speed and wind direction
     * into a string, with the delimiter "-" separating the two.
     * @return Encapsulated primitive value as String.
     */
    public String getValueAsString(){
        return this.windSpeed + "-" + this.windDirection;
    }

    /**
     * This method matches the input windDirection against the valid types.
     * @param windDirection Direction received as String.
     * @return True or False
     */
    private boolean isDirectionValid (String windDirection){
        for (String direction : permittedDirections){
            if (direction.equals(windDirection)){
                return true;
            }
        }
        return false;
    }
}
