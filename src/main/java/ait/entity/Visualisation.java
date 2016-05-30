package ait.entity;

/**
 * Created by studamit on 27/05/2016.
 */
public enum Visualisation {
    MONTH_TEMPERATURE_BOLZANO("Average Temperatures in Bolzano for selected month", "Temperatures from 1926 until 2015.", "Bar Chart"),
    MONTH_TEMPERATURE_MERANO("Average Temperatures in Merano for selected month", "Average Temperatures from 1926 until 2015.", "Bar Chart"),
    MONTH_TEMPERATURE_SELVA_VAL_GARDENA("Average Temperatures in Selva Val Gardena for selected month", "Average Temperatures from 1926 until 2015.", "Bar Chart"),
    TEMPERATURE_BOLZANO("Temperatures in Bolzano", "Minimum, Maximum and Average Temperatures from 1926 until 2015.", "Line Chart"),
    TEMPERATURE_MERANO("Temperatures in Merano", "Minimum, Maximum and Average Temperatures from 1983 until 2015.", "Line Chart"),
    TEMPERATURE_SELVA_VAL_GARDENA("Temperatures in Selva Val Gardena", "Minimum, Maximum and Average Temperature from 1991 until 2015.", "Line Chart"),
    TEMPERATURE_MIN_ALL_PLACES("All minimum temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015.", "Line Chart"),
    TEMPERATURE_MAX_ALL_PLACES("All maximum temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015.", "Line Chart"),
    TEMPERATURE_AVG_ALL_PLACES("All average temperatures", "Temperatures in Bolzano, Merano and Selva Val Gardena from 1926 until 2015.", "Line Chart");


    private String label;
    private String description;
    private String typeOfGraph;

    // Constructor
    Visualisation(String label, String description, String typeOfGraph) {
        this.label = label;
        this.description = description;
        this.typeOfGraph = typeOfGraph;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeOfGraph() {
        return typeOfGraph;
    }
}


