package com.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.scroll_fragmnrt_layout,container,false);
        List<String> list=new ArrayList<>();
        for (int i=1;i<=100;i++){
            list.add("测试数据"+i);
        }
        ListView scroll_ListView=view.findViewById(R.id.scroll_ListView);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,list);
        scroll_ListView.setAdapter(adapter);
        return view;
    }
}
