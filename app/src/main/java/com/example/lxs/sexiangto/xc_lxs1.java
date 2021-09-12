package com.example.lxs.sexiangto;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lxs.R;

import java.security.Provider;
public class xc_lxs1 extends Activity {
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxt_1);
        imageView=findViewById(R.id.sxt_img);
        Button sxt_btn1=findViewById(R.id.sxt_btn1);
        sxt_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(xc_lxs1.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(xc_lxs1.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    intent();
                }
            }
        });
    }
    public void intent(){
        Intent intent=new Intent("android.intent.action.GET_INTENT");
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
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
            String io_id=DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equalsIgnoreCase(uri.getAuthority())){
                String id=io_id.split(":")[1];
                String o=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,o);
            }else if ("com.android.providers.downloads.documents".equalsIgnoreCase(uri.getAuthority())){
                Uri uri1= ContentUris.withAppendedId(Uri.parse("content://downloads/public_documents"),Long.parseLong(io_id));
                imagePath=getImagePath(uri1,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        getImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
       String image;
       Uri uri=data.getData();
       image=getImagePath(uri,null);
       getImage(image);
    }

    public String getImagePath(Uri uri, String select){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,select,null,null);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    public void getImage(String image_Path){
        if (image_Path!=null){
            Bitmap bitmap= BitmapFactory.decodeFile(image_Path);
            imageView.setImageBitmap(bitmap);
        }else{
            Toast.makeText(xc_lxs1.this,"imagePath为空",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    intent();
                }else{
                    Toast.makeText(xc_lxs1.this,"授权失败",Toast.LENGTH_SHORT).show();
                }
        }
    }
}
