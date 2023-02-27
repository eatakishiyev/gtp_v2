package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class PdnTypeInformationElement extends InformationElement {
    private PdnType pdnType;

    @Override
    public InformationElementType getType() {
        return InformationElementType.PDN_TYPE;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b00000111;
        this.pdnType = PdnType.getInstance(b);
    }


    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) pdnType.value()};
    }
}
