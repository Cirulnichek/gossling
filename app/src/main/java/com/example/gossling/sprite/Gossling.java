package com.example.gossling.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.gossling.R;

public class Gossling {
    private Context context;
    private Bitmap img;
    private int height, width;
    private float  x = 500, y = 100;
    int speed = 0;
    double angle = 0;

    public Gossling(Context context) {
        this.context = context;
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.gossling2);
        height = img.getHeight();
        width = img.getWidth();
    }

    public Gossling(Context context, float x, float y, int speed, double angle) {
        this.context = context;
        this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.gossling2);
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.speed = speed;
        this.angle = angle;
    }

    public Bitmap getImg() {
        return img;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(img, x, y, paint);
    }

    public void update() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }
}
