package azrc.gtp.ie;

public enum InformationElementType {
    IMSI(1),
    CAUSE(2),
    RESTART_COUNTER(3),
    APN(71),
    AGGREGATE_MAXIMUM_BIT_RATE(72),
    EPS_BEARER_ID(73),
    MOBILE_EQUIPMENT_IDENTITY(75),
    MSISDN(76),
    INDICATION(77),
    PROTOCOL_CONFIGURATION_OPTIONS(78),
    PDN_ADDRESS_ALLOCATION(79),
    BEARER_LEVEL_QOS(80),
    RATTYPE(82),
    SERVING_NETWORK(83),
    USER_LOCATION_INFO(86),
    FTEID(87),

    BEARER_CONTEXT(93),
    CHARGING_CHARACTERISTICS(95),
    PDN_TYPE(99),
    TIMEZONE(114),
    APN_RESTRICTION(127),
    SELECTION_MODE(128),
    UL_TIMESTAMP(170),
    UNKNOWN(-1);

    private final int value;

    InformationElementType(int value) {
        this.value = value;
    }

    public static InformationElementType getInstance(int value) {
        switch (value) {
            case 1:
                return IMSI;
            case 2:
                return CAUSE;
            case 3:
                return RESTART_COUNTER;
            case 71:
                return APN;
            case 72:
                return AGGREGATE_MAXIMUM_BIT_RATE;
            case 73:
                return EPS_BEARER_ID;
            case 75:
                return MOBILE_EQUIPMENT_IDENTITY;
            case 76:
                return MSISDN;
            case 77:
                return INDICATION;
            case 78:
                return PROTOCOL_CONFIGURATION_OPTIONS;
            case 79:
                return PDN_ADDRESS_ALLOCATION;
            case 80:
                return BEARER_LEVEL_QOS;
            case 82:
                return RATTYPE;
            case 83:
                return SERVING_NETWORK;
            case 86:
                return USER_LOCATION_INFO;
            case 87:
                return FTEID;
            case 93:
                return BEARER_CONTEXT;
            case 95:
                return CHARGING_CHARACTERISTICS;
            case 99:
                return PDN_TYPE;
            case 114:
                return TIMEZONE;
            case 127:
                return APN_RESTRICTION;
            case 128:
                return SELECTION_MODE;
            case 170:
                return UL_TIMESTAMP;
            default:
                return UNKNOWN;
        }
    }

    public int value() {
        return value;
    }
}
