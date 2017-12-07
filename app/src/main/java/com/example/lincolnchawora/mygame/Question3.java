package com.example.lincolnchawora.mygame;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Question3 extends AppCompatActivity {

    MySurfaceView mySurfaceView;
    ViewGroup content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String Ans1 = extras.getString("Q1Answer");
            String Answer2 = extras.getString("Q2Answer");

            Toast.makeText(Question3.this, "Question 1 Answer = "+ Ans1 + " Question 2 Answer = "+ Answer2, Toast.LENGTH_LONG).show();
        }

        mySurfaceView = new MySurfaceView(this);

        content = (ViewGroup)findViewById(R.id.content_main3);


        content.addView(mySurfaceView); // workshop 6

//        switch (id) {
//            case R.id.item1:
//                mySurfaceView.stop();
//                return true;
//            case R.id.item2:
//                mySurfaceView.start();
//                return true;
//        }

    }
}
