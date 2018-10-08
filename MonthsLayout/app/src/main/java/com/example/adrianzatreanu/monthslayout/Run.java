package com.example.adrianzatreanu.monthslayout;

/**
 * Created by adrianzatreanu on 23/11/2016.
 */

public class Run {
    private int month;
    private String day;
    private String year;
    private String pace;
    private String time;
    private String distance;
    private String[] icons;

    public Run(int month, String day, String year, String pace, String time, String distance, String[] icons){
        this.month = month;
        this.day = day;
        this.year = year;
        this.pace = pace;
        this.time = time;
        this.distance = distance;
        this.icons = icons;
    }

    public String[] getIcons(){
        return this.icons;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {

        return time;
    }

    public String getPace() {

        return pace;
    }

    public String getYear() {

        return year;
    }

    public String getDay() {

        return day;
    }

    public int getMonth() {

        return month;
    }
}
