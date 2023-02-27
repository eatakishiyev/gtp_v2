package azrc.gtp.ie;

import azrc.gtp.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GtpInformationElementFactory {
    public static byte[] encodeInformationElement(InformationElement informationElement) throws IOException {
        byte[] encoded = informationElement.encode();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //Write information element type
        baos.write(informationElement.getType().value());
        //Write information element length
        baos.write(Utils.encode2ByteNumber(encoded.length));
        //Write flags
        int flags = 0;
        flags = flags | ((informationElement.getInstance() ? 1 : 0) & 0b00001111);
        baos.write(flags);

        baos.write(encoded);
        return baos.toByteArray();
    }

    public static InformationElement createInformationElement(InputStream is) throws IOException {
        int b = is.read() & 0b11111111;
        InformationElementType informationElementType = InformationElementType.getInstance(b);
        InformationElement informationElement = null;
        switch (informationElementType) {
            case IMSI:
                informationElement = new ImsiInformationElement();
                break;
            case RESTART_COUNTER:
                informationElement = new RestartCounter();
                break;
            case CAUSE:
                informationElement = new Cause();
                break;
            case APN:
                informationElement = new AccessPointNameInformationElement();
                break;
            case AGGREGATE_MAXIMUM_BIT_RATE:
                informationElement = new AggregateMaximumBitRateInformationElement();
                break;
            case INDICATION:
                informationElement = new IndicationInformationElement();
                break;
            case EPS_BEARER_ID:
                informationElement = new EPSBearerIdInformationElement();
                break;
            case MOBILE_EQUIPMENT_IDENTITY:
                informationElement = new MobileEquipmentIdentityInformationElement();
                break;
            case MSISDN:
                informationElement = new MsisdnInformationElement();
                break;
            case BEARER_LEVEL_QOS:
                informationElement = new BearerLevelQosInformationElement();
                break;
            case RATTYPE:
                informationElement = new RatTypeInformationElement();
                break;
            case SERVING_NETWORK:
                informationElement = new ServingNetworkInformationElement();
                break;
            case USER_LOCATION_INFO:
                informationElement = new UserLocationInformationElement();
                break;
            case FTEID:
                informationElement = new FullyQualifiedTunnelEndpointIdentifier();
                break;
            case BEARER_CONTEXT:
                informationElement = new BearerContextInformationElement();
                break;
            case PDN_TYPE:
                informationElement = new PdnTypeInformationElement();
                break;
            case CHARGING_CHARACTERISTICS:
                informationElement = new ChargingCharacteristicsInformationElement();
                break;
            case PDN_ADDRESS_ALLOCATION:
                informationElement = new PdnAddressAllocationInformationElement();
                break;
            case PROTOCOL_CONFIGURATION_OPTIONS:
                informationElement = new ProtocolConfigurationOptionsInformationElement();
                break;
            case TIMEZONE:
                informationElement = new TimeZoneInformationElement();
                break;
            case APN_RESTRICTION:
                informationElement = new ApnRestrictionInformationElement();
                break;
            case SELECTION_MODE:
                informationElement = new SelectionModeInformationElement();
                break;
            case UL_TIMESTAMP:
                informationElement = new UliTimestampInformationElement();
                break;
        }

        int length = Utils.decode2ByteNumber(is);

        int flag = is.read() & 0b11111111;

        byte[] data = new byte[length];
        is.read(data);

        if (informationElement != null) {

            informationElement.decode(new ByteArrayInputStream(data));
            informationElement.setInstance((flag & 0b00001111) > 0);
        }

        return informationElement;
    }
}
