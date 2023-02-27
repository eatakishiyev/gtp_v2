package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class RatTypeInformationElement extends InformationElement {
    private RatType ratType;

    @Override
    public InformationElementType getType() {
        return InformationElementType.RATTYPE;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b11111111;
        this.ratType = RatType.getInstance(b);
    }


    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) ratType.value()};
    }
}
