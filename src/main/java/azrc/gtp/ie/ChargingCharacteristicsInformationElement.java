package azrc.gtp.ie;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

@Data
public class ChargingCharacteristicsInformationElement extends InformationElement {
    private byte[] data;

    @Override
    public InformationElementType getType() {
        return InformationElementType.CHARGING_CHARACTERISTICS;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.data = new byte[is.available()];
        is.read(this.data);
    }

    @Override
    public byte[] encode() {
        return data;
    }
}
