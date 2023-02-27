package azrc.gtp.messages;

import lombok.Data;

@Data
public class DeleteSessionRequest extends GtpMessage {
    private final MessageType messageType = MessageType.DELETE_SESSION_REQUEST;

    public DeleteSessionRequest(int version, int piggyBacking, int priority, Long tunnelEndpointIdentifier) {
        super(version, piggyBacking, priority, tunnelEndpointIdentifier, MessageType.DELETE_SESSION_REQUEST);
    }

    public DeleteSessionRequest() {
    }
}
