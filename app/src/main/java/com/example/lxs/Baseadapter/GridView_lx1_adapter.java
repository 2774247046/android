package com.example.lxs.Baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxs.List.GridView_list;
import com.example.lxs.R;

import java.util.List;

public class GridView_lx1_adapter extends BaseAdapter {
    private List<GridView_list>list;
    private Context context;
    public GridView_lx1_adapter(List<GridView_list> list, Context context) {
        this.list = list;
        this.context = context;
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
        HH hh;
        if (convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_text_img,parent,false);
            hh= new HH();
            hh.img=convertView.findViewById(R.id.GridView_img);
            hh.text=convertView.findViewById(R.id.GridView_text);
            convertView.setTag(hh);
        }else{
            hh=(HH) convertView.getTag();
        }
        GridView_list gridView_list= list.get(position);
        hh.text.setText(gridView_list.getText());
        hh.img.setImageResource(gridView_list.getImg());
        return convertView;
    }
    static class HH{
        TextView text;
        ImageView img;
    }
}
