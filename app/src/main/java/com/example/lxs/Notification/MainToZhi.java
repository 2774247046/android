package com.example.lxs.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.lxs.List.litepal_list.lxs2_Sql;
import com.example.lxs.R;
import com.example.lxs.lxs1;

import org.litepal.LitePal;

import java.io.File;

public class MainToZhi extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintozhi);
        Button button=findViewById(R.id.btn_ToZhi);
        button.setOnClickListener(onClick);
    }
    private final View.OnClickListener onClick=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_ToZhi:
//                    点击通知栏跳转 需要使用 PendingIntent包装intent 然后在Builder后.setContentIntent(传入 代定的Intent PendingIntent)
                    Intent intent=new Intent(MainToZhi.this,lxs1.class);
                    PendingIntent pe=PendingIntent.getActivity(MainToZhi.this,0,intent,0);
//                    builder 需要传入 上下文 和 一个id 这个id为通知id
                    Notification notification=new NotificationCompat.Builder(MainToZhi.this,"001")
//                            标题
                            .setContentTitle("通知")
//                            弹出小窗内容
//                            .setContentTitle("2021届，双非本科，去年秋招拿到的几个大厂offer, 没有低于20k的，而且还有股票激励。")
//                            通知内容用setContentText 内容过多用下面这个
                            .setStyle(new NotificationCompat.BigTextStyle().bigText("2021届，双非本科，去年秋招拿到的几个大厂offer, 没有低于20k的，而且还有股票激励。\n" +
                                    "\n" +
                                    "周围同学也差不多(当然这是指我们就业党进大厂的圈子)，差一点的算上股票也有20K了。说实话，这两年校招薪资涨得确实离谱。\n" +
                                    "\n" +
                                    "如果是刚进入这个行业的大学生,尽早做好准备吧，别把好牌打烂。\n" +
                                    "\n" +
                                    "这个薪资在我入学前根本想都不敢想，当年入学时，觉得毕业后有个1w就很不错了，后来想想真是时也，命也。\n" +
                                    "\n" +
                                    "不知道这个问题是哪一年提出的， 我只讲讲现在的行情，毕竟以前的也没有太多意义了。\n" +
                                    "\n" +
                                    "校招技术岗:客户端，首要的便是算法。\n" +
                                    "\n" +
                                    "人们容易把自己不懂的东西的难度放大，天天吹捧一些难度很大, 性价比很低的神书。\n" +
                                    "\n" +
                                    "比如说算法导论，天知道我一开始啃里面的主定理的时候有多懵逼(当然掌握主定理对分析复杂度是有帮助的)，但是其实完全没必要。\n" +
                                    "————————————————\n"))
//                            放图片 bigPicture后面这一部分和设置弹出图标 setLargeIcon一样
//                            .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.th)))
//                             设置时间
                            .setWhen(System.currentTimeMillis())
//                            设置图标
                            .setSmallIcon(R.drawable.a1)
//                            设置弹出的图标
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.th))
//                            手机呼吸灯的颜色
                            .setLights(Color.GREEN,1000,1000)
//                            跳转其他界面
                            .setContentIntent(pe)
//                            点击之后消失
                            .setAutoCancel(true)
//                            根据当前手机状态来 设置铃声
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .build();
//                    高版本需要下面这一部分
                    NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    if (android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O){
//                        通知渠道 需要传入 通知设置的id name自己设置 后面这个是设置通知的 重要性
                        NotificationChannel notificationChannel=new NotificationChannel("001","测试",NotificationManager.IMPORTANCE_HIGH);
notificationChannel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getPackageName()+"/"+R.raw.feint_stray),Notification.AUDIO_ATTRIBUTES_DEFAULT);
//                        下面这个方法也可以
//                        notificationChannel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+getPackageName() + "/"+R.raw.feint_stray),audioAttributes);

//                        然后把通知渠道 放入 createNotificationChannel创建通知通道 最后进入 通知管理器
                        manager.createNotificationChannel(notificationChannel);
                    }
                    manager.notify(1,notification);
                    break;
                default:
                    break;
            }
        }
    };
}
