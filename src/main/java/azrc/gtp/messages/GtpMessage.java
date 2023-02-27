package azrc.gtp.messages;

import azrc.gtp.Utils;
import azrc.gtp.ie.GtpInformationElementFactory;
import azrc.gtp.ie.InformationElement;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public abstract class GtpMessage implements Encodable, Decodable {
    private GtpMessageHeader gtpMessageHeader;
    private AtomicInteger sequencer = new AtomicInteger();

    public GtpMessage(int version, int piggyBacking, int priority, Long tunnelEndpointIdentifier, MessageType messageType) {
        GtpMessageHeader gtpMessageHeader = new GtpMessageHeader();
        gtpMessageHeader.setMessagePriority(priority);
        gtpMessageHeader.setVersion(version);
        gtpMessageHeader.setPiggyBackingFlag(piggyBacking);
        gtpMessageHeader.setTunnelEndpointIdentifier(tunnelEndpointIdentifier);
        gtpMessageHeader.setSequenceNumber(getSequence());
        gtpMessageHeader.setMessageType(messageType);
    }


    private int getSequence() {
        int seq = sequencer.getAndIncrement();
        if (seq == Integer.MAX_VALUE) {
            sequencer.getAndSet(0);
        }
        return seq;
    }


    public GtpMessage() {

    }


    private List<InformationElement> informationElements;

    public List<InformationElement> getInformationElements() {
        if (informationElements == null) {
            informationElements = new ArrayList<>();
        }
        return informationElements;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        List<InformationElement> informationElements = this.getInformationElements();

        while (is.available() > 0) {
            InformationElement informationElement = GtpInformationElementFactory.createInformationElement(is);
            if (informationElement != null) {
                informationElements.add(informationElement);
            }
        }
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] header = gtpMessageHeader.encode();
        baos.write(header);

        List<InformationElement> informationElements = this.getInformationElements();
        for (InformationElement informationElement : informationElements) {
            byte[] encodedInformationElement = GtpInformationElementFactory.encodeInformationElement(informationElement);
            //Write encoded information element
            baos.write(encodedInformationElement);
        }
        byte[] encodedLength = Utils.encode2ByteNumber(baos.size() - 4);
        byte[] bytes = baos.toByteArray();
        bytes[2] = encodedLength[0];
        bytes[3] = encodedLength[1];
        return bytes;
    }


}
