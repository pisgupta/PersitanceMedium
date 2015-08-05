package com.example.pankaj.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Pankaj on 8/5/2015.
 */
public class MyDataBase extends SQLiteOpenHelper {
    public static final String DBNAME = "employeedb";
    public static final int VERSIONID = 1;

    public static String TABLENAME = "employee";

    public static String TCID = "empid";
    public static String TCNAME = "empname";
    public static String TCADDRESS = "empaddress";
    public static String TCAGE = "empage";

    public String QUERY = "create table " + TABLENAME + " ( " + TCID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TCNAME + " TEXT NOT NULL, " + TCADDRESS + " TEXT NOT NULL, " + TCAGE + " INTEGER NOT NULL )";

    public MyDataBase(Context mContext) {
        super(mContext, DBNAME, null, VERSIONID);
        Log.e("Db Create", "DB create");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(QUERY);
            Log.e("Table Create", "Table create");
        } catch (Exception ex) {
            Log.e("Exception", ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
