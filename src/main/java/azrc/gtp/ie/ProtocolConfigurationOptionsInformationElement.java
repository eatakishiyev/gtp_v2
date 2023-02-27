package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class ProtocolConfigurationOptionsInformationElement extends InformationElement {
    private byte[] data;

    @Override
    public InformationElementType getType() {
        return InformationElementType.PROTOCOL_CONFIGURATION_OPTIONS;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.data = new byte[is.available()];
        is.read(data);
    }


    @Override
    public byte[] encode() throws IOException {
        return data;
    }
}
