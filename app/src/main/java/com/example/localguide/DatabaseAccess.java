package com.example.localguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */


    public ArrayList<CityModel> getAllCity()
    {
        ArrayList<CityModel> cityModels=new ArrayList<CityModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(CityDAO.TBName);
        Cursor cursor=qb.query(database,null,null,null,null,null,CityDAO.ColNId);
        while (cursor.moveToNext())
        {
            CityModel model=new CityModel();
            model.Id=cursor.getInt(cursor.getColumnIndex(CityDAO.ColNId));
            model.Image=cursor.getString(cursor.getColumnIndex(CityDAO.ColImage));
            model.Name=cursor.getString(cursor.getColumnIndex(CityDAO.ColName));
            cityModels.add(model);

        }
        return  cityModels;
    }

    public ArrayList<TypeModel> getAllType()
    {
        ArrayList<TypeModel> cityModels=new ArrayList<TypeModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(TypeDAO.TBName);
        Cursor cursor=qb.query(database,null,null,null,null,null,TypeDAO.ColNId);
        while (cursor.moveToNext())
        {
            TypeModel model=new TypeModel();
            model.Id=cursor.getInt(cursor.getColumnIndex(TypeDAO.ColNId));

            model.Name=cursor.getString(cursor.getColumnIndex(TypeDAO.ColName));
            cityModels.add(model);

        }
        return  cityModels;
    }

    public ArrayList<PlacesModel> getAllPlacesByCityIdandTypeId(int cityid,int typeid)
    {
        ArrayList<PlacesModel> cityModels=new ArrayList<PlacesModel>();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(PlacesDAO.TBName);
        String selection=PlacesDAO.ColCityId+"=? and "+ PlacesDAO.ColTypeId+"=?" ;
        String selectionargs[]=new String[]{String.valueOf(cityid),String.valueOf(typeid)};
        Cursor cursor=qb.query(database,null,selection,selectionargs,null,null,PlacesDAO.ColNId);
        while (cursor.moveToNext())
        {
            PlacesModel model=new PlacesModel();
            model.Id=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColNId));
            model.Name=cursor.getString(cursor.getColumnIndex(PlacesDAO.ColName));
            model.Desc=cursor.getString(cursor.getColumnIndex(PlacesDAO.ColDesc));
            model.img1= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg1));
            model.img2= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg2));
            model.img3= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg3));
            model.lat= cursor.getDouble(cursor.getColumnIndex(PlacesDAO.ColLat));
            model.lng=cursor.getDouble(cursor.getColumnIndex(PlacesDAO.ColNId));
            model.cityid=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColCityId));
            model.typeid=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColTypeId));

            cityModels.add(model);

        }
        return  cityModels;
    }


    public  PlacesModel getPlaceById(int id)
    {
        PlacesModel model=new PlacesModel();
        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();
        qb.setTables(PlacesDAO.TBName);
        String selection=PlacesDAO.ColNId+"=?";
        String selectionargs[]=new String[]{String.valueOf(id)};
        Cursor cursor=qb.query(database,null,selection,selectionargs,null,null,PlacesDAO.ColNId);
        while (cursor.moveToNext())
        {

            model.Id=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColNId));
            model.Name=cursor.getString(cursor.getColumnIndex(PlacesDAO.ColName));
            model.Desc=cursor.getString(cursor.getColumnIndex(PlacesDAO.ColDesc));
            model.img1= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg1));
            model.img2= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg2));
            model.img3= cursor.getString(cursor.getColumnIndex(PlacesDAO.ColImg3));
            model.lat= cursor.getDouble(cursor.getColumnIndex(PlacesDAO.ColLat));
            model.lng=cursor.getDouble(cursor.getColumnIndex(PlacesDAO.ColNId));
            model.cityid=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColCityId));
            model.typeid=cursor.getInt(cursor.getColumnIndex(PlacesDAO.ColTypeId));



        }
        return  model;
    }

}
