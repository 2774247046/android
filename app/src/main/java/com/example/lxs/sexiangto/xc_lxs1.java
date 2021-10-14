package com.example.lxs.sexiangto;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Selection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lxs.R;

import java.net.URL;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class xc_lxs1 extends Activity {
    private ImageView imageView;
    private TextView img_text;
    private List<String> PerissionString=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxt_1);
        imageView = findViewById(R.id.sxt_img);
        Button sxt_btn1 = findViewById(R.id.sxt_btn1);
        sxt_btn1.setOnClickListener(V->{
            if (ContextCompat.checkSelfPermission(xc_lxs1.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                PerissionString.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (!PerissionString.isEmpty()){
                    String []uu=PerissionString.toArray(new String[PerissionString.size()]);
                    ActivityCompat.requestPermissions(xc_lxs1.this,uu,1);
                }
            }else{
                methods();
            }
        });
    }

    private void methods() {
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int ER:grantResults){
                        if (ER==PackageManager.PERMISSION_GRANTED){
                            methods();
                        }
                    }
                }else{
                    Toast.makeText(xc_lxs1.this, "不知明错误", Toast.LENGTH_SHORT).show();
            }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
                    highSDK(data);
                }else{
                    lowSDK(data);
                }
        }
    }
    private void highSDK(Intent data){
        Uri uri=data.getData();
        String image=null;
        if (DocumentsContract.isDocumentUri(this,uri)){
            String id=DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String spite_id=id.split(":")[1];
                String select=MediaStore.Images.Media._ID+"="+spite_id;
                image=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,select);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri uri1=ContentUris.withAppendedId(Uri.parse("content//downloads/public_downloads"),Long.parseLong(id));
                image=getImagePath(uri1,null);
            }
        }else if ("content".equals(uri.getScheme())){
            image=getImagePath(uri,null);
        }else if ("file".equals(uri.getScheme())){
            image=uri.getPath();
        }
        displayImage(image);
    }
    private String getImagePath(Uri uri,String select){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,select,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if (imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            Log.d("6",String.valueOf(bitmap));
            imageView.setImageBitmap(bitmap);
        }
    }

    private void lowSDK(Intent data){
        Uri uri=data.getData();
        String a=getImagePath(uri,null);
        displayImage(a);
    }
}
