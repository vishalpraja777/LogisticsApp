package com.example.logisticapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class allBookings extends AppCompatActivity implements MyAdapter.onNoteListener{

    DBHelper db;

    RecyclerView recyclerView;
    ArrayList<String> ind,from,to,dis,veh;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bookings);

        //db = MainActivity.db;
        db = new DBHelper(this);

        ind = new ArrayList<>();
        from = new ArrayList<>();
        to = new ArrayList<>();
        dis = new ArrayList<>();
        veh = new ArrayList<>();

        recyclerView = findViewById(R.id.recylerview);
        adapter = new MyAdapter(this, ind,from,to,dis,veh,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();



//        Cursor res = db.getData();
//        if(res.getCount() == 0) {
//            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            StringBuffer buffer = new StringBuffer();
//            while (res.moveToNext()){
//                buffer.append("ID : " + res.getString(0)+"\n");
//                buffer.append("From : " + res.getString(1)+"\n");
//                buffer.append("To : " + res.getString(2)+"\n");
//                buffer.append("Distance : " + res.getString(3)+"\n");
//                buffer.append("Vehicle : " + res.getString(4)+"\n");
//            }
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setCancelable(true);
//            builder.setTitle("Bookings");
//            builder.setMessage(buffer.toString());
//            builder.show();
//
//        }

    }

    private void displayData() {
        Cursor cursor = db.getData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                ind.add( cursor.getString(0));
                from.add( cursor.getString(1));
                to.add( cursor.getString(2));
                dis.add( cursor.getString(3));
                veh.add( cursor.getString(4));
            }
        }
    }

    @Override
    public void onNoteClick(int position) {

        Intent intent = new Intent(this,bookedStatus.class);
        intent.putExtra("ind",ind.get(position));
        intent.putExtra("from",from.get(position));
        intent.putExtra("to",to.get(position));
        intent.putExtra("dis",dis.get(position));
        intent.putExtra("veh",veh.get(position));

        startActivity(intent);
    }
}