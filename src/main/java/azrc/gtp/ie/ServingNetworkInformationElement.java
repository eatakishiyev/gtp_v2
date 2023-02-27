package azrc.gtp.ie;

import dev.ocean.gsm.string.utils.bcd.BCDStringUtils;
import lombok.Data;

import java.io.*;
import java.nio.ByteBuffer;

import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.fromTBCDToString;
import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.toTBCDString;

@Data
public class ServingNetworkInformationElement extends InformationElement {
    private String mcc;
    private String mnc;

    @Override
    public InformationElementType getType() {
        return InformationElementType.SERVING_NETWORK;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        byte[] octets = new byte[3];
        is.read(octets);
        StringBuilder sb = new StringBuilder();
        sb.append(octets[0] & 0b00001111);
        sb.append((octets[0] & 0b11110000) >> 4);
        sb.append(octets[1] & 0b00001111);
        this.mcc = sb.toString();

        sb = new StringBuilder();
        sb.append(octets[2] & 0b00001111);
        sb.append((octets[2] & 0b11110000) >> 4);
        if (((octets[1] & 0b11110000)) != 0b11110000) {
            sb.append((octets[1] & 0b11110000) >> 4);
        }
        this.mnc = sb.toString();
    }


    @Override
    public byte[] encode() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(5);

        int octet = nextMCCDigit(0);

        int digit = nextMCCDigit(1);
        octet = octet | ((digit & 0xFF) << 4);

        bb.put((byte) octet);
        octet = 0;

        octet = nextMCCDigit(2);

        digit = nextMNCDigit(3);
        octet = octet | (((digit == -1 ? 0b1111 : digit) & 0xFF) << 4);

        bb.put((byte) octet);
        octet = 0;

        octet = nextMNCDigit(0);

        digit = nextMNCDigit(1);
        octet = octet | (digit << 4);
        bb.put((byte) octet);
        octet = 0;
        bb.flip();
        return bb.array();
    }


    private int nextMCCDigit(int i) {
        String strToken = String.valueOf(mcc.charAt(i));
        return Integer.parseInt(strToken);
    }

    private int nextMNCDigit(int i) {
        try {
            String strToken = String.valueOf(mnc.charAt(i));
            return Integer.parseInt(strToken);
        } catch (StringIndexOutOfBoundsException ex) {
            return -1;
        }
    }
}
