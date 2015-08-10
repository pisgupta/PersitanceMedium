package com.example.pankaj.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

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
            Log.e("Table and db", "Both created");
        } catch (Exception ex) {
            Log.e("Table and db", ex.toString());
        }
    }

    public boolean insertData(EmployeeBean bean) {
        boolean flag = false;
        ContentValues cv = new ContentValues();
        cv.put(MyDataBase.TCNAME, bean.getId());
        cv.put(MyDataBase.TCADDRESS, bean.getAddress());
        cv.put(MyDataBase.TCAGE, bean.getAge());
        long rowaffcted = sqLiteDatabase.insert(MyDataBase.TABLENAME, null, cv);
        if (rowaffcted > 0) {
            flag = true;
        }
        return flag;
    }

    String[] allcolumnname = {MyDataBase.TCID, MyDataBase.TCNAME, MyDataBase.TCADDRESS, MyDataBase.TCAGE};

    public ArrayList<String> selectAllData() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = sqLiteDatabase.query(MyDataBase.TABLENAME, allcolumnname, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String add = c.getString(2);
            int age = c.getInt(3);
            list.add(id + " " + name + " " + add + " " + age);
            c.moveToNext();
        }
        return list;
    }

    public boolean upTableData(EmployeeBean bean, int empid) {
        boolean flag = false;
        ContentValues cv = new ContentValues();
        //  cv.put(MyDataBase.TCNAME, bean.getId());
        cv.put(MyDataBase.TCADDRESS, bean.getAddress());
        cv.put(MyDataBase.TCAGE, bean.getAge());

        int row = sqLiteDatabase.update(MyDataBase.TABLENAME, cv, MyDataBase.TCID + " = ? ", new String[]{Integer.valueOf(empid).toString()});
        if (row > 0) {
            flag = true;
        }
        return flag;
    }

    String columnname[] = {MyDataBase.TCNAME, MyDataBase.TCADDRESS, MyDataBase.TCAGE};

    public ArrayList<String> selectAllDataWhere(String empid) {
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = sqLiteDatabase.query(MyDataBase.TABLENAME, columnname, MyDataBase.TCID + " = ?", new String[]{empid}, null, null, null);
        c.moveToFirst();
        String name = c.getString(0);
        String add = c.getString(1);
        int age = c.getInt(2);
        list.add(name + " " + add + " " + age);
        return list;
    }


    public ArrayList<String> selectAllDataGroupBy() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = sqLiteDatabase.query(MyDataBase.TABLENAME, allcolumnname, null, null, MyDataBase.TCNAME, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String add = c.getString(2);
            int age = c.getInt(3);
            list.add(id + " " + name + " " + add + " " + age);
            c.moveToNext();
        }
        return list;
    }

    public ArrayList<String> selectAllDataOrderBy() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = sqLiteDatabase.query(MyDataBase.TABLENAME, allcolumnname, null, null, null, null, null + " DESC ");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String add = c.getString(2);
            int age = c.getInt(3);
            list.add(id + " " + name + " " + add + " " + age);
            c.moveToNext();
        }
        return list;
    }


    public ArrayList<String> selectAllDataHaving() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor c = sqLiteDatabase.query(MyDataBase.TABLENAME, allcolumnname, null, null, MyDataBase.TCAGE, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String add = c.getString(2);
            int age = c.getInt(3);
            list.add(id + " " + name + " " + add + " " + age);
            c.moveToNext();
        }
        return list;
    }

    public boolean deleteData(String id) {
        boolean flag = false;
        int rowdele = sqLiteDatabase.delete(MyDataBase.TABLENAME, MyDataBase.TCID + " = ?", new String[]{id});
        if (rowdele > 0) {
            flag = true;
        }
        return flag;
    }

}

