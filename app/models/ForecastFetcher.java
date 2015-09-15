package models;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/**
 * Created by dyyd on 15.09.15.
 */
public class ForecastFetcher extends BaseFetcher  {

    // Get weather forecast info for selected location
    public String getForecastFor(String zip) throws Exception{
        return parseFetchedXML(getDataFor(zip, "GetCityForecastByZIP"));
    }


    private String parseFetchedXML(String xml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));

            doc.getDocumentElement().normalize();
            // TODO: Parse the XML into Forecast objects
        }
        catch (Exception e) {
            System.err.println("Exception parsing XML: " + e.getMessage());
        }

        return "";
    }
}
