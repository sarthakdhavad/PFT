package com.example.personalfinancetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText u1;

    EditText u2;

    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u1= (EditText) findViewById(R.id.username);
         u2 = (EditText) findViewById(R.id.password);
         sign = (Button) findViewById(R.id.save);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(u1.getText().toString().equals("nikhil") && u2.getText().toString().equals("sarthak"))
                {
                    View view11 = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.ViewAll));
                    Toast toast = new Toast(MainActivity.this);
                    toast.setView(view11);
                    TextView txt = view11.findViewById(R.id.text22);
                    txt.setText("Successfully Login");
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                    navigate();
                }
                else {

                    View v12= getLayoutInflater().inflate(R.layout.failed_toast,(ViewGroup) findViewById(R.id.view123));
                    Toast toast = new Toast(MainActivity.this);
                    toast.setView(v12);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();

                }
            }
            void navigate(){
                finish();
                Intent intent= new Intent(MainActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });
    }

}


