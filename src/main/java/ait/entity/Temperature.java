package ait.entity;


import ait.db.Column;

/**
 * Created by studamit on 07/05/2016.
 */
public class Temperature extends IdEntity {

    public enum MeasurmentType {
        MIN, MAX, AVG
    }

    @Column
    private int year;
    @Column
    private int month;
    @Column
    private int measurementType;
    @Column
    private double temperature;

    public Temperature() {
    }

    public Temperature(int year, int month, int measurementType, double temperature) {
        this.year = year;
        this.month = month;
        this.measurementType = measurementType;
        this.temperature = temperature;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getMeasurmentType() {
        return measurementType;
    }

    public double getTempreture() {
        return temperature;
    }

}
