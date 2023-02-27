package azrc.gtp.ie;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public class PdnAddressAllocationInformationElement extends InformationElement {
    private PdnType pdnType;
    private byte[] address;

    @Override
    public InformationElementType getType() {
        return InformationElementType.PDN_ADDRESS_ALLOCATION;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b00000111;
        this.pdnType = PdnType.getInstance(b);
        this.address = new byte[is.available()];
        is.read(address);
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(pdnType.value());
        baos.write(address);
        return baos.toByteArray();
    }
}
