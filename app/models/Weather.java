package models;

/**
 * Created by dyyd on 14.09.15.
 */
public class Weather {

//     Currently not used but potentially provided by source
//    private String id;
//    private String relativeHumidity;
//    private String wind;
//    private String pressure;
//    private String visibility;
//    private String windChill;
//    private String remarks;

    private String description;
    private Double temperature;
    private Boolean imperial;

    public Weather(String weatherID, String description, String temperature, String relativeHumidity,
                   String wind, String pressure, String visibility, String windChill, String remarks, Boolean imperial) {

//        this.id = weatherID;
//        this.relativeHumidity = relativeHumidity;
//        this.wind = wind;
//        this.pressure = pressure;
//        this.visibility = visibility;
//        this.windChill = windChill;
//        this.remarks = remarks;
        this.description = description;
        this.temperature = Double.parseDouble(temperature);
        this.imperial = imperial;
    }

    public Weather() {
        this.imperial = false;
    }

    public String toString() {
        if(description == null)
            return "No weather data.";
        // TODO: Find better way to build the output string
        String returnString = "";
        returnString += description;
        returnString += ", ";
        returnString += temperatureInUnits();

        return returnString;
    }

    public void convertToMetric() {
        if(!imperial)
            return;
        imperial = false;
        temperature = (5.0/9.0) * (temperature - 32.0);
    }

    public void convertToImperial() {
        if(imperial) {
            return;
        }
        imperial = true;

    }

    private String temperatureInUnits() {
        String unit = imperial ? "F" : "C";
        return String.format("%.0f", temperature) + unit;
    }

}
