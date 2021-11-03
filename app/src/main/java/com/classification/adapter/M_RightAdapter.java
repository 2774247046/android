package com.classification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;

public class M_RightAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    public M_RightAdapter(Context context, List<String> list) {
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
        Holder holder=new Holder();
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.minu_class_item,parent,false);
            holder.minute_class_text=convertView.findViewById(R.id.minute_class_text_item);
            holder.minute_gridview_item=convertView.findViewById(R.id.minute_gridview_item);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }

        String a[]={"张三","李四","王五"};
        holder.minute_class_text.setText(a[position]);

        MinuteAdapter minuteAdapter=new MinuteAdapter(context);
        holder.minute_gridview_item.setAdapter(minuteAdapter);
        return convertView;
    }
    class Holder{
        TextView minute_class_text;
        GridView minute_gridview_item;
    }
}
