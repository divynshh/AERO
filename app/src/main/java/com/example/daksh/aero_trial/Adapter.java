package com.example.daksh.aero_trial;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;




public class Adapter extends  RecyclerView.Adapter<Adapter.MyViewHolder>{
    Intent intent;

    Cursor c = null;
    //float lat,lon;
    Context context;
    float flat,flon;

  //  public Adapter(Context context){
    //    this.context=context;
      //  Toast.makeText(context,"Cons",Toast.LENGTH_SHORT).show();        //inflater= LayoutInflater.from(context);
        //this.data=data;
    //}
    private List<Hospital> hospitalList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, bed,distance,lat,lon,ebeds;
        public Button b_viwonmap;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            bed = (TextView) view.findViewById(R.id.capacity);
            distance = (TextView) view.findViewById(R.id.distance);
            lat=(TextView)view.findViewById(R.id.lat);
            lon=(TextView)view.findViewById(R.id.lon);
            ebeds=(TextView)view.findViewById(R.id.icucapacity);
            b_viwonmap=(Button) view.findViewById(R.id.viewonmap);

        }
    }


    public Adapter(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hospital_info, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);
        holder.title.setText(hospital.getTitle());
        holder.bed.setText(hospital.getbeds());
        holder.distance.setText(hospital.getdistance());
        holder.ebeds.setText(hospital.getebeds());
        holder.lat.setText(hospital.getLat());
        holder.lon.setText(hospital.getLon());




        holder.b_viwonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(),"context worked",Toast.LENGTH_SHORT).show();



               // Intent sp= new Intent(view.getContext(),Splash.class);
                //view.getContext().startActivity(sp);

                //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat , lon );
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                //view.getContext().startActivity(intent);

                double latitude = Double.parseDouble(holder.lat.getText().toString());
                double longitude = Double.parseDouble(holder.lon.getText().toString());
                String label = holder.title.getText().toString();
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = holder.title.getText().toString();//latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }





}