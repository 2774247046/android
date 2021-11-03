package com.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lxs.R;

public class fragment_item3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item,container,false);
        TextView textView=view.findViewById(R.id.fragment_item_text);
        textView.setText("第三个页面");
        textView.setTextColor(Color.WHITE);
        view.setBackgroundColor(Color.BLACK);
        return view;
    }
}
