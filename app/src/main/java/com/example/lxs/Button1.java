package com.example.lxs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Button1 extends AppCompatActivity {
    private EditText editText;
    private android.widget.Button button;
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        String a=editText.getText().toString();
        outState.putString("6",a);
        Log.d("HD",a);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);
        String aa=getIntent().getStringExtra("6");
        String hh=getIntent().getStringExtra("7");
        TextView textView=findViewById(R.id.nb);
        textView.setText("6"+aa+"7"+hh);
        editText=findViewById(R.id.text01);
        if (savedInstanceState!=null) {
            String h=savedInstanceState.getString("6");
            editText.setText(h);
            Log.d("text",h);
        }else{
            Toast.makeText(Button1.this,"null",Toast.LENGTH_SHORT).show();
        }
        button=findViewById(R.id.tiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Button1.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Button1.this,new String[]{Manifest.permission.CALL_PHONE},1);
                }else{
                    kk();
                }
            }
        });
    }
    public void kk(){
        String aa=editText.getText().toString();
        try {
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+aa));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case 1:
               if (grantResults.length>0&&grantResults[0]>PackageManager.PERMISSION_GRANTED){
                   kk();
               }else {
                   Toast.makeText(Button1.this,"授权失败",Toast.LENGTH_SHORT).show();
               }
               break;
           default:
       }
    }
}
