package com.example.personalfinancetracker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MoreActivity extends AppCompatActivity {

    Databasehelper g;
    Button b1;
    EditText e1;
    double Spend ;

    Button b2;
    Bitmap LargeIcon;
    private static final String CHANNEL_ID ="Limit Channel";
    private static final int NOTIFICATION_ID =99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        b1 = (Button) findViewById(R.id.set);
        e1 =(EditText) findViewById(R.id.Limit);
        g= new Databasehelper(this);
        double total = g.total();

        b2 = (Button)findViewById(R.id.back11);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoreActivity.this,MainAppActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.logo,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

         LargeIcon = bitmapDrawable.getBitmap();
        if(total>Spend){
            notification("Control Your Spending","Your Expense is more than Limit");
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Limit = e1.getText().toString();
                if (!Limit.isEmpty()){
                    Spend = Double.parseDouble(Limit);
                }
                e1.setText("");
                Toast.makeText(MoreActivity.this, "Limit Set Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void notification(String Title,String message){
        NotificationManager nm= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notification = new Notification.Builder(this)
                    .setLargeIcon(LargeIcon)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(Title)
                     .setContentText(message)
                    .setChannelId(CHANNEL_ID)
                    .build();
             nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Limit Channel",NotificationManager.IMPORTANCE_HIGH));
        }else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(LargeIcon)
                    .setSmallIcon(R.drawable.logo)
                    .setContentText(Title)
                    .setSubText(message)
                    .build();
        }
        nm.notify(NOTIFICATION_ID,notification);
    }

}