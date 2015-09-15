package models;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;

/**
 * Created by dyyd on 15.09.15.
 */
public class BaseFetcher {

    public static String getDataFor(String zip, String SOAPAction) throws Exception {
        if(zip == null) {
            return "";
        }

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        String url = "http://wsf.cdyne.com/WeatherWS/Weather.asmx";
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(zip, SOAPAction), url);

        // print SOAP Response
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        soapResponse.writeTo(os);
        System.out.print(os.toString());
        soapConnection.close();

        return os.toString("UTF-8");
    }


    private static SOAPMessage createSOAPRequest(String zip, String SOAPAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();


        // SOAP Body
        String baseURI = "http://ws.cdyne.com/WeatherWS/";
        SOAPBody soapBody = envelope.getBody();
        QName bodyElem = new QName(baseURI, SOAPAction);
        SOAPElement soapBodyElem = soapBody.addChildElement(bodyElem);
        SOAPElement zipElem = soapBodyElem.addChildElement("ZIP");
        zipElem.addTextNode(zip);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", baseURI + SOAPAction);
        headers.addHeader("Content-Length", Integer.toString(zip.length()));

        soapMessage.saveChanges();

        return soapMessage;
    }
}
