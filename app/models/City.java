package models;

/**
 * Created by dyyd on 14.09.15.
 */
public class City {
    private String name;
    private String zip;

    public City(String name, String zip) {
        this.name = name;
        this.zip = zip;
    }

    public City() {
        this.name = "";
        this.zip = "";
    }

    public String getZip() {
        return new String(zip);
    }

    public String getName() {
        return new String(name);
    }
}
