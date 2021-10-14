package com.example.lxs.Thread_UP_Ui;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class IntentService_ extends IntentService {
    public IntentService_() {
        super("IntentService_");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("IntentService_.class","id"+Thread.currentThread().getId());
    }
    @Override
    public void onDestroy() {
        Log.d("Destroy","结束");
        super.onDestroy();
    }
}
