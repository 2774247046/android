package com.pyq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lxs.R;
import com.pyq.MyAdapter.Grid_View_data;
import com.pyq.MyAdapter.MyViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class MyViewpage_Main extends AppCompatActivity{
    private ViewPager pyq_my_ViewPager;
    private List<View>list;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_viewpage_layout);
        pyq_my_ViewPager=findViewById(R.id.pyq_my_ViewPager);
        final Intent intent=getIntent();
        list=new ArrayList<>();
        List<Grid_View_data>data= (List<Grid_View_data>) intent.getSerializableExtra("image");
        for (int i=0;i<data.size();i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.view_pager_item, null, false);
            imageView = view.findViewById(R.id.view_image);
            Glide.with(this).load(data.get(i).getIma()).centerCrop().into(imageView);
            list.add(view);
        }
        MyViewpagerAdapter adapter=new MyViewpagerAdapter(list);
        pyq_my_ViewPager.setAdapter(adapter);
        pyq_my_ViewPager.setOffscreenPageLimit(data.size());
        pyq_my_ViewPager.setCurrentItem(intent.getIntExtra("id",0));
    }
}
