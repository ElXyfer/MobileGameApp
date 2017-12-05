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

            Log.i("Muy log ", "work work " + extras.getInt("Q1Answer"));
            String val = extras.getString("Q1Answer");
//            String val2 = extras.getString("Q2Answer");
            Toast.makeText(Question3.this, "val="+ val, Toast.LENGTH_LONG).show();
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
