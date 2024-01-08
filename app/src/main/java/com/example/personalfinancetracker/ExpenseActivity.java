package com.example.personalfinancetracker;

import static com.example.personalfinancetracker.R.id.Cash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExpenseActivity extends AppCompatActivity {

    Databasehelper g;
    TextView t;
    TextView o;
    TextView i;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        g = new Databasehelper(this);

        b= (Button) findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(ExpenseActivity.this, MainAppActivity.class);
                startActivity(i1);
                finish();
            }
        });
        double cash = g.Cash();
         t = (TextView) findViewById(R.id.Cash);
         t.setText(String.format("Total Cash Amount: %.2f", cash));

         double online = g.Online();
         o= (TextView) findViewById(R.id.online);
         o.setText(String.format("Total Online Amount: %.2f",online));

         double invest = g.Invest();
         i= (TextView) findViewById(R.id.invest);
         i.setText(String.format("Total Invest Amount: %.2f",invest));
    }
}