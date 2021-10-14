package com.example.lxs.Popup_Window;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Popup_Window extends AppCompatActivity {
    private List<List_a1>list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxs1);
        inti();
        RecyclerView R_View=findViewById(R.id.lxs_view);
        Popup_List popup_list=new Popup_List(list,Main_Popup_Window.this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        StaggeredGridLayoutManager manager1=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        R_View.setLayoutManager(manager);
        R_View.setLayoutManager(manager1);
        R_View.setAdapter(popup_list);
    }
    private void inti() {
        for (int i = 0; i <= 5; i++) {
            List_a1 h = new List_a1("张三", R.drawable.th);
            list.add(h);
        }
    }
}
