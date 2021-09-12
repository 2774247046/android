package com.example.lxs.Intent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class A1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public static Intent actionStart(Context context, Class context2, String data1, String data2){
        Intent intent=new Intent(context, context2);
        intent.putExtra("6",data1);
        intent.putExtra("7",data2);
        context.startActivity(intent);
        return intent;
    }
}
