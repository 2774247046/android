package com.example.lxs.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lxs.List.lxs1_list;
import com.example.lxs.R;
import com.example.lxs.lxs1;

import java.util.ArrayList;
import java.util.List;

public class ToZhi_lx1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintozhi);
        Button button=findViewById(R.id.btn_ToZhi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(ToZhi_lx1.this,lxs1.class);
             PendingIntent pi=PendingIntent.getActivity(ToZhi_lx1.this,1,intent,0);
             Notification notification=new NotificationCompat.Builder(ToZhi_lx1.this,"001")
                     .setStyle(new NotificationCompat.BigTextStyle().bigText("\n" +
                             "public class ToZhi_lx1 extends AppCompatActivity {\n" +
                             "    @Override\n" +
                             "    protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                             "        super.onCreate(savedInstanceState);\n" +
                             "        setContentView(R.layout.maintozhi);\n" +
                             "        Button button=findViewById(R.id.btn_ToZhi);\n" +
                             "        button.setOnClickListener(new View.OnClickListener() {\n" +
                             "            @Override\n" +
                             "            public void onClick(View v) {\n" +
                             "             Intent intent=new Intent(ToZhi_lx1.this,lxs1.class);\n" +
                             "             Notification notification=new NotificationCompat.Builder(ToZhi_lx1.this,\"001\")\n" +
                             "                     .setStyle(new NotificationCompat.BigTextStyle().bigText(\"\"))\n" +
                             "                     .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a2))\n" +
                             "                     .setSmallIcon(R.drawable.a1)\n" +
                             "                     .setWhen(System.currentTimeMillis())\n" +
                             "                     .build();\n" +
                             "            }\n" +
                             "        });\n" +
                             "    }\n" +
                             "}"))
//                     .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.a2)))
                     .setContentIntent(pi)
                     .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.a2))
                     .setSmallIcon(R.drawable.a1)
                     .setWhen(System.currentTimeMillis())
                     .build();
             NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
             if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                 NotificationChannel channel=new NotificationChannel("1","测试",NotificationManager.IMPORTANCE_HIGH);
                 channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getPackageName()+"/"+R.raw.feint_stray),Notification.AUDIO_ATTRIBUTES_DEFAULT);
                 Log.d("5", String.valueOf(channel.getSound()));
                 manager.createNotificationChannel(channel);
             }
             manager.notify(1,notification);
            }
        });
    }
}
