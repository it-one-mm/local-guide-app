package com.example.localguide;

public class PlacesModel {
    public  int Id;
    public String Name;
    public String Desc;
    public double lat;
    public double lng;
    public String img1;
    public String img2;
    public String img3;
    public int cityid;
    public int typeid;


    public PlacesModel(int id, String name, String desc, double lat, double lng, String img1, String img2, String img3, int cityid, int typeid) {
        Id = id;
        Name = name;
        Desc = desc;
        this.lat = lat;
        this.lng = lng;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.cityid = cityid;
        this.typeid = typeid;
    }

    public PlacesModel() {
    }
}
