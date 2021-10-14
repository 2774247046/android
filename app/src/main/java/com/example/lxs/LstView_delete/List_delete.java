package com.example.lxs.LstView_delete;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lxs.R;
public class List_delete extends FrameLayout {
    private View contentView;
    private View menuView;

    private int viewHeight; //高是相同的
    private int contentWidth;
    private int menuWidth;
    private Scroller scroller;
    public List_delete(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView=findViewById(R.id.listView_content);
        menuView=findViewById(R.id.listView_menu);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        viewHeight=getMeasuredHeight();
        contentWidth=contentView.getMeasuredWidth();
        menuWidth=menuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        menuView.layout(contentWidth,0,contentWidth+menuWidth,viewHeight);
    }
    private float startX;
    private float startY;

    private float downX;
    private float downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                startY=event.getY();
                if (onStateChangeListener != null){
                    onStateChangeListener.onMove(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float endx=event.getX();
                float endy=event.getY();
                float distanceX=endx-startX;
                Log.d("distanceX",distanceX+"");
                int toScrollX = (int) (getScrollX()-distanceX);
                Log.d("toScrollX",toScrollX+"");
                if (toScrollX<0){
                    toScrollX=0;
                }
                if (toScrollX>menuWidth){
                    toScrollX=menuWidth;
                }
                scrollTo(toScrollX,getScrollY());
                startX = event.getX();
                float dx = Math.abs(event.getX()-downX);
                float dy = Math.abs(event.getY()-downY);
                if (dx > dy && dx > 6){
                    //事件反拦截，使父ListView的事件传递到自身SlideLayout
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (getScrollX() > menuWidth/2){
                    //打开menu
                    openMenu();
                }else {
                    closeMenu();
                }
                break;
        }
        return true;
    }
    public void openMenu() {
        int dx = menuWidth-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null){
            onStateChangeListener.onOpen(this);
        }
    }
    public void closeMenu() {
        //0表示menu移动到的目标距离,目标位置-起始位置
        int dx = 0-getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(),dx, getScrollY());
        invalidate();
        if (onStateChangeListener != null){
            onStateChangeListener.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

//    @Override
//    public boolean onInterceptHoverEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case  MotionEvent.ACTION_DOWN:
//                downX=startX=event.getX();
//                downY=startY=event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float dx = Math.abs(event.getX()-downX);
//                float dy = Math.abs(event.getY()-downY);
//                if (dx > dy && dx > 6){
//                    //拦截事件
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return super.onInterceptTouchEvent(event);
//    }
    public interface OnStateChangeListener{
        void onOpen(List_delete slideLayout);
        void onMove(List_delete slideLayout);
        void onClose(List_delete slideLayout);
    }
    public OnStateChangeListener onStateChangeListener;
    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }
}
