package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

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

    OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            player.moveToo(event.getX(), event.getY());
            return true;
        }
    };

    public MySurfaceView(Context context) {
        super(context);

        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);

        myHolder = getHolder();

        myThread = new Thread(this);
        myThread.start();

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.table);

        myObject = new GameObject(100,100,10,10,drawable);

        gameObjects.add(new GameObject(100, 500, 10, -10, ContextCompat.getDrawable(context, R.drawable.cry)));

        gameObjects.add(new GameObject(500, 500, -10, 10, ContextCompat.getDrawable(context, R.drawable.woman)));

        gameObjects.add(new GameObject(300, 300, 10, 10, ContextCompat.getDrawable(context, R.drawable.fish)));

        player = new Player(300, 300, -10, -10, ResourcesCompat.getDrawable(getResources(), R.drawable.man, null));

        gameObjects.add(player);

        this.setOnTouchListener(touchListener);
    }





    @Override
    public void run(){
        while(isRunning) {
            if (!myHolder.getSurface().isValid())
                continue;
            Canvas canvas = myHolder.lockCanvas();

            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), pWhite);
            myObject.Move(canvas);


            for (GameObject gameObject : gameObjects)
                gameObject.Move(canvas);

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
