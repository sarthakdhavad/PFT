package com.example.personalfinancetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

public class MonthReport extends AppCompatActivity {

    Databasehelper g;
    double Total;
    double Cash;
    double online;
    double Invest;
    Button b1;
    String Report;
    Button b;
    public static int Request_create_File= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_report);
        g = new Databasehelper(this);
         Total = g.total();
         Cash = g.Cash();
         online = g.Online();
         Invest = g.Invest();
         Report =    "           Report  "+
                 "\n\n Total Cash Amount:   "+Cash+
                 "\n Total Online Amount: "+online+
                 "\n Total Invest Amount: "+Invest+
                 "\n\n Total Amount:       "+Total;


        b1 = (Button) findViewById(R.id.down);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreteSaveFile();
            }
        });

        b = (Button) findViewById(R.id.back13);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonthReport.this,MainAppActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void CreteSaveFile() {
        Intent createFileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        createFileIntent.addCategory(Intent.CATEGORY_OPENABLE);
        createFileIntent.setType("text/plain");
        createFileIntent.putExtra(Intent.EXTRA_TITLE, "Report.txt");

        startActivityForResult(createFileIntent, Request_create_File);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Request_create_File && resultCode ==RESULT_OK){
            if(data !=null){
                Uri uri = data.getData();
                writeData(uri);
            }
        }
    }

    public void writeData(Uri uri){
        try(OutputStream outputStream = getContentResolver().openOutputStream(uri)){
            if(outputStream != null){
                outputStream.write(Report.getBytes());
                outputStream.close();
                View view = getLayoutInflater().inflate(R.layout.download_toast,(ViewGroup) findViewById(R.id.v2));
                Toast toast = new Toast(MonthReport.this);
                toast.setView(view);
                toast.show();
            }
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "File Not Downloaded", Toast.LENGTH_SHORT).show();
        }
    }
}