package azrc.gtp.ie;

public enum SelectionMode {
    MS_OR_NETWORK_PROVIDED(0),
    MS_PROVIDED(1),
    NETWORK_PROVIDED(2),
    RESERVED(3),
    UNKNOWN(-1);

    private final int value;

    SelectionMode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static SelectionMode getInstance(int value) {
        switch (value) {
            case 0:
                return MS_OR_NETWORK_PROVIDED;
            case 1:
                return MS_PROVIDED;
            case 2:
                return NETWORK_PROVIDED;
            case 3:
                return RESERVED;
            default:
                return UNKNOWN;
        }
    }
}
