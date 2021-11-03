package com.pyq.MyAdapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.lxs.List.GridView_list;
import com.example.lxs.R;
import com.pyq.MyViewpage_Main;

import java.util.List;
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Grid_View_data>list;

    public GridViewAdapter(Context context, List<Grid_View_data> list) {
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
        A a=new A();
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.grid_view_item,parent,false);
            a.imageView=convertView.findViewById(R.id.gridView_img);
            convertView.setTag(a);
        }else{
            a= (A) convertView.getTag();
        }
        Grid_View_data data=list.get(position);
        Glide.with(context).load(data.getIma()).centerCrop().into(a.imageView);
        return convertView;
    }
    class A{
        ImageView imageView;
    }
}
