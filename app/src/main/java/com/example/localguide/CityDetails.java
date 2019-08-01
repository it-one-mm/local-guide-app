package com.example.localguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CityDetails extends AppCompatActivity {

    public static int cityid;
    public  static int typeid=1;
    ArrayList<String>typenames;
    int [] typeids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_city_details);
        final DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ArrayList<TypeModel> types=databaseAccess.getAllType();
        typeids=new int[types.size()];
        typenames=new ArrayList<String>();
        for(int i=0;i<types.size();i++)
        {
            typenames.add(types.get(i).Name);
            typeids[i]=types.get(i).Id;
        }
        databaseAccess.close();;
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,typenames);

        //AddItems(view);
        Spinner spinner=findViewById(R.id.typespinner);
        spinner.setAdapter(arrayAdapter1);
        final ListView lstcitydetails=findViewById(R.id.citydetailslist);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeid=typeids[position];
                DatabaseAccess databaseAccess1=DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess1.open();;
                ArrayList<PlacesModel> placesModelArray=databaseAccess.getAllPlacesByCityIdandTypeId(cityid,typeid);

                PlaceAdapter placeAdapter=new PlaceAdapter(placesModelArray);
                lstcitydetails.setAdapter(placeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private class PlaceAdapter extends BaseAdapter
    {
        ArrayList<PlacesModel> arrayList;

        public PlaceAdapter(ArrayList<PlacesModel> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.cityitem,null);
            final PlacesModel cityModel=arrayList.get(position);
            ImageView imageView=view.findViewById(R.id.cityimage);
            TextView cityname=view.findViewById(R.id.cityname);

            cityname.setText(cityModel.Name);
            Glide.with(getApplicationContext())
                    .load(Uri.parse(cityModel.img1))

                    .into(imageView);


            LinearLayout linearLayout=view.findViewById(R.id.cityitem);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

            return view;
        }
    }

}
