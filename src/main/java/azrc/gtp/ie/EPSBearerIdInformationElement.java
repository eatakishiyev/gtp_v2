package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class EPSBearerIdInformationElement extends InformationElement {
    private int epsBearerId;

    @Override
    public InformationElementType getType() {
        return InformationElementType.EPS_BEARER_ID;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.epsBearerId = is.read() & 0b00001111;
    }

    @Override
    public byte[] encode() {
        return new byte[]{(byte) epsBearerId};
    }
}
