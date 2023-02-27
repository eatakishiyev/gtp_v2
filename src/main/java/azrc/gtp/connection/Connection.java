package azrc.gtp.connection;

import java.io.IOException;

public abstract class Connection {
    public abstract void startConnection() throws IOException;
    public abstract void stopConnection() throws IOException;
}
