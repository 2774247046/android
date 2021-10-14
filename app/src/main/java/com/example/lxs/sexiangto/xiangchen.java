package com.example.lxs.sexiangto;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.icu.util.Measure;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.lxs.R;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class xiangchen extends AppCompatActivity {
    private ImageView imageView;
    private Uri uri_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxt_1);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        Button sxt_btn1=findViewById(R.id.sxt_btn1);
        imageView=findViewById(R.id.sxt_img);
        Button sxt_btn=findViewById(R.id.sxt_btn);
        sxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int a=random.nextInt(9)*5;
                Log.d("a", String.valueOf(a));
                File file=new File(getExternalCacheDir(),a+".img");
                try {
                if (file.exists()) {
                    file.delete();
                }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    uri_1= FileProvider.getUriForFile(xiangchen.this,"com.example.lxs.fileprovider",file);
                }else{
                    uri_1=Uri.fromFile(file);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri_1);
                startActivityForResult(intent,1);
            }
        });
        sxt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list=new ArrayList<>();
                if (ContextCompat.checkSelfPermission(xiangchen.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(xiangchen.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                    list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }else{
                    openAlbum();
                }
            }
        });
    }
    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        Log.d("intent", String.valueOf(intent));
        startActivityForResult(intent,2);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    for (int a:grantResults){
                        if (a==PackageManager.PERMISSION_GRANTED){
                            openAlbum();
                        }
                    }
                }else{
                    Toast.makeText(xiangchen.this,"失败",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                if (resultCode==RESULT_OK){
                 if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                     handleImageOnKitKat(data);
                 }else{
                     handleImageBeforeKitKat(data);
                 }
                }
                break;
            case 1:
                if (resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                        try {
                            Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(uri_1));
                            imageView.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Toast.makeText(xiangchen.this,"6",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;
        }
    }
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority())){
                String id=docId.split(":")[1];
                String select=MediaStore.Images.Media._ID+"="+id;
                Log.d("id",id);
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,select);
            }else if ("com.android.providers.downloads.documents".equalsIgnoreCase(uri.getAuthority())){
                Uri uri1=ContentUris.withAppendedId(Uri.parse("content//downloads/public_downloads"),Long.parseLong(docId));
                imagePath=getImagePath(uri1,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String string=getImagePath(uri,null);
        displayImage(string);
    }
    private  String getImagePath(Uri uri,String select){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,select,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        Log.d("解析",path);
        return path;
    }
    private void displayImage(String imagePath){
        if (imagePath!=null){
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
            imageView.setImageBitmap(bitmap);
            Log.d("设置的图片id",imagePath);
        }else{
            Toast.makeText(xiangchen.this,"失败",Toast.LENGTH_SHORT).show();
        }
    }
}
