package models;

/**
 * Created by dyyd on 14.09.15.
 */
public class Weather {
    private String zip;
    public Weather(String zip) {
        this.zip = zip;
        // TODO: Use the ZIP to fetch weather data
    }

    public String toString() {
        WeatherFetcher wf = new WeatherFetcher();
        String weatherInfo = "";
        try {
            System.out.println("Fetching from toString");
            weatherInfo = wf.getWeatherFor(zip);
        }
        catch (Exception e){
            System.err.println("Caught Exception: " + e.getMessage());
        };

        return weatherInfo;
    }

}
