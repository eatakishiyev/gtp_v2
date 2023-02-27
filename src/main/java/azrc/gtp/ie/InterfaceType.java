package azrc.gtp.ie;

public enum InterfaceType {
    S1UeNodeB(0),
    S1USGW(1),
    S12RNC(2),
    S12SGW(3),
    S5_S8_SGW_GTP_U(4),
    S5_S8_PGW_GTP_U(5),
    S5_S8_SGW_GTP_C(6),
    S5_S8_PGW_GTP_C(7),
    S11_MME_GTP_C(10),
    UNKNOWN(-1);


    private final int value;


    InterfaceType(int value) {
        this.value = value;
    }

    public static InterfaceType getInstance(int value) {
        switch (value) {
            case 0:
                return S1UeNodeB;
            case 1:
                return S1USGW;
            case 2:
                return S12RNC;
            case 3:
                return S12SGW;
            case 4:
                return S5_S8_SGW_GTP_U;
            case 5:
                return S5_S8_PGW_GTP_U;
            case 6:
                return S5_S8_SGW_GTP_C;
            case 7:
                return S5_S8_PGW_GTP_C;
            case 10:
                return S11_MME_GTP_C;
            default:
                return UNKNOWN;
        }
    }

    public int value() {
        return value;
    }
}
