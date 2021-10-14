package com.example.lxs;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lxs.List.lxs1_list;

import java.util.ArrayList;
import java.util.List;
public class lxs1 extends AppCompatActivity {
    private List<lxs1_list>list=new ArrayList<lxs1_list>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lxs1);
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        init();
        RecyclerView recyclerView=findViewById(R.id.lxs_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView_Adapter recyclerView_adapter=new RecyclerView_Adapter(list);
        recyclerView.setAdapter(recyclerView_adapter);
    }
    class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.GG>{
        private List<lxs1_list>listt;
        public RecyclerView_Adapter(List<lxs1_list> list) {
            this.listt = list;
        }
        @NonNull
        @Override
        public GG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.lxs_text_img_buju,parent,false);
            GG gg=new GG(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a=gg.getAbsoluteAdapterPosition();
                    lxs1_list o=listt.get(a);
                   new AlertDialog.Builder(lxs1.this)
                           .setTitle(o.getText())
                           .setIcon(o.getImg())
                           .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                           .setNeutralButton("6", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           })
                            .show();
                }
            });
            return gg;
        }
        @Override
        public void onBindViewHolder(@NonNull GG holder, int position) {
            lxs1_list l=listt.get(position);
            holder.imageView.setImageResource(l.getImg());
            holder.textView.setText(l.getText());
        }

        @Override
        public int getItemCount() {
            return listt.size();
        }
        class GG extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;
            public GG(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.lxs_view_text);
                imageView=itemView.findViewById(R.id.lxs_view_img);
            }
        }
    }
    public void init() {
        for (int i = 0; i < 15; i++) {
            lxs1_list listt = new lxs1_list("张三", R.drawable.th);
            list.add(listt);
        }
    }
}
