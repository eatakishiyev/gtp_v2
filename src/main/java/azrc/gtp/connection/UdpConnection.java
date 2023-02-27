package azrc.gtp.connection;

import lombok.Data;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.channels.DatagramChannel;

@Data
public class UdpConnection extends Connection {
    private DatagramChannel datagramChannel;
    private String localAddress;
    private int localPort;
    private String remoteAddress;
    private int remotePort;

    @Override
    public void startConnection() throws IOException {
        if (datagramChannel == null || !(datagramChannel.isConnected() || datagramChannel.isOpen())) {
            this.datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
            datagramChannel.bind(new InetSocketAddress(localAddress, localPort));
            datagramChannel.connect(new InetSocketAddress(remoteAddress, remotePort));
        }
    }

    @Override
    public void stopConnection() throws IOException {
        if (this.datagramChannel != null & datagramChannel.isConnected()) {
            datagramChannel.disconnect();
            datagramChannel.close();
        }
    }
}
