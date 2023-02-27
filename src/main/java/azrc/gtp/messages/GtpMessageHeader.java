package azrc.gtp.messages;

import azrc.gtp.Utils;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

@Getter
@Setter
public class GtpMessageHeader implements Encodable, Decodable {
    private int version;
    private int piggyBackingFlag;
    private int teidFlag;
    private int messagePriority;
    private MessageType messageType;
    private int messageLength;
    private int sequenceNumber;
    private Long tunnelEndpointIdentifier;

    @Override
    public void decode(InputStream is) throws IOException {
        if (is.available() <= 0) {
            throw new IOException("Unexpected end of stream");
        }

        int b = is.read() & 0b11111111;
        this.version = ((b & 0b11100000) >> 5);
        this.piggyBackingFlag = ((b & 0b00010000) >> 4);
        this.teidFlag = ((b & 0b00001000) >> 3);
        this.messagePriority = ((b & 0b00000100) >> 2);

        this.messageType = MessageType.getInstance(is.read() & 0b11111111);

        this.messageLength = is.read() & 0b11111111;
        this.messageLength = (messageLength << 8) | (is.read() & 0b11111111);

        if (teidFlag == 1) {
            this.tunnelEndpointIdentifier = Utils.decode4ByteNumber(is);
        }

        this.sequenceNumber = Utils.decode3ByteNumber(is);

        is.read();//spare
    }


    @Override
    public byte[] encode() {
        int b = (version << 5);
        b = b | (piggyBackingFlag << 4);
        b = b | (teidFlag << 3);
        b = b | (messagePriority << 2);

        byte[] teidBytes = Utils.encode4ByteNumber(tunnelEndpointIdentifier);
        byte[] sequenceBytes = Utils.encode3ByteNumber(sequenceNumber);

        return new byte[]{(byte) b, (byte) getMessageType().value(), 0x00/*dummy length*/, 0x00/*dummy length*/, teidBytes[0],
                teidBytes[1], teidBytes[2], teidBytes[3], sequenceBytes[0], sequenceBytes[1], sequenceBytes[2],0x00/*Spare*/};
    }
}
