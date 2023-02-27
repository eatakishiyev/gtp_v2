package azrc.gtp.ie;

public enum PdnType {
    IPv4(1),
    IPv6(2),
    IPv4v6(3),
    NON_IP(4),
    UNKNOWN(-1);


    private final int value;

    PdnType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static PdnType getInstance(int value) {
        switch (value) {
            case 1:
                return IPv4;
            case 2:
                return IPv6;
            case 3:
                return IPv4v6;
            case 4:
                return NON_IP;
            default:
                return UNKNOWN;
        }
    }
}
