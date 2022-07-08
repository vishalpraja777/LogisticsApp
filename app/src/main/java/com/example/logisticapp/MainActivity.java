package com.example.logisticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static DBHelper db;
    private Spinner spinner;
    private Button book;
    private EditText from,to,dis;
    private ImageView ct,bt,train,flight;
    private TextView vehc;

    String vehSt;

   // DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner = findViewById(R.id.spinner);
        book = findViewById(R.id.book);
        from = findViewById(R.id.fromIn);
        to = findViewById(R.id.toIn);
        dis = findViewById(R.id.disIn);

        ct = findViewById(R.id.ct);
        bt = findViewById(R.id.bt);
        train = findViewById(R.id.train);
        flight = findViewById(R.id.flight);

        vehc = findViewById(R.id.vehc);

        vehc.setText("Semi Truck");

        db = new DBHelper(this);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.vehicles, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(this);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });

        ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehSt = "Semi Truck";
                vehc.setText(vehSt);
                ct.setColorFilter(Color.argb(255,222,88,22));
                bt.setColorFilter(Color.argb(255, 255, 255, 255));
                train.setColorFilter(Color.argb(255, 255, 255, 255));
                flight.setColorFilter(Color.argb(255, 255, 255, 255));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehSt = "Big Truck";
                vehc.setText(vehSt);
                ct.setColorFilter(Color.argb(255, 255, 255, 255));
                bt.setColorFilter(Color.argb(255,222,88,22));
                train.setColorFilter(Color.argb(255, 255, 255, 255));
                flight.setColorFilter(Color.argb(255, 255, 255, 255));
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehSt = "Train";
                vehc.setText(vehSt);
                ct.setColorFilter(Color.argb(255, 255, 255, 255));
                bt.setColorFilter(Color.argb(255, 255, 255, 255));
                train.setColorFilter(Color.argb(255,222,88,22));
                flight.setColorFilter(Color.argb(255, 255, 255, 255));
            }
        });

        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehSt = "Flight";
                vehc.setText(vehSt);
                ct.setColorFilter(Color.argb(255, 255, 255, 255));
                bt.setColorFilter(Color.argb(255, 255, 255, 255));
                train.setColorFilter(Color.argb(255, 255, 255, 255));
                flight.setColorFilter(Color.argb(255,222,88,22));
            }
        });

    }

    public void openActivity() {

        String fromSt = from.getText().toString();
        String toSt = to.getText().toString();
        String disSt = dis.getText().toString();


        //Toast.makeText(getApplicationContext(),disSt,Toast.LENGTH_SHORT).show();

        if(fromSt=="" || toSt == "" || disSt=="" || vehSt==null)
        {
            Toast.makeText(getApplicationContext(),"Enter all the details",Toast.LENGTH_SHORT).show();
        }
        else {

            long len = db.getTableLen();
            String ind = String.valueOf(len);

            Boolean checkData = db.insertData(ind,fromSt,toSt,disSt,vehSt);

            if(checkData == true)
                Toast.makeText(this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"New Entry Not Inserted",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, allBookings.class);

//            intent.putExtra("from", fromSt);
//            intent.putExtra("to", toSt);
//            intent.putExtra("dis", disSt);
//            intent.putExtra("veh", vehSt);

            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        vehSt = choice;
        //Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}