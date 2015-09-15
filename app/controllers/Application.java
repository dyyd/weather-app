package controllers;

import models.City;
import models.CityList;
import models.Weather;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        CityList cl = CityList.getInstance();
        DynamicForm requestData = Form.form().bindFromRequest();
        String zipCode = requestData.get("zip");
        Form<City> cityForm = Form.form(City.class);
        City city = new City();
        if(zipCode != null) {
            city = cl.getCityByZip(zipCode);
        }
        Weather weatherData = new Weather(zipCode);
        return ok(index.render(cl, cityForm, weatherData, city));
    }

}
