package azrc.gtp.ie;

public enum ApnRestriction {
    NO_EXISTING_CONTEXTS_OR_RESTRICTIONS(0),
    PUBLIC_1(1),
    PUBLIC_2(2),
    PRIVATE_1(3),
    PRIVATE_2(4),
    UNKNOWN(-1);

    private final int value;

    ApnRestriction(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static ApnRestriction getInstance(int value) {
        switch (value) {
            case 0:
                return NO_EXISTING_CONTEXTS_OR_RESTRICTIONS;
            case 1:
                return PUBLIC_1;
            case 2:
                return PUBLIC_2;
            case 3:
                return PRIVATE_1;
            case 4:
                return PRIVATE_2;
            default:
                return UNKNOWN;
        }
    }
}
