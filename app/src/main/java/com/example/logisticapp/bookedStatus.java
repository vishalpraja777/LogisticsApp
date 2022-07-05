package com.example.logisticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Objects;

public class bookedStatus extends AppCompatActivity {

    private TextView from,to,dis,veh,amt;
    private ImageView vehi;
    int amount,vt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_status);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        dis = findViewById(R.id.dis);
        veh = findViewById(R.id.veh);
        amt = findViewById(R.id.amt);
        vehi = findViewById(R.id.vehi);

        String fromSt = getIntent().getStringExtra("from");
        String toSt = getIntent().getStringExtra("to");
        String disSt = getIntent().getStringExtra("dis");
        String vehSt = getIntent().getStringExtra("veh");

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
        //veh.setText("Vehicle: " +vehSt);
        amt.setText("Amount:    Rs." +amtSt);

    }
}