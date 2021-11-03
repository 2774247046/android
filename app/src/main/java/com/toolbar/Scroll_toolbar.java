package com.toolbar;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.lxs.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
public class Scroll_toolbar extends AppCompatActivity{
    private ViewPager scroll_viewPager;
    private TabLayout scroll_Tab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_toolbar_layout);
        List<Fragment>list=new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment1());
        list.add(new Fragment1());
        scroll_viewPager=findViewById(R.id.scroll_viewPager);
        scroll_viewPager.setAdapter(new S_adapter(getSupportFragmentManager(),0,list));
        scroll_Tab=findViewById(R.id.scroll_Tab);
        scroll_Tab.setupWithViewPager(scroll_viewPager);
        scroll_Tab.getTabAt(0).setText("1").setIcon(R.drawable.a1);
        scroll_Tab.getTabAt(1).setText("2");
        scroll_Tab.getTabAt(2).setText("3");
    }
    class S_adapter extends FragmentPagerAdapter{
        private List<Fragment>list;
        public S_adapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
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
}
