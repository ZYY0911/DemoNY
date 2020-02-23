package com.example.demony;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class Video extends VideoView {
    public Video(Context context) {
        super(context);
    }

    public Video(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Video(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = getDefaultSize(0,heightMeasureSpec);
        int w = getDefaultSize(0,widthMeasureSpec);
        setMeasuredDimension(w,h);
    }
}
