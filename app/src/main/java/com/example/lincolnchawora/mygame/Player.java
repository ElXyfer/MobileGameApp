package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by lincolnchawora on 05/12/2017.
 */

public class Player extends GameObject {

    public Player(Context context, float x, float y, float dx, float dy, Drawable image) {
        super(context, x, y, dx, dy, image);
    }

//    @Override
//    void Move(Canvas canvas) {
//        image.setBounds((int)x-200,(int)y,(int)(x+200f),(int)(y+200f));
//
//        image.draw(canvas);
//
//    }
    public void moveToo(float X, float Y) {
        if(X > x) {
            dx = 45;
        } else {
            dx=-45;
        }
        if(Y > y) {
            dy=0;
        } else {
            dy =0;
        }
    }
}
