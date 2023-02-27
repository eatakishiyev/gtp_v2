package azrc.gtp.messages;

import java.io.IOException;
import java.io.InputStream;

public interface Decodable {
    void decode(InputStream is) throws IOException;
}
