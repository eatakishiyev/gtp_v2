package azrc.gtp.ie;

public enum RatType {
    UTRAN(1),
    GERAN(2),
    WLAN(3),
    GAN(4),
    HSPAEvolution(5),
    WB_E_UTRAN(6),
    VIRTUAL(7),
    EUTRAN_NB_IOT(8),
    LTE_M(9),
    NR(10),
    UNKNOWN(-1);

    private final int value;

    RatType(int value) {
        this.value = value;
    }

    public static RatType getInstance(int value) {
        switch (value) {
            case 1:
                return UTRAN;
            case 2:
                return GERAN;
            case 3:
                return WLAN;
            case 4:
                return GAN;
            case 5:
                return HSPAEvolution;
            case 6:
                return WB_E_UTRAN;
            case 7:
                return VIRTUAL;
            case 8:
                return EUTRAN_NB_IOT;
            case 9:
                return LTE_M;
            case 10:
                return NR;
            default:
                return UNKNOWN;
        }
    }

    public int value() {
        return this.value;
    }
}
