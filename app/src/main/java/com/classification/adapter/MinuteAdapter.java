package com.classification.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;
public class MinuteAdapter extends BaseAdapter {
    private Context context;
    private List<MinuteList> list=new ArrayList<>();
    public MinuteAdapter(Context context) {
        this.context = context;
        init();
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
        itemView item=new itemView();
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.minute_gridview_item,parent,false);
            item.gridview_item_text=convertView.findViewById(R.id.gridview_item_text);
            item.gridview_item_img=convertView.findViewById(R.id.gridview_item_img);
            convertView.setTag(item);
        }else{
            item= (itemView) convertView.getTag();
        }
        MinuteList minuteList=list.get(position);
        item.gridview_item_text.setText(minuteList.getText());
        Glide.with(context).load(minuteList.getURl()).centerCrop().into(item.gridview_item_img);
        return convertView;
    }
    class itemView{
        TextView gridview_item_text;
        ImageView gridview_item_img;
    }
    private void init() {
        list.clear();
        for (int i = 0; i < 10; i++) {
            MinuteList minuteList = new MinuteList("1", "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF");
            list.add(minuteList);
        }
    }
}
