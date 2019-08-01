package com.example.localguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class City extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ListView listView=findViewById(R.id.lstcity);
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        CityAdapter cityAdapter=new CityAdapter(databaseAccess.getAllCity());
        listView.setAdapter(cityAdapter);
        databaseAccess.close();

    }

    private  class CityAdapter extends BaseAdapter
    {
        ArrayList<CityModel> cityModels;

        public CityAdapter(ArrayList<CityModel> cityModels) {
            this.cityModels = cityModels;
        }

        @Override
        public int getCount() {
            return cityModels.size();
        }

        @Override
        public Object getItem(int position) {
            return cityModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.cityitem,null);
            final CityModel cityModel=cityModels.get(position);
            ImageView imageView=view.findViewById(R.id.cityimage);
            TextView cityname=view.findViewById(R.id.cityname);

            cityname.setText(cityModel.Name);
           Glide.with(getApplicationContext())
                   .load(Uri.parse(cityModel.Image))
                   .into(imageView);


            LinearLayout linearLayout=view.findViewById(R.id.cityitem);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CityDetails.cityid=cityModel.Id;
                    Intent intent=new Intent(getApplicationContext(),CityDetails.class);
                    startActivity(intent);

                }
            });

            return view;
        }
    }

}
