package azrc.gtp.ie;

import azrc.gtp.Utils;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

@Data
public class FullyQualifiedTunnelEndpointIdentifier extends InformationElement {


    private InterfaceType interfaceType;
    private Long teidGreKey;
    private String ipv4Address;
    private byte[] ipv6Address;


    @Override
    public InformationElementType getType() {
        return InformationElementType.FTEID;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        int b = is.read() & 0b11111111;
        boolean ipV4AddressPresent = ((b & 0b10000000) >> 7) == 1;
        boolean ipV6AddressPresent = ((b & 0b01000000) >> 6) == 1;

        int interfaceType_ = (b & 0b00111111);
        this.interfaceType = InterfaceType.getInstance(interfaceType_);

        this.teidGreKey = Utils.decode4ByteNumber(is);

        if (ipV4AddressPresent) {
            byte[] tmp = new byte[4];
            is.read(tmp);
            ipv4Address = MessageFormat.format("{0}.{1}.{2}.{3}", tmp[0] & 0xff, tmp[1] & 0xff,
                    tmp[2] & 0xff, tmp[3] & 0xff);
        }
        if (ipV6AddressPresent) {
            this.ipv6Address = new byte[is.available()];
            is.read(ipv6Address);
        }
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream tmpBaos = new ByteArrayOutputStream();
        int interfaceTypeByte = 0;
        if (isIpV4AddressPresent()) {
            interfaceTypeByte = 0b10000000;
        }
        if (isIpV6AddressPresent()) {
            interfaceTypeByte = interfaceTypeByte | 0b01000000;
        }

        interfaceTypeByte = interfaceTypeByte | (interfaceType.value() & 0b00111111);
        //Write interface type
        tmpBaos.write(interfaceTypeByte);

        //Write TEID/Gre Key
        tmpBaos.write(Utils.encode4ByteNumber(this.teidGreKey));

        if (isIpV4AddressPresent()) {
            tmpBaos.write(ipv4Address.getBytes());
        }

        if (isIpV6AddressPresent()) {
            tmpBaos.write(ipv6Address);
        }

        return tmpBaos.toByteArray();
    }

    public boolean isIpV4AddressPresent() {
        return ipv4Address != null && !ipv4Address.isEmpty();
    }

    public boolean isIpV6AddressPresent() {
        return ipv6Address != null && ipv6Address.length > 0;
    }

}
