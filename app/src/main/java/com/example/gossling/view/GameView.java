package com.example.gossling.view;

import android.content.Context;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.gossling.thread.DrawThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private DrawThread drawThread;
    private SurfaceHolder holder;
    private float x, y;
    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.context = context;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.holder = holder;
        drawThread = new DrawThread(context, holder);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawThread.requestStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Pair<Float, Float> p = drawThread.requestStop();
        float Gossling_x = p.first;
        float Gossling_y = p.second;
        x = event.getRawX();
        y = event.getRawY();
        double angle;
        try {
            angle = Math.atan((y - Gossling_y) / (x - Gossling_x));
        } catch (Exception e) {
            if (y >= Gossling_y) {
                angle = Math.PI / 2;
            } else {
                angle = 3 * Math.PI / 2;
            }
        }
        if (Gossling_x > x) {
            angle += Math.PI / 2;
        }
        drawThread = new DrawThread(context, holder, Gossling_x, Gossling_y, 10, angle);
        drawThread.start();
        return true;
    }
}
