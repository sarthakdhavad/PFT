package com.example.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Total extends AppCompatActivity {

    Databasehelper g;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        g= new Databasehelper(this);
        double TotalAmount = g.total();

        t1= (TextView) findViewById(R.id.value);
        t1.setText(String.format("Total Amount:  %.2f",TotalAmount));




    }
}