package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class AccessPointNameInformationElement extends InformationElement {
    private String apn;

    @Override
    public InformationElementType getType() {
        return InformationElementType.APN;
    }

    @Override
    public boolean getInstance() {
        return false;
    }

    @Override
    public void setInstance(boolean instance) {

    }

    @Override
    public void decode(InputStream is) throws IOException {
        byte[] data = new byte[is.available()];
        is.read(data);
        this.apn = new String(data).replace(" ", ".");
    }

    @Override
    public byte[] encode() {
        return apn.getBytes();
    }
}
