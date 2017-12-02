package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/**
 * Created by lincolnchawora on 01/12/2017.
 */

public class MySurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning = true;
    Paint pWhite;
    GameObject myObject;




    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ViewGroup.LayoutParams lp = this.getLayoutParams();
        lp.width = 100;
        this.setLayoutParams(lp);
    }

    public MySurfaceView(Context context) {
        super(context);

        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);

        myHolder = getHolder();


        myThread = new Thread(this);
        myThread.start();

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.table);

        myObject = new GameObject(100,100,10,10,drawable);

    }



    @Override
    public void run(){
        while(isRunning)
        {
            if(!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();

            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), pWhite);
            myObject.Move(canvas);
            myHolder.unlockCanvasAndPost(canvas);
        }


    }
}
