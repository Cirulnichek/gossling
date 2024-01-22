package com.example.gossling.sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class End {
    private Context context;
    private Bitmap img;
    private int height, width;
    float x, y;

    public End(Context context, int id, float x, float y) {
        this.context = context;
        img = BitmapFactory.decodeResource(context.getResources(), id);

        this.x = x;
        this.y = y;

        this.height = img.getHeight();
        this.width = img.getWidth();
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

    public Rect getBoundingBoxRect () {
        return new Rect((int)x, (int)y, (int)(x + width), (int)(y + height));
    }

    public void setHeight(int height) {
        this.img.setHeight(height);
    }

    public void setWidth(int width) {
        this.img.setWidth(width);
    }
}