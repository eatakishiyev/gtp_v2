import azrc.gtp.ie.ServingNetworkInformationElement;
import azrc.gtp.messages.GtpMessageHeader;
import azrc.gtp.messages.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

@RunWith(MockitoJUnitRunner.class)
public class GtpMessageHeaderTest {

    @Test
    public void encodeHeader_Test() {
        GtpMessageHeader messageHeader = new GtpMessageHeader();
        messageHeader.setVersion(2);
        messageHeader.setPiggyBackingFlag(0);
        messageHeader.setTeidFlag(1);
        messageHeader.setMessagePriority(0);
        messageHeader.setSequenceNumber(41289);
        messageHeader.setTunnelEndpointIdentifier(108013715l);
        messageHeader.setMessageType(MessageType.CREATE_SESSION_REQUEST);

        byte[] encode = messageHeader.encode();
        assertArrayEquals("Expecting", new byte[]{(byte) 0x48, 0x20, 0x00, 0x00, 0x06, 0x70, 0x28, (byte) 0x93, 0x00, (byte) 0xa1, 0x49}, encode);
    }

    @Test
    public void encodeServingNetwork_Test() throws IOException {
        ServingNetworkInformationElement servingNetworkInformationElement = new ServingNetworkInformationElement();
//        servingNetworkInformationElement.setMcc();
        byte[] encoded = servingNetworkInformationElement.encode();
        assertArrayEquals("Expecting", new byte[]{(byte) 0xf0, 0x20}, encoded);
    }
}
