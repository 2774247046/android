package com.example.lxs.View_Flipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;

import com.example.lxs.R;

public class huo_View_Flipper extends Activity {
    private MyGestureListener mgListener;
    private GestureDetector mDate;
    private int[]a={R.drawable.th1,R.drawable.th2,R.drawable.th3,R.drawable.th4};
    private ViewFlipper viewFlipper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper_layout);
        mgListener=new MyGestureListener();
        mDate=new GestureDetector(huo_View_Flipper.this,mgListener);
        viewFlipper=findViewById(R.id.vflp_help);
        for (int i=0;i<a.length;i++){
            viewFlipper.addView(getImage(a[i]));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDate.onTouchEvent(event);
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX()-e1.getX()>200){
                viewFlipper.setInAnimation(huo_View_Flipper.this,R.anim.right_in);
                viewFlipper.setOutAnimation(huo_View_Flipper.this,R.anim.right_ou);
                viewFlipper.showPrevious();
            }else if (e2.getX()-e1.getX()>200) {
                viewFlipper.setInAnimation(huo_View_Flipper.this,R.anim.left_in);
                viewFlipper.setOutAnimation(huo_View_Flipper.this,R.anim.left_ou);
                viewFlipper.showNext();
            }
            return true;
        }
    }
    private ImageView getImage(int res){
        ImageView img = new ImageView(this);
        img.setBackgroundResource(res);
        return img;
    }
}

