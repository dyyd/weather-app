package models;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by dyyd on 14.09.15.
 */
public class CityList {
    private static CityList instance = null;
    private City[] cities;

    public static CityList getInstance() {
        if(instance == null) {
            instance = new CityList();
        }
        return instance;
    }

    public CityList() {
        String fileName = "conf/city_list.json";
        try {
            String jsonString =  new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
            Gson g = new Gson();
            cities = g.fromJson(jsonString, City[].class);
        }
        catch(FileNotFoundException ex) {
            // TODO: Log this
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            // TODO: Log this
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    public City[] getCities() {
        return cities;
    }

    public City getCityByZip(String zip) {
        for (City c : cities) {
            if (c.getZip().equals(zip)) {
                return c;
            }
        }
        return new City();
    }
}
