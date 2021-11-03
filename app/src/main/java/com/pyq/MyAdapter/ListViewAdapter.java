package com.pyq.MyAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lxs.R;
import com.pyq.MyViewpage_Main;

import java.io.Serializable;
import java.util.List;
public class ListViewAdapter extends BaseAdapter implements View.OnClickListener{
    private Context context;
    private List<Grid_View_data>list;
    private int z;
    public ListViewAdapter(Context context, List<Grid_View_data> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return 1;
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
        Holder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.listview_item_layout,parent,false);
            holder=new Holder();
            holder.gridView=convertView.findViewById(R.id.list_view_GridView);
            holder.button=convertView.findViewById(R.id.like_img);
            holder.button.setOnClickListener(this);
            convertView.setTag(holder);
        }else{
            holder= (Holder) convertView.getTag();
        }
        holder.gridView.setAdapter(new GridViewAdapter(context,list));
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(context, MyViewpage_Main.class);
                intent.putExtra("id",position);
                intent.putExtra("image", (Serializable) list);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.like_img:
                ImageView imageView=v.findViewById(R.id.like_img);
                if (z==1){
                    imageView.setImageResource(R.mipmap.zan1);
                    z=0;
                    break;
                }
                if (z==0){
                    imageView.setImageResource(R.mipmap.zan2);
                    z=1;
                }
                break;
        }
    }
    class Holder{
        GridView gridView;
        ImageView button;
    }
}
