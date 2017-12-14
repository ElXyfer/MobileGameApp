package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int SplashTimer;
    AnimationDrawable animationLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView animation = (ImageView) findViewById(R.id.animView);

        animationLoading = (AnimationDrawable)animation.getDrawable();

        // starts animation
        animationLoading.start();

        // timer duration
        SplashTimer = 35;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent SplashIntent = new Intent(MainActivity.this, Question1.class );
                startActivity(SplashIntent);
                finish();

            }
        }, SplashTimer);
    }
}
