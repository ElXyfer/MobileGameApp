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
    Question3 activity;

    public MySurfaceView(Context context, final Question3 activity) {
        super(context);

        this.activity = activity;

        pWhite = new Paint();
        pWhite.setColor(Color.WHITE);

        myHolder = getHolder();

        // instantiate & start thread
        myThread = new Thread(this);
        myThread.start();

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.calcium);

        myObject = new GameObject(getContext(), 700, 700, 10, -10, drawable, false, 1);


        // adds my object(s) to array
        gameObjects.add(myObject);



        gameObjects.add(new GameObject(getContext(), 100, 500, 10, -10, ContextCompat.getDrawable(context, R.drawable.copper), false, 2));

        gameObjects.add(new GameObject(getContext(), 500, 500, -10, 10, ContextCompat.getDrawable(context, R.drawable.magnesium), true, 3));


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // executes for each game object in the array
                for (GameObject object : gameObjects) {

                    // checks if the pointer is in line with any of the game objects
                    if (object.isTouchInRectangle(event.getX(), event.getY()) && !object.hasStopped) {

                        // set speeds to 0
                        object.dx = 0;
                        object.dy = 0;

                        object.hasStopped = true;

//                            if(object.hasStopped){
//                                activity.btn.setEnabled(true);
//                                Log.d("ET", "show me " + activity.btn.setEnabled(true));
//                            }


                        if(object.equals(gameObjects.get(0))){
                            Toast.makeText(getContext(), "This is calcium!", Toast.LENGTH_SHORT).show();

                            activity.btn.setEnabled(true);
                        } else if(object.equals(gameObjects.get(1))) {
                            Toast.makeText(getContext(), "This is copper!", Toast.LENGTH_SHORT).show();
                            activity.btn.setEnabled(true);
                        } else if(object.equals(gameObjects.get(2))) {
                            Toast.makeText(getContext(), "This is magnesium!", Toast.LENGTH_SHORT).show();
                            activity.btn.setEnabled(true);
                        }
                    } else {
                        object.hasStopped = false;

                        object.dx = object.originalxSpeed;
                        object.dy = object.originalySpeed;
                    }


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

            canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(), pWhite);
            myObject.Move(canvas);

            for (GameObject gameObject : gameObjects) {
                gameObject.Move(canvas);
            }

            myHolder.unlockCanvasAndPost(canvas);
        }

    }



}
