package models;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by dyyd on 15.09.15.
 */
public class WeatherFetcher extends BaseFetcher {

    public Weather getWeatherFor(String zip) {
        String xml = "";
        try {
            xml = getDataFor(zip, "GetCityWeatherByZIP");
        }
        catch (Exception e) {
            // TODO: Log this
            System.err.println(e.getMessage());
        }
        return parseFetchedXML(xml);
    }


    private Weather parseFetchedXML(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));

            doc.getDocumentElement().normalize();

            Element elm = (Element) doc.getElementsByTagName("GetCityWeatherByZIPResult").item(0);

            Weather weather = new Weather(
                    elm.getElementsByTagName("WeatherID").item(0).getTextContent(),
                    elm.getElementsByTagName("Description").item(0).getTextContent(),
                    elm.getElementsByTagName("Temperature").item(0).getTextContent(),
                    elm.getElementsByTagName("RelativeHumidity").item(0).getTextContent(),
                    elm.getElementsByTagName("Wind").item(0).getTextContent(),
                    elm.getElementsByTagName("Pressure").item(0).getTextContent(),
                    elm.getElementsByTagName("Visibility").item(0).getTextContent(),
                    elm.getElementsByTagName("WindChill").item(0).getTextContent(),
                    elm.getElementsByTagName("Remarks").item(0).getTextContent(),
                    true //Raw data is in Imperial units
            );

            return weather;

        }
        catch (Exception e) {
            // TODO: Log this
            System.err.println("Exception parsing XML: " + e.getMessage());
        }

        return new Weather();
    }

}
