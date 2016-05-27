package ait.entity;


import ait.db.Column;

/**
 * Created by studamit on 07/05/2016.
 */
public class Temperature extends IdEntity {

    public enum MeasurmentType {
        MIN, MAX, AVG
    }

    public enum Place {
        BOLZANO, MERANO, SELVA_VAL_GARDENA
    }

    @Column
    private int year;

    @Column
    private int month;

    @Column
    private MeasurmentType measurementType;

    @Column
    private double temperature;

    @Column
    private Place place;

    public Temperature() {
    }

    public Temperature(MeasurmentType measurementType, int month, Place place, double temperature, int year) {
        this.measurementType = measurementType;
        this.month = month;
        this.place = place;
        this.temperature = temperature;
        this.year = year;
    }

    public MeasurmentType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurmentType measurementType) {
        this.measurementType = measurementType;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
