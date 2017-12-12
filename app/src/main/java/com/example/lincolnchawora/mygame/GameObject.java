package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lincolnchawora on 01/12/2017.
 */

public class GameObject extends View {
    protected float x, y, dx, dy;
    Paint p = new Paint();
    Rect r;
    float originalxSpeed, originalySpeed;
    boolean hasStopped, isCorrect;


    public GameObject(Context context, float  x, float y, float dx, float dy, Drawable image, boolean isCorrect) {
        super(context);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.originalxSpeed = dx;
        this.originalySpeed = dy;
        this.image = image;
        p.setColor(Color.RED);
        p.setTextSize(100);
        this.isCorrect = isCorrect;

        r = image.copyBounds();

    }


    void Move (Canvas canvas) {
        x+=dx;
        y+=dy;
        if(x>canvas.getWidth() || x<0)
            dx=-dx;
        if(y>canvas.getHeight() || y<0)
            dy=-dy;

        image.setBounds((int)x-200,(int)y,(int)(x+10f),(int)(y+170f));
        image.draw(canvas);

        r = image.copyBounds();

    }

    public boolean isTouchInRectangle(float tx, float ty)
    {
        if(
                tx>(x-200) && tx<(x+10f)

            &&

            ty>y && ty<(y+170f)
        )

        return true;

        else

            return false;
    }

    protected Drawable image;




}
