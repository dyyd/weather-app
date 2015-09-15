package controllers;

import models.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        IndexViewManager ivm = new IndexViewManager(Form.form().bindFromRequest());
        return ok(index.render(ivm));
    }

    public Result postForm() {
        IndexViewManager ivm = new IndexViewManager(Form.form().bindFromRequest());
        return ok(index.render(ivm));
    }

    public Result refreshWeather() {
        IndexViewManager ivm = new IndexViewManager(Form.form().bindFromRequest());
        return ok(weatherInfo.render(ivm));
    }

}
