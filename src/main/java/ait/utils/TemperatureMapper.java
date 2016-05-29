package ait.utils;

import ait.entity.Visualisation;
import ait.entity.Temperature.MeasurmentType;
import ait.entity.Temperature.Place;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suomiy on 29/05/16.
 */
public class TemperatureMapper {
    private static final Map<Visualisation, List<MeasurmentType>> typeMap = new HashMap<>();
    private static final Map<Visualisation, List<Place>> placeMap = new HashMap<>();
    private static final Map<Visualisation, Integer> minYearMap = new HashMap<>();

    static {
        typeMap.put(Visualisation.TEMPERATURE_BOLZANO, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(Visualisation.TEMPERATURE_MERANO, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(Visualisation.TEMPERATURE_SELVA_VAL_GARDENA, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(Visualisation.TEMPERATURE_MIN_ALL_PLACES, Arrays.asList(MeasurmentType.MIN));
        typeMap.put(Visualisation.TEMPERATURE_MAX_ALL_PLACES, Arrays.asList(MeasurmentType.MAX));
        typeMap.put(Visualisation.TEMPERATURE_AVG_ALL_PLACES, Arrays.asList(MeasurmentType.AVG));

        placeMap.put(Visualisation.TEMPERATURE_BOLZANO, Arrays.asList(Place.BOLZANO));
        placeMap.put(Visualisation.TEMPERATURE_MERANO, Arrays.asList(Place.MERANO));
        placeMap.put(Visualisation.TEMPERATURE_SELVA_VAL_GARDENA, Arrays.asList(Place.SELVA_VAL_GARDENA));
        placeMap.put(Visualisation.TEMPERATURE_MIN_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));
        placeMap.put(Visualisation.TEMPERATURE_MAX_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));
        placeMap.put(Visualisation.TEMPERATURE_AVG_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));


        minYearMap.put(Visualisation.TEMPERATURE_BOLZANO, 1926);
        minYearMap.put(Visualisation.TEMPERATURE_MERANO, 1983);
        minYearMap.put(Visualisation.TEMPERATURE_SELVA_VAL_GARDENA, 1991);
        minYearMap.put(Visualisation.TEMPERATURE_MIN_ALL_PLACES, 1926);
        minYearMap.put(Visualisation.TEMPERATURE_MAX_ALL_PLACES, 1926);
        minYearMap.put(Visualisation.TEMPERATURE_AVG_ALL_PLACES, 1926);
    }

    public static List<MeasurmentType> getMeasurementTypes(Visualisation type) {
        checkType(type);
        return typeMap.get(type);
    }

    public static List<Place> getPlaces(Visualisation type) {
        checkType(type);
        return placeMap.get(type);
    }

    public static Integer getMinYear(Visualisation type) {
        checkType(type);
        return minYearMap.get(type);
    }

    public static Integer getMaxYear(Visualisation type) {
        return 2015;
    }

    private static void checkType(Visualisation type) {
        switch (type) {
            case TEMPERATURE_BOLZANO:
            case TEMPERATURE_MERANO:
            case TEMPERATURE_SELVA_VAL_GARDENA:
            case TEMPERATURE_MIN_ALL_PLACES:
            case TEMPERATURE_MAX_ALL_PLACES:
            case TEMPERATURE_AVG_ALL_PLACES:
                break;
            default:
                throw new IllegalArgumentException("Not a temperature!");
        }
    }
}


