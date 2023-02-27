package azrc.gtp.ie;

import azrc.gtp.messages.Decodable;
import azrc.gtp.messages.Encodable;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class InformationElement implements Encodable, Decodable {
    private boolean instance;

    public abstract InformationElementType getType();

    public boolean getInstance() {
        return instance;
    }

    public void setInstance(boolean instance) {
        this.instance = instance;
    }

}
