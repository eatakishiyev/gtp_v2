package azrc.gtp.ie;

import dev.ocean.gsm.string.utils.bcd.BCDStringUtils;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
public class TimeZoneInformationElement extends InformationElement {
    private Sign sign;
    private Duration timezone;
    private boolean daylightSaving;

    @Override
    public InformationElementType getType() {
        return InformationElementType.TIMEZONE;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b11111111;
        this.sign = Sign.getInstance((b & 0b00001000) == 0b00001000 ? 1 : 0);
        b = b & 0b11110111;
        this.timezone = Duration.of(((b >> 4) + (b & 0x0f) * 10) * 15, ChronoUnit.MINUTES);

        this.daylightSaving = (is.read() & 0b00000111) == 1;
    }

    @Override
    public byte[] encode() {
        long minutes = timezone.toMinutes() / 15;
        byte b = (byte) (((minutes % 10) << 4) | (minutes / 10));
        if (sign == Sign.NEGATIVE) {
            b |= 0x08;
        }

        return new byte[]{b, (byte) (daylightSaving ? 1 : 0)};
    }
}
