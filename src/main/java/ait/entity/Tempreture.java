package ait.entity;


import ait.db.Column;

/**
 * Created by studamit on 07/05/2016.
 */
public class Tempreture extends IdEntity {

    public enum MeasurmentType {
        MIN, MAX, AVG
    }

    @Column
    private int year;
    @Column
    private int month;
    @Column
    private int measurmentType;
    @Column
    private double tempreture;

    public Tempreture() {
    }

    public Tempreture(int year, int month, int measurmentType, double tempreture) {
        this.year = year;
        this.month = month;
        this.measurmentType = measurmentType;
        this.tempreture = tempreture;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getMeasurmentType() {
        return measurmentType;
    }

    public double getTempreture() {
        return tempreture;
    }

}
