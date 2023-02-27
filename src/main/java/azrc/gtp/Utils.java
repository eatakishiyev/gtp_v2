package azrc.gtp;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static long decode4ByteNumber(InputStream is) throws IOException {
        long number = is.read() & 0b11111111;
        number = (number << 8) | (is.read() & 0b11111111);
        number = (number << 8) | (is.read() & 0b11111111);
        number = (number << 8) | (is.read() & 0b11111111);
        return number;
    }

    public static byte[] encode4ByteNumber(long number) {
        byte[] octets = new byte[4];
        octets[0] = (byte) ((number >> 24) & 0b11111111);
        octets[1] = (byte) ((number >> 16) & 0b11111111);
        octets[2] = (byte) ((number >> 8) & 0b11111111);
        octets[3] = (byte) ((number) & 0b11111111);
        return octets;
    }

    public static byte[] encode2ByteNumber(int number) {
        byte[] octets = new byte[2];
        octets[0] = (byte) ((number >> 8) & 0b11111111);
        octets[1] = (byte) (number & 0b11111111);
        return octets;
    }

    public static int decode2ByteNumber(InputStream is) throws IOException {
        int number = is.read() & 0b11111111;
        number = (number << 8) | (is.read() & 0b11111111);
        return number;
    }

    public static long decode5ByteNumber(InputStream is) throws IOException {
        long number = is.read() & 0b11111111;
        number = (number << 8) | (is.read() & 0b11111111);
        number = (number << 8) | (is.read() & 0b11111111);
        number = (number << 8) | (is.read() & 0b11111111);
        number = (number << 8) | (is.read() & 0b11111111);
        return number;
    }

    public static byte[] encode5ByteNumber(long number) {
        byte[] octets = new byte[5];
        octets[0] = (byte) ((number >> 32) & 0b11111111);
        octets[1] = (byte) ((number >> 24) & 0b11111111);
        octets[2] = (byte) ((number >> 18) & 0b11111111);
        octets[3] = (byte) ((number >> 8) & 0b11111111);
        octets[4] = (byte) ((number) & 0b11111111);
        return octets;
    }

    public static int decode3ByteNumber(InputStream is) throws IOException {
        int sequenceNumber = is.read() & 0b11111111;
        sequenceNumber = (sequenceNumber << 8) | (is.read() & 0b11111111);
        sequenceNumber = (sequenceNumber << 8) | (is.read() & 0b11111111);
        return sequenceNumber;
    }

    public static byte[] encode3ByteNumber(int number) {
        byte[] octets = new byte[3];
        octets[0] = (byte) ((number >> 18) & 0b11111111);
        octets[1] = (byte) ((number >> 8) & 0b11111111);
        octets[2] = (byte) ((number) & 0b11111111);
        return octets;
    }
}
