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

        myThread = new Thread(this);
        myThread.start();

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.calcium);

        myObject = new GameObject(getContext(), 700, 700, 10, -10, drawable, false);

        gameObjects.add(myObject);

        gameObjects.add(new GameObject(getContext(), 100, 500, 10, -10, ContextCompat.getDrawable(context, R.drawable.copper), false));

        gameObjects.add(new GameObject(getContext(), 500, 500, -10, 10, ContextCompat.getDrawable(context, R.drawable.magnesium), true));

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gameObjects.get(0).setTag(1);
                gameObjects.get(1).setTag(2);
                gameObjects.get(2).setTag(3);
                for (GameObject object : gameObjects) {

                    // checks is the pointer is in line with any of the game objects
                    if (object.isTouchInRectangle(event.getX(), event.getY()) && !object.hasStopped) {

                        object.dx = 0;
                        object.dy = 0;

                        object.hasStopped = true;

                        if (object.hasStopped){
                            activity.btn.setEnabled(true);
                        }



                        if(object.equals(gameObjects.get(0))){
                            Toast.makeText(getContext(), "This is calcium!", Toast.LENGTH_SHORT).show();

                        } else if(object.equals(gameObjects.get(1))) {

                            Toast.makeText(getContext(), "This is copper!", Toast.LENGTH_SHORT).show();
                        } else if(object.equals(gameObjects.get(2))) {

                            Toast.makeText(getContext(), "This is magnesium!", Toast.LENGTH_SHORT).show();

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

            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), pWhite);
            //canvas.drawText("Which mineral ions \n are needed for chlorophyll in plants?", canvas.getWidth() / 14, canvas.getHeight() / 14, pBlack);
            myObject.Move(canvas);

            for (GameObject gameObject : gameObjects) {
                gameObject.Move(canvas);
            }

            myHolder.unlockCanvasAndPost(canvas);
        }

    }

}
