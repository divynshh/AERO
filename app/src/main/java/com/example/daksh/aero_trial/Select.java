package com.example.daksh.aero_trial;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.silvestrpredko.dotprogressbar.DotProgressBar;
import com.github.silvestrpredko.dotprogressbar.DotProgressBarBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Select extends AppCompatActivity {


    private static final int REQUEST_CODE_PERMISSION = 2;
//    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;


    //GPSTracker gps;
    Button b;

  //  DotProgressBar dotProgressBar;
    Cursor c=null;
    int count=0;
    double lat,mylat;
    double lon;
    double mylong;
    double dist[];

    int city_id;
    private String[] arraySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        final Spinner spinner=(Spinner)findViewById(R.id.city_spinner);
        final Intent i=new Intent(this,MainActivity.class);

        //spinner.getSelectedItemPosition();

        b=(Button)findViewById(R.id.city_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Toast.makeText(view.getContext(), "item"+spinner.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                    city_id=spinner.getSelectedItemPosition();
                        if(city_id==0){
                            Toast.makeText(view.getContext(),"Please Select City",Toast.LENGTH_SHORT).show();
                            recreate();
                        }else{

                   i.putExtra("city_id", city_id);
                startActivity(i);

                finish();}
            }
        });

        //15oct Currentlocation Module

        /*try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will execute every time, else your else part will work

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            b=(Button)findViewById(R.id.city_button);
            dotProgressBar=(DotProgressBar)findViewById(R.id.dot_progress_bar) ;
         b.setOnClickListener(new View.OnClickListener() {
        @Override
          public void onClick(View view) {


                gps = new GPSTracker(Select.this);





                // check if GPS enabled
                if(gps.canGetLocation() ){

                    Toast.makeText(getApplicationContext(),"Locating You",Toast.LENGTH_SHORT).show();

                        dotProgressBar.setVisibility(View.VISIBLE);
                        mylat = gps.getLatitude();
                        mylong = gps.getLongitude();




                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                            + mylat + "\nLong: " + mylong, Toast.LENGTH_SHORT).show();



                    int secondsDelayed = 1;
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            // startActivity(new Intent(Splash.this, Select.class));
                            startActivity(i);
                            finish();

                            //finish();
                        }
                    }, secondsDelayed * 3000);



                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    Toast.makeText(getApplicationContext(),"Please Enable Location And Data Connection",Toast.LENGTH_SHORT).show();
                    gps.showSettingsAlert();

                                     // recreate();

                }
            }
        });




        /*        this.arraySpinner = new String[] {
                "Select City","Gurgaon","Faridabad","New Delhi","Noida","Ghaziabad","Meerut"
        };
        final Intent i=new Intent(this,MainActivity.class);


        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spinner.setAdapter(adapter);

        Button b=(Button)findViewById(R.id.city_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        city = spinner.getSelectedItem().toString();
                       if (city.equals("Gurgaon")) {

                           Toast t = Toast.makeText(getApplicationContext(), "Finding Hospitals", Toast.LENGTH_SHORT);
                           t.show();
                           progressBar.setVisibility(View.VISIBLE);

                           int secondsDelayed = 1;
                           new Handler().postDelayed(new Runnable() {
                               public void run() {
                                  // startActivity(new Intent(Splash.this, Select.class));
                                   startActivity(i);
                                   finish();

                                   //finish();
                               }
                           }, secondsDelayed * 3000);




                          } else if(city.equals("Select City")){
                    Toast t = Toast.makeText(getApplicationContext(), "Select City", Toast.LENGTH_SHORT);
                    t.show();
                    }else {
                           Toast t = Toast.makeText(getApplicationContext(), "We Haven't reached your city  ", Toast.LENGTH_SHORT);
                           t.show();
                       }


                }
                 }); */

        //startActivity(i);
        //finish();

    }


}