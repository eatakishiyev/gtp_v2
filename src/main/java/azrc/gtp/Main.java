package azrc.gtp;

import azrc.gtp.connection.UdpConnection;
import azrc.gtp.messages.GtpMessage;
import azrc.gtp.messages.GtpMessageFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static HashMap<String, Integer> subscriberQueue = new HashMap<>();

    public static void main(String[] args) throws IOException {
        UdpConnection udpConnection = new UdpConnection();
        udpConnection.setLocalPort(5001);
        udpConnection.setLocalAddress("192.168.1.5");
        udpConnection.setRemotePort(5000);
        udpConnection.setRemoteAddress("192.168.1.5");
        udpConnection.startConnection();


        byte[] bytes = DatatypeConverter.parseHexBinary("4824002d067028930076db0049000100064d00030008000056000d001804f020402504f0200649bf05aa000400e79889a5");
        GtpMessage message = GtpMessageFactory.createMessage(new ByteArrayInputStream(bytes));

        ByteBuffer bb = ByteBuffer.allocate(1024);
        bb.put(message.encode());
        bb.flip();
        udpConnection.getDatagramChannel().send(bb,new InetSocketAddress("192.168.1.5",5000));

        byte[] tmp = new byte[]{0x0a, 0x40, (byte) 0xa5, 0x74};

        System.out.println(MessageFormat.format("{0}.{1}.{2}.{3}", tmp[0] & 0xff, tmp[1] & 0xff,
                tmp[2] & 0xff, tmp[3] & 0xff));

    }

}
