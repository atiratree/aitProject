package ait.utils;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by suomiy on 11/23/15.
 */
public class JsonParser {


    public static String save(List<?> entries) {
        Gson gson = new Gson();
        return gson.toJson(entries);
    }

    public static String save(Object entry) {
        Gson gson = new Gson();
        return gson.toJson(entry);
    }
/*
    public static List<AnimeEntry> loadAsAnimeEntry(String entries){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<AnimeEntry>>() {}.getType();
        return (entries == null || "".equals(entries)) ?  new ArrayList<>() : gson.fromJson(entries, listType);
    }

    public static List<Pair<Integer, Double>> loadAsDifferenceVector(String parseString){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Pair<Integer, Double>>>() {}.getType();
        return (parseString == null || "".equals(parseString)) ?  new ArrayList<>() : gson.fromJson(parseString, listType);
    }

    public static List<Genre> loadAsGenre(String entries){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Genre>>() {}.getType();
        return (entries == null || "".equals(entries)) ?  new ArrayList<>() : gson.fromJson(entries, listType);
    }

    public static List<Float> loadAsDescriptionSimilarityVector(String parseString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Float>>() {}.getType();
        return (parseString == null || "".equals(parseString)) ?  new ArrayList<>() : gson.fromJson(parseString, listType);
    }*/
}
