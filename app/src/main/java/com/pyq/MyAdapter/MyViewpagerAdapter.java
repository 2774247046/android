package com.pyq.MyAdapter;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
public class MyViewpagerAdapter extends PagerAdapter {
    private List<View>list;
    public MyViewpagerAdapter(List<View> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
}
