package SmartHome.vo;

public class HeightVO implements ValueObject{
    private final double height;

    public HeightVO(double height) {
        if (!validateHeight(height)) {
            throw new IllegalArgumentException("Invalid height value");
        }
        this.height = height;
    }

    private boolean validateHeight(double height) {
        return height >= 0;
    }

    public double getHeight() {
        return this.height;
    }

}
