package models;

import play.data.DynamicForm;

/**
 * Created by dyyd on 15.09.15.
 *
 * General index page manager that deals with providing data to view.
 */
public class IndexViewManager {
    private CityList cityList;
    private DynamicForm requestData;

    public IndexViewManager(DynamicForm input) {
        this.cityList = CityList.getInstance();
        this.requestData = input;
    }

    public Boolean newPage() {
        return requestData.get("zip") == null;
    }

    public City[] getCities() {
        return cityList.getCities();
    }

    public String getSelectedZip() {
        String rsp = "";
        rsp += requestData.get("zip");
        return rsp;
    }

    public Boolean metric() {
        String unit = requestData.get("unit");
        if(unit == null) {
            return false;
        } else {
            return unit.equals("metric");
        }
    }
    public Boolean imperial() {
        return !metric();
    }

    public City selectedCity() {
        City city = new City();

        if(getSelectedZip() != null) {
            city = cityList.getCityByZip(getSelectedZip());
        }
        return city;
    }

    public String weatherDataString() {
        Weather weather = (new WeatherFetcher()).getWeatherFor(getSelectedZip());
        if(metric()){
            weather.convertToMetric();
        } else {
            weather.convertToImperial();
        }
        return weather.toString();
    }

}
