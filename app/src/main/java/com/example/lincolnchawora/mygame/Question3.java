package com.example.lincolnchawora.mygame;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Question3 extends AppCompatActivity {

    MySurfaceView mySurfaceView;
    ViewGroup content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);


        mySurfaceView = new MySurfaceView(this);

        content = (ViewGroup)findViewById(R.id.content_main3);


        content.addView(mySurfaceView); // workshop 6





    }
}
