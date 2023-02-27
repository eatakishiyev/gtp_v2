package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class IndicationInformationElement extends InformationElement {
    private byte[] flag;

    @Override
    public InformationElementType getType() {
        return InformationElementType.INDICATION;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.flag = new byte[is.available()];
        is.read(flag);
    }


    @Override
    public byte[] encode() throws IOException {
        return flag;
    }
}
