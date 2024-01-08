package com.example.personalfinancetracker;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String Database_name = "expense.sqLiteDatabase";
    public static final String Table_name = "expense_table";
    public static final String Amount = "amount";
    public static final String Description = "description";
    public static final String Date = "date";
    public static final String Section = "section";

    public Databasehelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Table_name + "(amount number PRIMARY KEY,description Text,date date,section Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(sqLiteDatabase);
    }

    public Integer deleteData(String description)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(Table_name,"description = ?",new String[]{description});

    }

    public boolean insertdata(String amount, String description, String date, String section) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Amount, amount);
        contentValues.put(Description, description);
        contentValues.put(Date, date);
        contentValues.put(Section, section);
        long result = sqLiteDatabase.insert(Table_name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public double total() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        double TotalAmount = 0.0;
        String query = "SELECT SUM (" + Databasehelper.Amount + ") AS Total_Amount FROM " + Databasehelper.Table_name;
        Cursor res = sqLiteDatabase.rawQuery(query, null);
        if (res != null && res.moveToFirst()) {
            TotalAmount = res.getDouble(0);
            res.close();
        }
        return TotalAmount;
    }

    public double Cash() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT SUM (" + Databasehelper.Amount + ") As cash FROM (" + Databasehelper.Table_name + ") WHERE (" + Databasehelper.Section + ")= 'Cash' ";
        Cursor res = sqLiteDatabase.rawQuery(query, null);

        double cash = 0;
        if (res != null && res.moveToFirst()) {
            int Column = res.getColumnIndex("cash");

            if (Column != -1) {
                cash = res.getDouble(Column);
                res.close();
            }
        }
        return cash;
    }

    public double Online() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT SUM (" + Databasehelper.Amount + ") As online FROM (" + Databasehelper.Table_name + ") WHERE (" + Databasehelper.Section + ")= 'Online' ";
        Cursor r = sqLiteDatabase.rawQuery(query, null);

        double online = 0;
        if (r != null && r.moveToFirst()) {
            int Column = r.getColumnIndex("online");

            if (Column != -1) {
                online = r.getDouble(Column);
                r.close();
            }
        }
        return online;
    }

    public double Invest() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT SUM (" + Databasehelper.Amount + ") As invest FROM (" + Databasehelper.Table_name + ") WHERE (" + Databasehelper.Section + ")= 'Invest' ";
        Cursor r = sqLiteDatabase.rawQuery(query, null);

        double invest = 0;
        if (r != null && r.moveToFirst()) {
            int Column = r.getColumnIndex("invest");

            if (Column != -1) {
                invest = r.getDouble(Column);
                r.close();
            }
        }
        return invest;
    }


    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + Table_name, null);
        return res;
    }

    public void Delete() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Table_name, null, null);
    }


}



