package SmartHome.vo;

public class DoorVO implements ValueObject{
    String door;

    /**
     * Constructor for DoorVO. It takes a string, ensures it is not null or empty, and encapsulates it.
     * @param door Door
     * @throws IllegalArgumentException When parameter invalid
     */
    public DoorVO (String door) throws IllegalArgumentException {
        if (isParamValid(door)){
            this.door = door;
        } else {
            throw new IllegalArgumentException ("Invalid Parameters");
        }
    }

    public String getDoor() {
        return door;
    }

    private boolean isParamValid(String param){
        return param != null && !param.isBlank();
    }
}
