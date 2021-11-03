package com.a1.SQLite_image;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.a1.SQLite_image.Dao.SQLite;
import com.example.lxs.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;

public class Main extends AppCompatActivity implements View.OnClickListener{
    private Button a1_button_add,a1_button_all,a1_button_delete;
    private ImageView image,image_1,image_2;
    private SQLite sqLite=new SQLite(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1_main);
        a1_button_add=findViewById(R.id.a1_button_add);
        a1_button_all=findViewById(R.id.a1_button_All);
        a1_button_delete=findViewById(R.id.a1_button_delete);
        image_1=findViewById(R.id.a1_image_1);
        image_2=findViewById(R.id.a1_image_2);
        image=findViewById(R.id.a1_image);
        a1_button_add.setOnClickListener(this);
        a1_button_all.setOnClickListener(this);
        a1_button_delete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.a1_button_All:
                String []name={"第1张图片"};
                Cursor cursor=sqLite.All(name);
                if (cursor!=null){
                    if (cursor.moveToFirst()){
                        do {
                            byte []i=cursor.getBlob(cursor.getColumnIndex("image"));
                            Bitmap bitmap=BitmapFactory.decodeByteArray(i,0,i.length);
                            image.setImageBitmap(bitmap);
                            image_1.setImageBitmap(bitmap);
                            image_2.setImageBitmap(bitmap);
                        }while (cursor.moveToNext());
                    }
                }
                break;
            case R.id.a1_button_delete:
                break;
            case R.id.a1_button_add:
                String []a={"第1张图片","6"};
                sqLite.Add(bitmapToBytes(R.drawable.th4),a);
                break;
        }
    }
    private byte[] bitmapToBytes(int image_id){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),image_id);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        Log.d("a",outputStream.size()+"");
        return outputStream.toByteArray();
    }
}
