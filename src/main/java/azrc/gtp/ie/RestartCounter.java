package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class RestartCounter extends InformationElement {
    private int restartCounter;

    @Override
    public InformationElementType getType() {
        return InformationElementType.RESTART_COUNTER;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.restartCounter = is.read() & 0b11111111;
    }

    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) restartCounter};
    }
}
