package com.example.lxs.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

public class Sqlite extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
        Button button=findViewById(R.id.sqlite_button);
        sqlite1 s= new sqlite1(Sqlite.this,"BookStoer.db",null,1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.getWritableDatabase();
            }
        });
    }
    class sqlite1 extends SQLiteOpenHelper{
        public static  final String name2="create table book(" +
                "id integer primary key," +
                "name integer," +
                "password text)";
        public sqlite1(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(name2);
            Toast.makeText(Sqlite.this, "成功", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists book");
            onCreate(db);
        }
    }
}
