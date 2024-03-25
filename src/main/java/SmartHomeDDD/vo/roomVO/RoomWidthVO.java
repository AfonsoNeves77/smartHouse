package SmartHomeDDD.vo.roomVO;

import SmartHomeDDD.vo.ValueObject;

public class RoomWidthVO implements ValueObject<Double> {
    private final double roomWidth;

    /**
     * Constructor for RoomWidthVO, it validates if the parameter is valid and creates the object.
     * @param roomWidth Width
     */
    public RoomWidthVO (double roomWidth){
        if (isParamValid(roomWidth)) {
            this.roomWidth = roomWidth;
        }
        else {
            throw new IllegalArgumentException("Room Width has to be higher than zero");
        }
    }

    /**
     * Checks if width is above or equal to zero;
     * @param width Width
     * @return True or False
     */
    private boolean isParamValid(double width){
        if(width <= 0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Simple getter method
     * @return Returns the encapsulated value;
     */
    @Override
    public Double getValue() {
        return this.roomWidth;
    }
}
