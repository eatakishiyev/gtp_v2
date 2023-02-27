package azrc.gtp.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Encodable {

    byte[] encode() throws IOException;
}
