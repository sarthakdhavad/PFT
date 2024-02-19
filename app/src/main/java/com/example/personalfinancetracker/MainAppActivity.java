package com.example.personalfinancetracker;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainAppActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Button Add;
    Button log;
    Button v;
    Button c;
    Button  t;
    Button s;
    Button m;
    Button More;

    Databasehelper g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user== null){
            Intent intent = new Intent(MainAppActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        log = findViewById(R.id.Logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainAppActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        More =(Button) findViewById(R.id.more);
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= new Intent(MainAppActivity.this,MoreActivity.class);
                startActivity(i2);
            }
        });
        m= (Button) findViewById(R.id.report);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, MonthReport.class);
                startActivity(intent);
            }
        });
        s= (Button)findViewById(R.id.section);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainAppActivity.this, ExpenseActivity.class);
                startActivity(i);
            }
        });

        t =(Button) findViewById(R.id.total);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, Total.class);
                startActivity(intent);
            }
        });

        c= (Button) findViewById(R.id.clear);
        delete();
        v= (Button) findViewById(R.id.view);
        viewAll();
        g= new Databasehelper(this);
        Add = (Button) findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainAppActivity.this, MainAddActivity.class);
                startActivity(i);
            }
        });
    }


    public void delete(){
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.Delete();
               //Toast.makeText(MainAppActivity.this, "Expense is Deleted", Toast.LENGTH_SHORT).show();
                View view1 = getLayoutInflater().inflate(R.layout.all_delete,(ViewGroup) findViewById(R.id.v1));
                Toast toast = new Toast(MainAppActivity.this);
                toast.setView(view1);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }


    public void viewAll(){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = g.getAllData();
                if(res.getCount()== 0){
                    showMessage("Expenses: ","No Expenses Found");
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Amount:         "+ res.getString(0)+"\n");
                        buffer.append("Description:   "+ res.getString(1)+"\n");
                        buffer.append("Dates:               "+ res.getString(2)+"\n");
                        buffer.append("Section:         "+ res.getString(3)+"\n\n\n");
                    }
                    showMessage("Expenses: ",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    }
