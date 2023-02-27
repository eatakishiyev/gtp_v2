package azrc.gtp.messages;

import lombok.Data;

@Data
public class CreateSessionResponse extends GtpMessage {
    private final MessageType messageType = MessageType.CREATE_SESSION_RESPONSE;

    public CreateSessionResponse(int version, int piggyBacking, int priority, Long tunnelEndpointIdentifier) {
        super(version, piggyBacking, priority, tunnelEndpointIdentifier, MessageType.CREATE_SESSION_RESPONSE);
    }


    public CreateSessionResponse() {
    }
}
