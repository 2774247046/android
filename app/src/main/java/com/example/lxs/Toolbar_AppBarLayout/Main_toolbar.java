package com.example.lxs.Toolbar_AppBarLayout;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lxs.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Main_toolbar extends AppCompatActivity {
    private List<Fruit>list=new ArrayList<>();
    private Fruit []fruit={new Fruit("皎月女神",R.drawable.th1),new Fruit("卡莎",R.drawable.th2),
            new Fruit("暴走萝莉",R.drawable.th3),new Fruit("拉克斯",R.drawable.th4),
            new Fruit("复仇之矛",R.drawable.th5)};
    private ImageButton imageButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FruitAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionsMenu button_menu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        imageButton=findViewById(R.id.toolbar_image);
        swipeRefreshLayout=findViewById(R.id.Fruit_RecyclerView);
        button_menu=findViewById(R.id.multiple_actions_down);
        fruit_inti();
        recyclerView=findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        adapter=new FruitAdapter(list,this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fruit_inti();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        button_menu.findViewById(R.id.top_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                Toast.makeText(Main_toolbar.this,"点击2",Toast.LENGTH_SHORT).show();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main_toolbar.this,"点击",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a2,menu);
        return true;
    }
    private void fruit_inti(){
        list.clear();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int a=random.nextInt(fruit.length);
            list.add(fruit[a]);
        }
    }
}
