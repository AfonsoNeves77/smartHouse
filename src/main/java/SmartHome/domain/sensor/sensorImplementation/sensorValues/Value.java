package SmartHome.domain.sensor.sensorImplementation.sensorValues;


    /**
     * This interface utilizes generics. The interface value requires a generic marker "<>", to help control the type
     * in scope. On the interface itself, the "T" represents that the type that will fill the <> may vary with context
     */
public interface Value<T> {

        /**
         * @return Returns the primitive value that is encapsulated in the Value object. The "T" stands for a generic
         * return, which means, the return type will dynamically change based on context.
         */
     T getValue();
        /**
        * Dynamically converts the value in scope to string and returns it.
        * @return Value converted to String
        */
    String getValueAsString();
}
