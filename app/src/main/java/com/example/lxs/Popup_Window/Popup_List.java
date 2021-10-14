package com.example.lxs.Popup_Window;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lxs.R;

import java.util.List;

public class Popup_List extends RecyclerView.Adapter<Popup_List.Popup_ViewHolder> {
    private List<List_a1>list;
    private Context context;
    private View view;
    public Popup_List(List<List_a1> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Popup_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lxs_text_img_buju,null,false);
        Popup_ViewHolder viewHolder=new Popup_ViewHolder(view);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=viewHolder.getAbsoluteAdapterPosition();
                List_a1 c=list.get(a);
                String text=c.getText();
                inPopup(v,text);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Popup_ViewHolder holder, int position) {
        List_a1 list_a1=list.get(position);
        holder.textView.setText(list_a1.getText());
        holder.imageView.setImageResource(list_a1.getImage());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class Popup_ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public Popup_ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.lxs_view_text);
            imageView=itemView.findViewById(R.id.lxs_view_img);
        }
    }

    private void inPopup(View v,String text){
        View view=LayoutInflater.from(v.getContext()).inflate(R.layout.popup_button_layout,null,false);
        Button button=view.findViewById(R.id.popup_button);
        Button button1=view.findViewById(R.id.popup_button2);
        PopupWindow popupWindow=new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(v,0,0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你喜欢"+text,Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"你讨厌"+text,Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
    }
}
