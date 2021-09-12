package com.example.lxs.w;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class w_Paint extends View{
    private float a=40;
    private float u=50;
    private Paint paint=new Paint();
    private Paint paint2=new Paint();
    public w_Paint(Context context) {
        super(context);
    }
    public w_Paint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public w_Paint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public w_Paint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawCircle(a,u,15,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        a=event.getX();
        u=event.getY();
        invalidate();
        return true;
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
}
