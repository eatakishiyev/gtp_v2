package azrc.gtp.ie;

import azrc.gtp.Utils;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class UliTimestampInformationElement extends InformationElement {
    private Instant timestamp;

    @Override
    public InformationElementType getType() {
        return InformationElementType.UL_TIMESTAMP;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        long secondsSince1900 = Utils.decode4ByteNumber(is);
        long betweenMillis = ChronoUnit.MILLIS.between(Instant.parse("1900-01-01T00:00:00.00Z"),
                Instant.parse("1970-01-01T00:00:00.00Z"));
        long seconds = secondsSince1900 - betweenMillis / 1000;
        timestamp = Instant.ofEpochSecond(seconds);
    }

    @Override
    public byte[] encode() throws IOException {
        long betweenSeconds = ChronoUnit.MILLIS.between(Instant.parse("1900-01-01T00:00:00.00Z"), timestamp)/1000;
        return Utils.encode4ByteNumber(betweenSeconds);
    }
}
