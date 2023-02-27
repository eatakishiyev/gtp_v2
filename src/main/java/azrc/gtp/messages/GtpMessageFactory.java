package azrc.gtp.messages;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GtpMessageFactory {

    public static GtpMessage createMessage(InputStream is) throws IOException {
        GtpMessageHeader gtpMessageHeader = new GtpMessageHeader();
        gtpMessageHeader.decode(is);

        int messageLength = gtpMessageHeader.getMessageLength();
        if (gtpMessageHeader.getTeidFlag() == 1) {
            messageLength -= 4;
        }
        messageLength -= 4;
        byte[] data = new byte[messageLength];
        is.read(data);
        GtpMessage gtpMessage = null;
        switch (gtpMessageHeader.getMessageType()) {
            case CREATE_SESSION_REQUEST:
                gtpMessage = new CreateSessionRequest();
                break;
            case CREATE_SESSION_RESPONSE:
                gtpMessage = new CreateSessionResponse();
                break;
            case DELETE_SESSION_REQUEST:
                gtpMessage = new DeleteSessionRequest();
                break;
            case DELETE_SESSION_RESPONSE:
                gtpMessage = new DeleteSessionResponse();
                break;
        }

        if (gtpMessage != null) {
            gtpMessage.decode(new ByteArrayInputStream(data));
            gtpMessage.setGtpMessageHeader(gtpMessageHeader);
        }

        return gtpMessage;
    }
}
