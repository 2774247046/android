package com.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        RecyclerView scroll_ListView=view.findViewById(R.id.scroll_ListView);
        R_adapter r_adapter=new R_adapter(list);
        scroll_ListView.setAdapter(r_adapter);
        scroll_ListView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return view;
    }
    class R_adapter extends RecyclerView.Adapter<R_adapter.Holder>{
        private List<String>list;

        public R_adapter(List<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.t_recyclerview_item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            TextView textView;
            public Holder(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.t_RecyclerView_itemText);
            }
        }
    }
}
