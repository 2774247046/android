package com.example.lxs.sexiangto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.example.lxs.MainActivity;
import com.example.lxs.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class sexiangto_lx1 extends AppCompatActivity {
    private Button button;
    private ImageView img;
    private Uri imguri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sxt_1);
        button=findViewById(R.id.sxt_btn);
        img=findViewById(R.id.sxt_img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               File file=new File(getExternalCacheDir(),"001.jpg");
                try {
               if (file.exists()){
                   file.delete();
               }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imguri=FileProvider.getUriForFile(sexiangto_lx1.this,"com.example.lxs.fileprovider",file);
                }else{
                    imguri=Uri.fromFile(file);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imguri);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    try {
                        Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(imguri));
                        img.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
