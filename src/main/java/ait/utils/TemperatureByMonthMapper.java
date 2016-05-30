package ait.utils;

import ait.entity.Temperature.Place;
import ait.entity.Visualisation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suomiy on 29/05/16.
 */
public class TemperatureByMonthMapper {
    private static final Map<Visualisation, Place> placeMap = new HashMap<>();

    static {
        placeMap.put(Visualisation.MONTH_TEMPERATURE_BOLZANO, Place.BOLZANO);
        placeMap.put(Visualisation.MONTH_TEMPERATURE_MERANO, Place.MERANO);
        placeMap.put(Visualisation.MONTH_TEMPERATURE_SELVA_VAL_GARDENA, Place.SELVA_VAL_GARDENA);
    }

    public static Place getPlace(Visualisation type) {
        checkType(type);
        return placeMap.get(type);
    }

    private static void checkType(Visualisation type) {
        switch (type) {
            case MONTH_TEMPERATURE_BOLZANO:
            case MONTH_TEMPERATURE_MERANO:
            case MONTH_TEMPERATURE_SELVA_VAL_GARDENA:
                break;
            default:
                throw new IllegalArgumentException("Not a month temperature!");
        }
    }
}


