package com.example.logisticapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db ", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Bookings(ind TEXT primary key, froml TEXT , tol TEXT , distance TEXT , vehi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Bookings");
    }

    public long getTableLen() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "Bookings");
        db.close();
        return count;
    }

    public Boolean insertData(String ind , String froml, String tol, String distance, String vehi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ind",ind);
        contentValues.put("froml",froml);
        contentValues.put("tol",tol);
        contentValues.put("distance",distance);
        contentValues.put("vehi",vehi);

        long result = db.insert("Bookings",null,contentValues);
        if (result==1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean updateData(String ind , String froml, String tol, String distance, String vehi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("froml",froml);
        contentValues.put("tol",tol);
        contentValues.put("distance",distance);
        contentValues.put("vehi",vehi);

        Cursor cursor = db.rawQuery("Select * from Bookings where ind = ?",new String[]{ind});

        if (cursor.getCount()>0){

        long result = db.update("Bookings",contentValues,"ind=?",new String[]{ind});
        if (result==1){
            return false;
        }
        else {
            return true;
        }
    }
        else {
            return false;
        }
    }

    public Boolean deleteData(String ind){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Bookings where ind = ?",new String[]{ind});

        if (cursor.getCount()>0){

            long result = db.delete("Bookings","ind=?",new String[]{ind});
            if (result==1){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Bookings",null);

        return cursor;

    }

    public Cursor getDataInd(String ind){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Bookings where ind = ?",new String[]{ind});

        return cursor;

    }

}
