package azrc.gtp.ie;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SelectionModeInformationElement extends InformationElement {
    private SelectionMode selectionMode;

    @Override
    public InformationElementType getType() {
        return InformationElementType.SELECTION_MODE;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b00000011;
        this.selectionMode = SelectionMode.getInstance(b);
    }


    @Override
    public byte[] encode() throws IOException {
        return new byte[]{(byte) selectionMode.value()};
    }
}
