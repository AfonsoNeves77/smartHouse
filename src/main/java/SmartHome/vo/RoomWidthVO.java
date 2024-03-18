package SmartHome.vo;

public class RoomWidthVO {
    private double roomWidth;

    public RoomWidthVO (double roomWidth){
        if (isParamValid(roomWidth)) {
            this.roomWidth = roomWidth;
        }
        else {
            throw new IllegalArgumentException("Room Width has to be higher than zero");
        }
    }
    private boolean isParamValid(double width){
        if(width <= 0){
            return false;
        }
        else{
            return true;
        }
    }
    public double getRoomWidth(){
        return this.roomWidth;
    }
}
