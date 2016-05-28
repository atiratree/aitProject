package ait.entity;

/**
 * Created by studamit on 27/05/2016.
 */
public enum CartType {
    TEMPERATURE_MERANO("Temperature :Bolzano", "Here you see the temperature of Bolzano."),
    TEMPERATURE_BOLZANO("Temperature Merano:", "Here you see the temperature of Merano."),
    TEMPERATURE_SELVA_VAL_GARDENA("Temperature: Selva Val Gardena", " temperature of Selva Val Gardena."),
    SALINITY("Salibity of World", "Salinity of world");


    private String label;
    private String description;

    // Constructor
    CartType(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel()
    {
        return label;
    }

    public String getDescription()
    {
        return description;
    }
}


