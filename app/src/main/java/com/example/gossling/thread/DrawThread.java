package com.example.gossling.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import com.example.gossling.ProcessingOfUserData;
import com.example.gossling.R;
import com.example.gossling.sprite.Car;
import com.example.gossling.sprite.End;
import com.example.gossling.sprite.Gossling;

public class DrawThread extends Thread {
    private SurfaceHolder holder;
    private boolean working;
    private Context context;
    private Paint paint;
    private Gossling gossling;
    private Car tesla, camaro, mcqueen;
    private ProcessingOfUserData processing;

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

    public DrawThread(Context context, SurfaceHolder surfaceHolder,
                      float startx, float starty, float endx, float endy, int speed, double angle) {
        this.holder = surfaceHolder;
        this.context = context;
        this.working = true;

        this.gossling = new Gossling(context, startx, starty, endx, endy, speed, angle);
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
                    this.gossling.update();
                    this.gossling.draw(canvas, paint);
                    this.tesla.draw(canvas, paint);
                    this.mcqueen.draw(canvas, paint);
                    this.camaro.draw(canvas, paint);
                    if (gossling.intersect(camaro)) {
                        new End(context, R.drawable.end, 0, 0).draw(canvas, paint);
                        requestStop();
                    }
                    if (gossling.intersect(tesla)) {
                        new End(context, R.drawable.end, 0, 0).draw(canvas, paint);
                        requestStop();
                    }
                    if (gossling.intersect(mcqueen)) {
                        new End(context, R.drawable.end, 0, 0).draw(canvas, paint);
                        requestStop();
                    }
                } finally {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public Pair<Float, Float> requestStop() {
        this.working = false;
        return new Pair<>(gossling.getX(), gossling.getY());
    }
}