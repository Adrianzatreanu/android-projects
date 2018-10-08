package com.example.adrianzatreanu.monthslayout;

/**
 * Created by adrianzatreanu on 25/11/2016.
 */

public class Month implements Comparable<Month>{

    private int month;
    private String totalDistance;
    private String averagePace;
    private String nikeFuel;

    public Month(int month, String totalDistance, String averagePace, String nikeFuel){
        this.month = month;
        this.totalDistance = totalDistance;
        this.averagePace = averagePace;
        this.nikeFuel = nikeFuel;
    }

    @Override
    public int compareTo(Month other) {
        if(this.month < other.getMonth())
            return 1;
        if(this.month == other.getMonth())
            return 0;
        return -1;
    }

    public int getMonth(){
        return this.month;
    }

    public String getTotalDistance(){
        return this.totalDistance;
    }

    public String getAveragePace(){
        return this.averagePace;
    }

    public String getNikeFuel(){
        return this.nikeFuel;
    }
}
