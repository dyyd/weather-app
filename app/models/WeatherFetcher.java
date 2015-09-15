package models;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by dyyd on 15.09.15.
 */
public class WeatherFetcher extends BaseFetcher {

    public String getWeatherFor(String zip) throws Exception{
        return parseFetchedXML(getDataFor(zip, "GetCityWeatherByZIP"));
    }


    private String parseFetchedXML(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));

            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            System.out.println(doc.getElementsByTagName("WeatherReturn").item(0));
            System.out.println(doc.getElementsByTagName("Description").item(0));
            System.out.println(doc.getElementsByTagName("Temperature").item(0));
            NodeList nl = doc.getChildNodes();
            for (int count = 0; count < nl.getLength(); count++) {
                Node tempNode = nl.item(count);
                System.out.println(tempNode.getNodeName());
                NodeList nls = tempNode.getChildNodes();
                for (int count2 = 0; count2 < nls.getLength(); count2++) {
                    Node tempNode2 = nls.item(count2);
                    System.out.println(tempNode2.getNodeName());
                    NodeList nlsb = tempNode2.getChildNodes();
                    for (int count3 = 0; count3 < nlsb.getLength(); count3++) {
                        Node tempNode3 = nlsb.item(count3);
                        System.out.println(tempNode3.getNodeName());
                    }
                }
            }

            return doc.getElementById("WeatherReturn") + " - " + doc.getElementById("Temperature") + "F";

        }
        catch (Exception e) {
            System.err.println("Exception parsing XML: " + e.getMessage());
        }

        return "";
    }

}
