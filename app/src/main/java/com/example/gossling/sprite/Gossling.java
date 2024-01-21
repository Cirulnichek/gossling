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
    float x = 500, y = 100;

    public Gossling(Context context) {
        this.context = context;
        img = BitmapFactory.decodeResource(context.getResources(), R.drawable.gossling2);
        height = img.getHeight();
        width = img.getWidth();
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
}
