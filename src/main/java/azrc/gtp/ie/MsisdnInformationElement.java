package azrc.gtp.ie;

import dev.ocean.gsm.string.utils.bcd.BCDStringUtils;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.*;

@Data
public class MsisdnInformationElement extends InformationElement {
    private String msisdn;

    @Override
    public InformationElementType getType() {
        return InformationElementType.MSISDN;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.msisdn = fromTBCDToString(is);
    }


    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        toTBCDString(msisdn, baos);
        return baos.toByteArray();
    }
}
