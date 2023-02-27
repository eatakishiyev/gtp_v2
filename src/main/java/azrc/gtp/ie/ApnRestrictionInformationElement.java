package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class ApnRestrictionInformationElement extends InformationElement {
    private ApnRestriction apnRestriction;

    @Override
    public InformationElementType getType() {
        return InformationElementType.APN_RESTRICTION;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.apnRestriction = ApnRestriction.getInstance(is.read() & 0b11111111);
    }

    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) apnRestriction.value()};
    }
}
