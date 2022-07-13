package com.example.logisticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class bookedStatus extends AppCompatActivity {

    private Button del;
    private TextView from,to,dis,veh,amt;
    private ImageView vehi;
    int amount,vt;

    DBHelper db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_status);

        del = findViewById(R.id.del);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        dis = findViewById(R.id.dis);
        veh = findViewById(R.id.veh);
        amt = findViewById(R.id.amt);
        vehi = findViewById(R.id.vehi);

        db = new DBHelper(this);

        String indSt = getIntent().getStringExtra("ind");
        String fromSt = getIntent().getStringExtra("from");
        String toSt = getIntent().getStringExtra("to");
        String disSt = getIntent().getStringExtra("dis");
        String vehSt = getIntent().getStringExtra("veh");

//        Cursor cursor = db.getDataInd(indSt);

//        String fromSt = cursor.getString(0);
//        String toSt = cursor.getString(1);
//        String disSt = cursor.getString(2);
//        String vehSt = cursor.getString(3);

        //Toast.makeText(this,  indSt , Toast.LENGTH_SHORT).show();




        if(Objects.equals(vehSt, "Semi Truck")) {
            vt = 1;
            vehi.setImageResource(R.drawable.cargotruck);
        }
        if(Objects.equals(vehSt, "Big Truck")) {
            vt = 2;
            vehi.setImageResource(R.drawable.bigtruck);
        }
        if(Objects.equals(vehSt, "Train")) {
            vt = 3;
            vehi.setImageResource(R.drawable.train);
        }
        if(Objects.equals(vehSt, "Flight")) {
            vt = 4;
            vehi.setImageResource(R.drawable.flight);
        }

        amount = Integer.parseInt(disSt)*vt*5;

        String amtSt = String.valueOf(amount);

        from.setText(fromSt);
        to.setText(toSt);
        dis.setText("Distance:  " +disSt);
        veh.setText("Vehicle: " +vehSt);
        amt.setText("Amount:    Rs." +amtSt);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(bookedStatus.this, "Delivered", Toast.LENGTH_SHORT).show();
                db.deleteData(indSt);
               openActivity();
            }
        });

    }

    private void openActivity() {
        Intent intent = new Intent(this,allBookings.class);
        startActivity(intent);
    }
}