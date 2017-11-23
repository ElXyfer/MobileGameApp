package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button H_btn;
    MediaPlayer ding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        H_btn = (Button) findViewById(R.id.startBtn);

        ding = MediaPlayer.create(this, R.raw.correct);

        H_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                H_btn.setTextColor(Color.GREEN);

//                ding.start();

                Intent page1Intent = new Intent(MainActivity.this, Question1.class);
                startActivity(page1Intent);
            }
        });


    }
}
