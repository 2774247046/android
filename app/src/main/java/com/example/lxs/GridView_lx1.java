package com.example.lxs;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.List.GridView_list;
import com.example.lxs.Baseadapter.GridView_lx1_adapter;

import java.util.ArrayList;
import java.util.List;

public class GridView_lx1 extends AppCompatActivity {
    private List<GridView_list> list=new ArrayList<GridView_list>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);
        GridView gridView = findViewById(R.id.GridView_layout);
        inti();
        GridView_lx1_adapter gridView_lx1_adapter=new GridView_lx1_adapter(list,GridView_lx1.this);
        gridView.setAdapter(gridView_lx1_adapter);
    }
    public  void inti() {
        for (int i = 0; i < 20; i++) {
            GridView_list gridView_list = new GridView_list("通讯录", R.drawable.th);
            list.add(gridView_list);
        }
    }
}
