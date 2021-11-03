package com.fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;
public class Main_fragment extends AppCompatActivity {
    private ViewPager fragment_ViewPager;
    private Button btn_convert;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);
        fragment_ViewPager=findViewById(R.id.fragment_ViewPager);
        btn_convert=findViewById(R.id.btn_convert);
        List<Fragment>fragmentList=new ArrayList<>();
        fragmentList.add(new fragment_item());
        fragmentList.add(new fragment_item2());
        fragmentList.add(new fragment_item3());
        Adapter adapter=new Adapter(getSupportFragmentManager(),0,fragmentList);
        fragment_ViewPager.setAdapter(adapter);
        fragment_ViewPager.setOffscreenPageLimit(fragmentList.size());
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=fragment_ViewPager.getCurrentItem();
                fragment_ViewPager.setCurrentItem(a+1);
            }
        });
    }
    class Adapter extends FragmentPagerAdapter{
        private List<Fragment>list;
        public Adapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
            super(fm, behavior);
            this.list = list;
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }
        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onBackPressed() {
        int a=fragment_ViewPager.getCurrentItem();
        if (a!=0) {
            fragment_ViewPager.setCurrentItem(a - 1);
        }
    }
}
