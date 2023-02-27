package azrc.gtp.ie;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class UserLocationInformationElement extends InformationElement {
    private byte userLocationInfoFlag;
    private byte[] cgi;
    private byte[] sai;
    private byte[] rai;
    private byte[] tai;
    private byte[] ecgi;
    private byte[] lai;
    private byte[] macroeNodeBId;
    private byte[] extendedMacroeNodeBId;


    @Override
    public InformationElementType getType() {
        return InformationElementType.USER_LOCATION_INFO;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.userLocationInfoFlag = (byte) (is.read() & 0b11111111);
        if (isCGIPresent()) {
            cgi = new byte[7];
            is.read(cgi);
        }
        if (isSAIPresent()) {
            sai = new byte[7];
            is.read(sai);
        }
        if (isRAIPresent()) {
            rai = new byte[7];
            is.read(rai);
        }
        if (isTAIPresent()) {
            tai = new byte[5];
            is.read(tai);
        }
        if (isECGIPresent()) {
            ecgi = new byte[7];
            is.read(ecgi);
        }
        if (isLAIPresent()) {
            lai = new byte[5];
            is.read(lai);
        }
        if (isMacroeNodeBIdPresent()) {
            macroeNodeBId = new byte[6];
            is.read(macroeNodeBId);
        }
        if (isExtendedMacroeNodeBIdPresent()) {
            extendedMacroeNodeBId = new byte[6];
            is.read(extendedMacroeNodeBId);
        }
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(userLocationInfoFlag);
        if (cgi != null) {
            baos.write(cgi);
        }
        if (sai != null) {
            baos.write(sai);
        }
        if (rai != null) {
            baos.write(rai);
        }
        if (tai != null) {
            baos.write(tai);
        }
        if (ecgi != null) {
            baos.write(ecgi);
        }
        if (lai != null) {
            baos.write(lai);
        }
        if (macroeNodeBId != null) {
            baos.write(macroeNodeBId);
        }
        if (extendedMacroeNodeBId != null) {
            baos.write(extendedMacroeNodeBId);
        }

        return baos.toByteArray();
    }

    public boolean isExtendedMacroeNodeBIdPresent() {
        return (userLocationInfoFlag & 0b10000000) == 0b10000000;
    }

    public boolean isMacroeNodeBIdPresent() {
        return (userLocationInfoFlag & 0b01000000) == 0b01000000;
    }

    public boolean isLAIPresent() {
        return (userLocationInfoFlag & 0b00100000) == 0b00100000;
    }

    public boolean isECGIPresent() {
        return (userLocationInfoFlag & 0b00010000) == 0b00010000;
    }

    public boolean isTAIPresent() {
        return (userLocationInfoFlag & 0b00001000) == 0b00001000;
    }

    public boolean isRAIPresent() {
        return (userLocationInfoFlag & 0b00000100) == 0b00000100;
    }

    public boolean isSAIPresent() {
        return (userLocationInfoFlag & 0b00000010) == 0b00000010;
    }

    public boolean isCGIPresent() {
        return (userLocationInfoFlag & 0b00000001) == 0b00000001;
    }


}

