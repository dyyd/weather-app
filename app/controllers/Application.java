package controllers;

import models.City;
import models.CityList;
import models.Weather;
import models.WeatherFetcher;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        CityList cl = CityList.getInstance();
        DynamicForm requestData = Form.form().bindFromRequest();
        String zipCode = requestData.get("zip");
        String unit = requestData.get("unit");
        Boolean metric = unit == null ? false : unit.equals("metric");

        Form<City> cityForm = Form.form(City.class);
        City city = new City();
        if(zipCode != null) {
            city = cl.getCityByZip(zipCode);
        }
        Weather weatherData = (new WeatherFetcher()).getWeatherFor(zipCode);
        if(metric){
            weatherData.convertToMetric();
        } else {
            weatherData.convertToImperial();
        }
        return ok(index.render(cl, cityForm, weatherData, city, metric));
    }

}
