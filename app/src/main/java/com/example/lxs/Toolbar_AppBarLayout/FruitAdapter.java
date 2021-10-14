package com.example.lxs.Toolbar_AppBarLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lxs.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit>fruitList;
    private Context context;

    public FruitAdapter(List<Fruit> fruitList, Context context) {
        this.fruitList = fruitList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.toolbar_text_image,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=fruitList.get(position);
        holder.t_view.setText(fruit.getText());
        Glide.with(context).load(fruit.getImage()).into(holder.image);
    }
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView t_view;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t_view=itemView.findViewById(R.id.toolbar_text);
            image=itemView.findViewById(R.id.toolbar_image);
        }
    }

}
