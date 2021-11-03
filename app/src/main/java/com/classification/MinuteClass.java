package com.classification;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.classification.adapter.M_RightAdapter;
import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;
public class MinuteClass extends AppCompatActivity {
    private ListView MinuteClass_LeftListview,MinuteClass_RightListview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minute_class_layout);
        init();
    }
    private void init() {
        MinuteClass_LeftListview=findViewById(R.id.MinuteClass_LeftListview);
        List<String >data=new ArrayList<>();
        data.add("热门添加");
        data.add("卫衣");
        data.add("裤子");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        MinuteClass_LeftListview.setAdapter(adapter);
        MinuteClass_LeftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MinuteClass_RightListview.setSelection(position);
            }
        });

        MinuteClass_RightListview=findViewById(R.id.MinuteClass_RightListview);
        M_RightAdapter adapter1=new M_RightAdapter(this,data);
        MinuteClass_RightListview.setAdapter(adapter1);
    }
}
