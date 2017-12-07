package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Question3 extends AppCompatActivity {

    MySurfaceView mySurfaceView;
    ViewGroup content;
    Button btn;
    TextView txt;

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

        txt = (TextView)findViewById(R.id.q3Text);

        mySurfaceView = new MySurfaceView(this, this);

        content = (ViewGroup)findViewById(R.id.content_main3);
        content.addView(mySurfaceView); // workshop 6

        btn = (Button)findViewById(R.id.q3Button);

        btn.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Q3Intent = new Intent(Question3.this, MainActivity.class); // change this to next question
                startActivity(Q3Intent);
                finish();
            }
        });

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
