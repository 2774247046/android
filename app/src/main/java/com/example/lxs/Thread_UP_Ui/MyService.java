package com.example.lxs.Thread_UP_Ui;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lxs.R;
public class MyService extends Service {
    private  DownloadBinder binder=new DownloadBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onBind","每次绑定");
        return null;
    }
    @Override
    public void onCreate() {
        Log.d("服务","创建成功");
        binder.starDownload();
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("onStartCommand","onStartCommand");
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        new DownloadBinder().getP();
        super.onDestroy();
    }
    class DownloadBinder extends Binder {
        public void starDownload(){
            Notification notification=new NotificationCompat.Builder(MyService.this,"1")
                    .setContentText("通知")
                    .setContentTitle("Create")
                    .setSubText("SubText")
                    .setTicker("通知")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("6"))
                    .setSmallIcon(R.drawable.a2)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.a1))
                    .setWhen(System.currentTimeMillis())
                    .build();
            NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                NotificationChannel channel=new NotificationChannel("01","测试",NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            startForeground(1,notification);
        }
        public int getP(){
            Log.d("服务停止","onDestroy");
            return 0;
        }
    }
}
