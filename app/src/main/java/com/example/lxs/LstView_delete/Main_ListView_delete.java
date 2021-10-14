package com.example.lxs.LstView_delete;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_ListView_delete extends AppCompatActivity {
    private List<ListView_arraylist>A_list=new ArrayList<>();
    private Set<List_delete> sets = new HashSet();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_delect_lstview);
        inti();
        ListView listView=findViewById(R.id.listView_Main);
        listView.setAdapter(new adpter(this,A_list));
        SwipeRefreshLayout swipeRefreshLayout=findViewById(R.id.listView_Up);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new adpter(Main_ListView_delete.this,A_list));
                        new adpter(Main_ListView_delete.this,A_list).notifyDataSetInvalidated();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });
    }
    class adpter extends BaseAdapter{
        private Context context;
        public List<ListView_arraylist>list=new ArrayList<>();

        public adpter(Context context, List<ListView_arraylist> list) {
            this.context = context;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HoView hoView;
            if (convertView==null){
                convertView= LayoutInflater.from(context).inflate(R.layout.listview_delete_content,parent,false);
                hoView=new HoView();
                hoView.textView=convertView.findViewById(R.id.listView_content);
                hoView.imageView=convertView.findViewById(R.id.listView_img);
                hoView.menu=convertView.findViewById(R.id.listView_menu);
                convertView.setTag(hoView);
            }else{
                hoView=(HoView)convertView.getTag();
            }
            hoView.imageView.setImageResource(list.get(position).getImage());
            hoView.textView.setText(list.get(position).getText());
            List_delete slideLayout = (List_delete) convertView;
            slideLayout.setOnStateChangeListener(new MyOnStateChangeListener());
            return convertView;
        }
    }
    class HoView{
        TextView textView;
        TextView menu;
        ImageView imageView;
    }
    private void inti() {
        for (int i = 0; i < 5; i++) {
            ListView_arraylist listView_arraylist = new ListView_arraylist("张三", R.drawable.a1);
            A_list.add(listView_arraylist);
            ListView_arraylist listView_arraylist2 = new ListView_arraylist("张三", R.drawable.a2);
            A_list.add(listView_arraylist2);
            ListView_arraylist listView_arraylist3 = new ListView_arraylist("张三", R.drawable.a3);
            A_list.add(listView_arraylist3);
            ListView_arraylist listView_arraylist4 = new ListView_arraylist("张三", R.drawable.a4);
            A_list.add(listView_arraylist4);
        }
    }
    private List_delete delete;
    class MyOnStateChangeListener implements List_delete.OnStateChangeListener {
        @Override
        public void onOpen(List_delete slideLayout) {
            delete = slideLayout;
            if (sets.size() > 0) {
                for (List_delete s : sets) {
                    s.closeMenu();
                    sets.remove(s);
                }
            }
            sets.add(slideLayout);
        }
        @Override
        public void onMove(List_delete slideLayout) {
            if (slideLayout != null && slideLayout !=slideLayout) {
                slideLayout.closeMenu();
            }
        }
        @Override
        public void onClose(List_delete slideLayout) {
            if (sets.size() > 0) {
                sets.remove(slideLayout);
            }
            if(slideLayout ==slideLayout){
                slideLayout = null;
            }
        }
    }
}
