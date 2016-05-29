package ait.utils;

import ait.entity.CartType;
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
    private static final Map<CartType, List<MeasurmentType>> typeMap = new HashMap<>();
    private static final Map<CartType, List<Place>> placeMap = new HashMap<>();
    private static final Map<CartType, Integer> minYearMap = new HashMap<>();

    static {
        typeMap.put(CartType.TEMPERATURE_BOLZANO, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(CartType.TEMPERATURE_MERANO, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(CartType.TEMPERATURE_SELVA_VAL_GARDENA, Arrays.asList(MeasurmentType.MIN, MeasurmentType.MAX, MeasurmentType.AVG));
        typeMap.put(CartType.TEMPERATURE_MIN_ALL_PLACES, Arrays.asList(MeasurmentType.MIN));
        typeMap.put(CartType.TEMPERATURE_MAX_ALL_PLACES, Arrays.asList(MeasurmentType.MAX));
        typeMap.put(CartType.TEMPERATURE_AVG_ALL_PLACES, Arrays.asList(MeasurmentType.AVG));

        placeMap.put(CartType.TEMPERATURE_BOLZANO, Arrays.asList(Place.BOLZANO));
        placeMap.put(CartType.TEMPERATURE_MERANO, Arrays.asList(Place.MERANO));
        placeMap.put(CartType.TEMPERATURE_SELVA_VAL_GARDENA, Arrays.asList(Place.SELVA_VAL_GARDENA));
        placeMap.put(CartType.TEMPERATURE_MIN_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));
        placeMap.put(CartType.TEMPERATURE_MAX_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));
        placeMap.put(CartType.TEMPERATURE_AVG_ALL_PLACES, Arrays.asList(Place.BOLZANO, Place.MERANO, Place.SELVA_VAL_GARDENA));


        minYearMap.put(CartType.TEMPERATURE_BOLZANO, 1926);
        minYearMap.put(CartType.TEMPERATURE_MERANO, 1983);
        minYearMap.put(CartType.TEMPERATURE_SELVA_VAL_GARDENA, 1991);
        minYearMap.put(CartType.TEMPERATURE_MIN_ALL_PLACES, 1926);
        minYearMap.put(CartType.TEMPERATURE_MAX_ALL_PLACES, 1926);
        minYearMap.put(CartType.TEMPERATURE_AVG_ALL_PLACES, 1926);
    }

    public static List<MeasurmentType> getMeasurementTypes(CartType type) {
        checkType(type);
        return typeMap.get(type);
    }

    public static List<Place> getPlaces(CartType type) {
        checkType(type);
        return placeMap.get(type);
    }

    public static Integer getMinYear(CartType type) {
        checkType(type);
        return minYearMap.get(type);
    }

    public static Integer getMaxYear(CartType type) {
        return 2015;
    }

    private static void checkType(CartType type) {
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


