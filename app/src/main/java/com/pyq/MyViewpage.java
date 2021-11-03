package com.pyq;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class MyViewpage implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View page, float position) {
        //        -1等于左滑
        if (position<-1){
            position=-1;
//            1等于右滑
        }else if (position > 1){
            position = 1;
        }
        float tempScale = position < 0 ? 1 + position : 1 - position; // [0,1]
        float scaleValue = 0.9f + tempScale * 0.1f; // [0,1]
//        默认下标为0的显示0.9f下标为1的显示0.1f
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);
    }
}
