package com.example.daksh.aero_trial;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    GPSTracker gps;
    Cursor c = null;
    private List<Hospital> hList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    boolean doubleBackToExitPressedOnce = false;
    double mylat, mylong;
    double distarray[] = null;
    int k = 0;
    double lat, lon;
    int rx_city_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        gps = new GPSTracker(MainActivity.this);

        rx_city_id=getIntent().getIntExtra("city_id",1000);
       // Toast.makeText(this,"rx_city_id"+rx_city_id,Toast.LENGTH_SHORT).show();
      //
        switch(rx_city_id){

            case 1: //Toast.makeText(this,"rx_city_id"+rx_city_id,Toast.LENGTH_SHORT).show();
                Gurgaon_Selected();
                break;

            case 2: //Toast.makeText(this,"rx_city_id"+rx_city_id,Toast.LENGTH_SHORT).show();
                Delhi_Selected();
                break;

            case 3: Toast.makeText(this," ",Toast.LENGTH_SHORT).show();
                break;

        }
        // Gurgaon_Selected();

        /*
        // check if GPS enabled
        if (gps.canGetLocation()) {

            //  Toast.makeText(getApplicationContext(),"Locating You",Toast.LENGTH_SHORT).show();


            mylat = gps.getLatitude();
            mylong = gps.getLongitude();

            //Toast.makeText(this,"Lat " + mylat,Toast.LENGTH_SHORT).show();


            // \n is for new line
    //        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
      //              + mylat + "\nLong: " + mylong, Toast.LENGTH_SHORT).show();

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            Toast.makeText(getApplicationContext(), "Please Enable Location And Data Connection", Toast.LENGTH_SHORT).show();
            gps.showSettingsAlert();

            // recreate();

        }
            */



    }


    public void Gurgaon_Selected(){

        DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(MainActivity.this, "Hospitals Found", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("hospitals", null, null, null, null, null, null);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new Adapter(hList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new Dividerdec(this, LinearLayoutManager.VERTICAL));

// set the adapter

        recyclerView.setAdapter(mAdapter);
        recyclerView.setAdapter(mAdapter);


     /* Button b=(Button)findViewById(R.id.testbt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c.moveToFirst();
                lat = Double.parseDouble(c.getString(3));
                lon = Double.parseDouble(c.getString(4));
                Toast.makeText(MainActivity.this, c.getString(3),Toast.LENGTH_SHORT);

            }
        });     */

        //gettingloctaion();
        hospitalData();
    }



    public void Delhi_Selected(){

        DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(MainActivity.this, "Hospitals Found", Toast.LENGTH_SHORT).show();
        c = myDbHelper.querydelhi("hospitalsdelhi", null, null, null, null, null, null);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new Adapter(hList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new Dividerdec(this, LinearLayoutManager.VERTICAL));

// set the adapter

        recyclerView.setAdapter(mAdapter);
        recyclerView.setAdapter(mAdapter);


     /* Button b=(Button)findViewById(R.id.testbt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c.moveToFirst();
                lat = Double.parseDouble(c.getString(3));
                lon = Double.parseDouble(c.getString(4));
                Toast.makeText(MainActivity.this, c.getString(3),Toast.LENGTH_SHORT);

            }
        });     */

        //gettingloctaion();
        hospitalData();
    }



    public static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // convert to meters

        //   double height = el1 - el2;

        distance = Math.pow(distance, 2);// + Math.pow(height, 2);

        return Math.sqrt(distance);
    }



    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }




    //}

        public void hospitalData() {


            c.moveToFirst();




        do {
         //  Toast.makeText(this,c.getString(0) + c.getString(1) +c.getString(2) ,Toast.LENGTH_SHORT).show();
            Hospital hospital = new Hospital(c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5));
              hList.add(hospital);

        }while(c.moveToNext());


        /*
        Giving data at compile time

        hospital = new Hospital("Medicare","16 km away","10");
        hList.add(hospital);

        hospital = new Hospital("CSA hospital","16 km away" , "6");
        hList.add(hospital);

        hospital = new Hospital("KK health care","20 km away" ,"35");
        hList.add(hospital);

        hospital = new Hospital("CMI hospital", "21 km away" ,"36");
        hList.add(hospital);

        hospital = new Hospital("Luthra Nursing Home", "25 km away", "20");
        hList.add(hospital);

        hospital = new Hospital("Medicare","16 km away","10");
        hList.add(hospital);

        hospital = new Hospital("CSA hospital","16 km away" , "6");
        hList.add(hospital);

        hospital = new Hospital("KNK health care","20 km away" ,"35");
        hList.add(hospital);

        hospital = new Hospital("Aryan hospital", "21 km away" ,"36");
        hList.add(hospital);

        hospital = new Hospital("Lal Nursing Home", "25 km away", "20");
        hList.add(hospital);
        hospital = new Hospital("Sethi Medicare","16 km away","10");
        hList.add(hospital);

        hospital = new Hospital("KCA hospital","16 km away" , "6");
        hList.add(hospital);

        hospital = new Hospital("RK health care","20 km away" ,"35");
        hList.add(hospital);

        hospital = new Hospital("CMI hospital", "21 km away" ,"36");
        hList.add(hospital);

        hospital = new Hospital("Luthra Nursing Home", "25 km away", "20");
        hList.add(hospital);

        */

        mAdapter.notifyDataSetChanged();


    }
}