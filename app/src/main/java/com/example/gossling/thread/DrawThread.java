package com.example.gossling.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.gossling.R;
import com.example.gossling.sprite.Car;
import com.example.gossling.sprite.Gossling;

public class DrawThread extends Thread {
    private SurfaceHolder holder;
    private boolean working;
    private Context context;
    private Paint paint;
    private Gossling gossling;
    private Car tesla, camaro, mcqueen;

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.holder = surfaceHolder;
        this.context = context;
        this.working = true;

        this.gossling = new Gossling(context);
        this.camaro = new Car(this.context, R.drawable.camaro, 1600, 100);
        this.mcqueen = new Car(this.context, R.drawable.mcqueen, 400, 630);
        this.tesla = new Car(this.context, R.drawable.tesla, 1200, 560);

        this.paint = new Paint();
    }

    @Override
    public void run() {
        while (working) {
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                try {
                    canvas.drawColor(Color.WHITE);
                    this.gossling.draw(canvas, paint);
                    this.tesla.draw(canvas, paint);
                    this.mcqueen.draw(canvas, paint);
                    this.camaro.draw(canvas, paint);
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void requestStop() {
        this.working = false;
    }
}