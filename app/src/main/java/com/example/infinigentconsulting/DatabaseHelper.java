package com.example.infinigentconsulting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "qt_infinigentdb.db";
    public static final String TABLE_NAME = "LU_User_t";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "MobileNo";
    public static final String COL_5 = "Password";
    public static final String COL_6 = "IsActive";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,MobileNo TEXT,Password TEXT,IsActive NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean   insertData(String Name, String Email, String MobileNo, String Password,boolean IsActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Name);
        contentValues.put(COL_3, Email);
        contentValues.put(COL_4, MobileNo);
        contentValues.put(COL_5, Password);
        contentValues.put(COL_6, IsActive);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Integer deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();
       /* return db.delete(TABLE_NAME, "ID = ?",new String[] {id});*/
        return db.delete(TABLE_NAME,"1= ?",new String[] {"1"});
    }


}



