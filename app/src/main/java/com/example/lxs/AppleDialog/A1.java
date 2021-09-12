package com.example.lxs.AppleDialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;

public class A1 extends AppCompatActivity {
    private Button dialog_button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apple_qq_dialog);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog_button=findViewById(R.id.dialog_button);
        dialog_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View();
            }
        });
    }
    public void View(){
        final MDialog mDialog=new MDialog(this);
        mDialog.onClickBtn_qudin(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()){
                    mDialog.hide();
                    finish();
                }
            }
        });
        mDialog.onClickBtn_quxiao(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()&&mDialog!=null){
                    mDialog.hide();
                }
            }
        });
        mDialog.show();
    }
}
