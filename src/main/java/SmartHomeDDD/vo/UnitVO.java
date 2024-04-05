package SmartHomeDDD.vo;

public class UnitVO implements ValueObject<String> {
    private final String unit;

    /**
     * Constructor for UnitVO
     *
     * @param unit
     * @return UnitVO
     * @throws IllegalArgumentException
     */
    public UnitVO(String unit) {
        if (unit == null || unit.isEmpty()) throw new IllegalArgumentException("Invalid parameters");
        this.unit = unit;
    }

    /**
     * Get the unit
     *
     * @return Unit as String
     */
    public String getUnit() {
        return unit;
    }

    @Override
    public String getValue() {
        return unit;
    }
}
