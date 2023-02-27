package azrc.gtp.ie;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.fromTBCDToString;
import static dev.ocean.gsm.string.utils.bcd.BCDStringUtils.toTBCDString;

@Data
public class MobileEquipmentIdentityInformationElement extends InformationElement {
    private String imei;

    @Override
    public InformationElementType getType() {
        return InformationElementType.MOBILE_EQUIPMENT_IDENTITY;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.imei = fromTBCDToString(is);
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        toTBCDString(imei, baos);
        return baos.toByteArray();
    }
}
