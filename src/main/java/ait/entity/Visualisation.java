package ait.entity;

/**
 * Created by studamit on 27/05/2016.
 */
public enum Visualisation {
    TEMPERATURE_BOLZANO("Temperatures in Bolzano", "Minimum, Maximum and Average Temperatures from 1926 until 2015."),
    TEMPERATURE_MERANO("Temperatures in Merano", "Minimum, Maximum and Average Temperatures from 1983 until 2015."),
    TEMPERATURE_SELVA_VAL_GARDENA("Temperatures in Selva Val Gardena", "Minimum, Maximum and Average Temperature from 1991 until 2015."),
    TEMPERATURE_MIN_ALL_PLACES("All minimum temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015."),
    TEMPERATURE_MAX_ALL_PLACES("All maximum temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015."),
    TEMPERATURE_AVG_ALL_PLACES("All average temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015."),
    SALINITY("Salinity of World", "Salinity of world.");


    private String label;
    private String description;

    // Constructor
    Visualisation(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }
}


