package azrc.gtp.ie;

import azrc.gtp.Utils;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public class AggregateMaximumBitRateInformationElement extends InformationElement {
    private long ambrUplink;
    private long ambrDownlink;

    @Override
    public InformationElementType getType() {
        return InformationElementType.AGGREGATE_MAXIMUM_BIT_RATE;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.ambrUplink = Utils.decode4ByteNumber(is);
        this.ambrDownlink = Utils.decode4ByteNumber(is);
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(8);
        baos.write(Utils.encode4ByteNumber(ambrUplink));
        baos.write(Utils.encode4ByteNumber(ambrDownlink));
        return baos.toByteArray();
    }


}
