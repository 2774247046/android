package com.example.lxs.sexiangto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.lxs.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilePermission;

public class Mainsexiangto extends AppCompatActivity {
    ImageView img;
    public static final int TAKE_PHOTO=1;
    private Uri imgUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxt_1);
        Button button=findViewById(R.id.sxt_btn);
        img=findViewById(R.id.sxt_img);
        button.setOnClickListener(hh);
    }
    public View.OnClickListener hh=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            File outimg=new File(getExternalCacheDir(),"output_img");
            try {
                if (outimg.exists()){
                    outimg.delete();
                }
                outimg.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT>=24){
                imgUri= FileProvider.getUriForFile(Mainsexiangto.this,"com.example.lxs.fileprovider",
                        outimg);
            }else{
                imgUri=Uri.fromFile(outimg);
            }
            Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
            startActivityForResult(intent,TAKE_PHOTO);
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("照片","添加成功");
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
                        Log.d("照片","6");
                        img.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
