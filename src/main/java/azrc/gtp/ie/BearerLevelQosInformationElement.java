package azrc.gtp.ie;

import azrc.gtp.Utils;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Data
public class BearerLevelQosInformationElement extends InformationElement {
    private boolean preEmptionCapability;
    private byte priorityLevel;
    private boolean preEmptionVulnerability;
    private byte qci;
    private long maxBitRateForUplink;
    private long maxBitRateForDownlink;
    private long guaranteedBitRateForUplink;
    private long guaranteedBitRateForDownlink;


    @Override
    public InformationElementType getType() {
        return InformationElementType.BEARER_LEVEL_QOS;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b11111111;
        this.preEmptionCapability = ((b & 0b01000000) >> 6) == 0;
        this.priorityLevel = (byte) ((b & 0b00111100) >> 2);
        this.preEmptionVulnerability = ((b & 0b00000001)) == 0;

        this.qci = (byte) (is.read() & 0b11111111);

        this.maxBitRateForUplink = Utils.decode5ByteNumber(is);
        this.maxBitRateForDownlink = Utils.decode5ByteNumber(is);
        this.guaranteedBitRateForUplink = Utils.decode5ByteNumber(is);
        this.guaranteedBitRateForDownlink = Utils.decode5ByteNumber(is);
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(22);
        int b = 0;//spare
        b = (preEmptionCapability ? 0 : 1) << 6;
        b = (priorityLevel << 2) | b;
        b = b | (preEmptionVulnerability ? 0 : 1);
        baos.write(b);

        baos.write(qci);

        baos.write(Utils.encode5ByteNumber(maxBitRateForUplink));
        baos.write(Utils.encode5ByteNumber(maxBitRateForDownlink));
        baos.write(Utils.encode5ByteNumber(guaranteedBitRateForUplink));
        baos.write(Utils.encode5ByteNumber(guaranteedBitRateForDownlink));

        return baos.toByteArray();
    }


}
