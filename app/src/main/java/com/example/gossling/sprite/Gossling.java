package com.example.gossling.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.example.gossling.R;

public class Gossling {
    private Context context;
    private Bitmap img;
    private int height, width;
    private float x, y;
    private float end_x, end_y;
    int speed = 0;
    double angle = 0;
    int w, h;

    public Gossling(Context context) {
        this.context = context;
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.gossling2);
        height = img.getHeight();
        width = img.getWidth();
        x = 100;
        y = 150;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        h = displayMetrics.heightPixels;
        w = displayMetrics.widthPixels;
    }

    public Gossling(Context context, float x, float y, float end_x,
                    float end_y, int speed, double angle) {
        this.context = context;
        this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.gossling2);
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.speed = speed;
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.end_x = end_x;
        this.end_y = end_y;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        h = displayMetrics.heightPixels;
        w = displayMetrics.widthPixels;
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
        if (Math.abs(end_x - (x + 100)) < 50 &&
                Math.abs(end_y - (y + 75)) < 75) {
            speed = 0;
            angle = 0;
        }
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
        if (x < 20 || x > h - 20 || y < 20 || y > w - 20) {
            speed = 0;
            angle = 0;
            x -= speed * Math.cos(angle);
            y -= speed * Math.sin(angle);
        }
    }

    public Rect getBoundingBoxRect () {
        return new Rect((int)x, (int)y, (int)(x + width), (int)(y + height));
    }

    public boolean intersect (Car s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
}
