package com.example.lincolnchawora.mygame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * Created by lincolnchawora on 01/12/2017.
 */

public class GameObject {
    protected float x, y, dx, dy;
    Paint p = new Paint();


    public GameObject(float x, float y, float dx, float dy, Drawable image) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.image = image;

        p.setColor(Color.RED);
        p.setTextSize(100);
    }

    void Move (Canvas canvas) {
        x+=dx;
        y+=dy;
        if(x>canvas.getWidth() || x<0)
            dx=-dx;
        if(y>canvas.getHeight() || y<0)
            dy=-dy;
//       canvas.drawText("Hello", x-100, y+10, p);

        image.setBounds((int)x-200,(int)y,(int)(x+200f),(int)(y+200f));

        image.draw(canvas);

    }


    protected Drawable image;




}
