package com.example.demony;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class Image implements View.OnTouchListener {
    private ImageView imageView;
    private Matrix matrix = new Matrix();
    private Matrix current = new Matrix();
    private PointF start = new PointF();
    private int NONE = 9;
    private int mode = NONE, DROP = 1, ZOOM = 5;
    private float dis, scale;
    private PointF mpointF;

    public Image(ImageView imageView) {
        this.imageView = imageView;
    }

    public float tance(MotionEvent event)
    {
        float dx  =event.getX(1)-event.getX(0);
        float dy = event.getY(1)-event.getY(0);
        return  (float) Math.sqrt(dx*dx+dy*dy);
    }
    public static  PointF tance1(MotionEvent event)
    {
        float dx  =(event.getX(0)+event.getX(1))/2;
        float dy = (event.getY(0)+event.getY(1))/2;
        return  new PointF(dx,dy);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = DROP;
                matrix.set(imageView.getImageMatrix());
                current.set(imageView.getImageMatrix());
                start.set(motionEvent.getX(), motionEvent.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DROP) {
                    float dx = motionEvent.getX() - start.x;
                    float dy = motionEvent.getY() - start.y;
                    matrix.set(current);
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float end = tance(motionEvent);
                    if (end > 10f)
                    {
                        float sca =end/dis;
                        matrix.set(current);
                        matrix.postScale(sca,sca,mpointF.x,mpointF.y);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode=0;
                break;
            case MotionEvent.ACTION_UP:
                mode=NONE;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mode=ZOOM;
                dis  =tance(motionEvent);
                if (dis>10f)
                {
                    mpointF = tance1(motionEvent);
                    scale = imageView.getScaleX();
                    current.set(imageView.getImageMatrix());

                }

        }
        imageView.setImageMatrix(matrix);
        return true;
    }
}
