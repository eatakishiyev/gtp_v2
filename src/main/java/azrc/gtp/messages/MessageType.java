package azrc.gtp.messages;

public enum MessageType {
    CREATE_SESSION_REQUEST(32),
    CREATE_SESSION_RESPONSE(33),
    DELETE_SESSION_REQUEST(36),
    DELETE_SESSION_RESPONSE(37),
    UNKNOWN(-1);
    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public static MessageType getInstance(int messageType) {
        switch (messageType) {
            case 32:
                return CREATE_SESSION_REQUEST;
            case 33:
                return CREATE_SESSION_RESPONSE;
            case 36:
                return DELETE_SESSION_REQUEST;
            case 37:
                return DELETE_SESSION_RESPONSE;
            default:
                return UNKNOWN;
        }
    }

    public int value() {
        return this.value;
    }

}
