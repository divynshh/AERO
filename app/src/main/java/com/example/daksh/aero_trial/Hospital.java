package com.example.daksh.aero_trial;


import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Hospital {

    String name;
    String bed;
    String ebed;
    String dist;
    String lat;
    String lon;

    Cursor c = null;
    //int photoId;

    Hospital(String name, String bed , String lat, String lon, String ebed) {
        this.name = name;
        this.bed = "Beds Available :"+bed;
        this.dist = dist;
        this.lat = lat;
        this.lon = lon;
        this.ebed="Ventilator beds :"+ebed;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getbeds() {
        return bed;
    }

    public void setbeds(String bed) {
        this.bed = bed;
    }


    public String getebeds() {
        return ebed;
    }

    public void setebeds(String ebed) {
        this.ebed = ebed;
    }




    public String getLat() {return lat;}

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {return lon;}

    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getdistance() {
        return dist;
    }

    public void setdistance(String dist) {
        this.dist = dist;
    }


    // private List<Hospital> hospitals;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    // private void initializeData(){
    // hospitals = new ArrayList<>();
    // hospitals.add(new Hospital("Emma Wilson", "23 years old", R.drawable.emma));
    // hospitals.add(new Hospital("Lavery Maiss", "25 years old", R.drawable.lavery));
    // hospitals.add(new Hospital("Lillie Watts", "35 years old", R.drawable.lillie));
//}
}
