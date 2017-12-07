package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by lincolnchawora on 01/12/2017.
 */

public class MySurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder myHolder;
    Thread myThread;
    boolean isRunning = true;
    Paint pWhite;
    GameObject myObject;
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    Player player;


    public MySurfaceView(Context context) {
        super(context);

        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);

        myHolder = getHolder();

        myThread = new Thread(this);
        myThread.start();

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.calcium);

        myObject = new GameObject(getContext(),100,100,10,10,drawable);

        gameObjects.add(new GameObject(getContext(), 100, 500, 10, -10, ContextCompat.getDrawable(context, R.drawable.copper)));

        gameObjects.add(new GameObject(getContext(), 500, 500, -10, 10, ContextCompat.getDrawable(context, R.drawable.magnesium)));

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if(myObject.isTouchInRectangle(event.getX(),event.getY()))
                {
                    Log.d("LC", "touched this");
                }
                return false;
            }
        });

    }


    @Override
    public void run(){
        while(isRunning) {
            if (!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();

            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), pWhite);
            myObject.Move(canvas);

//
            myHolder.unlockCanvasAndPost(canvas);
        }


    }

//    public void stop() {
//        isRunning = false;
//
//        while (true){
//            try {
//                myThread.join();
//                break;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            break;
//        }
//    }
//
//    public void start() {
//        isRunning = true;
//        myThread = new Thread(this);
//        myThread.start();
//    }
}
