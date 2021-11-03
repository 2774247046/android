package com.a1.SQLite_image.Dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class SQLite extends SQLiteOpenHelper{
    public SQLite(@Nullable Context context){
        super(context, "image.db", null, 6);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table a(" +
                "image longblob," +
                "name varchar," +
                "id Integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists a ");
        onCreate(db);
    }
    public Cursor All(String[] name){
        SQLiteDatabase data=getWritableDatabase();
        return data.rawQuery("select*from a where name=?",name);
    }
    public void Add(byte []a,String[]name){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL("insert into a(image,name,id)values(?,?,?)",new Object[]{a,name[0],name[1]});
    }
}
