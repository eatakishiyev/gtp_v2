package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class Cause extends InformationElement {
    private int causeValue;
    private int flag;

    @Override
    public InformationElementType getType() {
        return null;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.causeValue = is.read() & 0b11111111;
        this.flag = is.read() & 0b11111111;
    }

    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) causeValue, (byte) flag};
    }
}
