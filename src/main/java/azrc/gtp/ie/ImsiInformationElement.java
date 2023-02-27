package azrc.gtp.ie;

import dev.ocean.gsm.string.utils.bcd.BCDStringUtils;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.fromTBCDToString;

@Data
public class ImsiInformationElement extends InformationElement {
    private String imsi;

    @Override
    public InformationElementType getType() {
        return InformationElementType.IMSI;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.imsi = fromTBCDToString(is);
    }


    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BCDStringUtils.toTBCDString(imsi, baos);
        return baos.toByteArray();
    }
}
