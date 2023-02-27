package azrc.gtp.ie;

public enum Sign {
    POSITIVE(0),
    NEGATIVE(1);

    private final int value;

    Sign(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static Sign getInstance(int value) {
        switch (value) {
            case 0:
                return POSITIVE;
            default:
                return NEGATIVE;
        }
    }
}
