package com.example.lxs.AppleDialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.lxs.R;

public class MDialog extends Dialog {
    private Button btn_qudin,btn_quxiao;
    public MDialog(@NonNull Context context) {
        super(context);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.apple_dialog,null,false);
        btn_qudin=view.findViewById(R.id.btn_qudin);
        btn_quxiao=view.findViewById(R.id.btn_quxiao);
        setContentView(view);
    }
    public void onClickBtn_qudin(View.OnClickListener a){
        btn_qudin.setOnClickListener(a);
    }
    public void onClickBtn_quxiao(View.OnClickListener a){
        btn_quxiao.setOnClickListener(a);
    }
}
