package com.example.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainAddActivity extends AppCompatActivity {
    Databasehelper g;
    EditText e1,e2,e3,e4;
    Button s;
    Button d;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        g = new Databasehelper(this);
        e1 = (EditText) findViewById(R.id.amount);
        e2 = (EditText) findViewById(R.id.description);
        e3 = (EditText) findViewById(R.id.date);
        e4 = (EditText) findViewById(R.id.section);
        s  = (Button)   findViewById(R.id.save);
        AddData();
        d= (Button) findViewById(R.id.dele);
        Delete();
        b =(Button) findViewById(R.id.back12);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAddActivity.this,MainAppActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void Delete(){
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer DeletedRow = g.deleteData(e2.getText().toString());
                if(DeletedRow > 0){
                    //Toast.makeText(MainAddActivity.this, "Data Deleted Successfully ", Toast.LENGTH_SHORT).show();
                    View v21= getLayoutInflater().inflate(R.layout.delete_toast,(ViewGroup) findViewById(R.id.v112));
                    Toast toast = new Toast(MainAddActivity.this);
                    toast.setView(v21);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();

                }else {
                    Toast.makeText(MainAddActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
                e2.setText("");
            }
        });
    }

    public void AddData(){
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinserted = g.insertdata(e1.getText().toString(),
                                                  e2.getText().toString(),
                                                  e3.getText().toString(),
                                                  e4.getText().toString());
                if(isinserted == true){
                    View v123 = getLayoutInflater().inflate(R.layout.stored_data,(ViewGroup) findViewById(R.id.v111));
                    Toast toast = new Toast(MainAddActivity.this);
                    toast.setView(v123);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Toast.makeText(MainAddActivity.this, "Data is not Stored", Toast.LENGTH_SHORT).show();
                }
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
            }

        });
    }
}