package com.example.pankaj.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Pankaj on 8/5/2015.
 */
public class DBConnection {
    MyDataBase myDataBase;
    SQLiteDatabase sqLiteDatabase;

    public DBConnection(Context ct) {
        try {
            myDataBase = new MyDataBase(ct);
            sqLiteDatabase = myDataBase.getWritableDatabase();
            Log.e("Table and db","Both created");
        } catch (Exception ex) {
            Log.e("Table and db",ex.toString());
        }
    }
    public boolean insertData(EmployeeBean bean){
        boolean flag = false;
        ContentValues cv = new ContentValues();
        cv.put(MyDataBase.TCNAME,bean.getId());
        cv.put(MyDataBase.TCADDRESS,bean.getAddress());
        cv.put(MyDataBase.TCAGE,bean.getAge());
        long rowaffcted = sqLiteDatabase.insert(MyDataBase.TABLENAME,null,cv);
        if(rowaffcted>0){
            flag = true;
        }
        return  flag;
    }
}

