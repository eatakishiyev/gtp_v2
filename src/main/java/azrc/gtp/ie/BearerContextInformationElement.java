package azrc.gtp.ie;

import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Data
public class BearerContextInformationElement extends InformationElement {
    private List<InformationElement> informationElements;

    @Override
    public InformationElementType getType() {
        return InformationElementType.BEARER_CONTEXT;
    }

    @Override
    public void decode(InputStream is) throws IOException {
        this.informationElements = new ArrayList<>();
        while (is.available() > 0) {
            InformationElement informationElement = GtpInformationElementFactory.createInformationElement(is);
            informationElements.add(informationElement);
        }
    }

    @Override
    public byte[] encode() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (InformationElement informationElement : informationElements) {
            baos.write(GtpInformationElementFactory.encodeInformationElement(informationElement));
        }

        return baos.toByteArray();
    }
}
