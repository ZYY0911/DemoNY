package com.example.demony;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class Solle extends HorizontalScrollView {
    public Solle(Context context) {
        super(context);
    }

    public Solle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Solle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public interface SetData{
        void setdata(int x);
    }
    public SetData data;
    public void SetData(SetData data)
    {
        this.data=data;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        data.setdata(l);
    }
}
