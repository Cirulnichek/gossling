package com.example.gossling.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.gossling.thread.DrawThread;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private DrawThread drawThread;
    private SurfaceHolder holder;
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


}
