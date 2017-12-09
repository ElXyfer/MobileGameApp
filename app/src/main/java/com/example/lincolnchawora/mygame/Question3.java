package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Question3 extends AppCompatActivity {

    MySurfaceView mySurfaceView;
    ViewGroup content;
    Button btn;
    TextView txt;
    Vibrator vibrator;
//    String Q3AnswerTag;

    // pop ups
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Q3Intent = new Intent(Question3.this, LearnPage.class); // change this to next question

//            String Ans1 = answer;
//            Q1Intent.putExtra("Q1Answer", Ans1);
            startActivity(Q3Intent);
//            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint3);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String Ans1 = extras.getString("Q1Answer");
            String Answer2 = extras.getString("Q2Answer");

            Toast.makeText(Question3.this, "Question 1 Answer = "+ Ans1 + " Question 2 Answer = "+ Answer2, Toast.LENGTH_LONG).show();
        }

        mySurfaceView = new MySurfaceView(this, this);

        content = (ViewGroup)findViewById(R.id.constraint3);
        content.addView(mySurfaceView); // workshop 6

        txt = (TextView)findViewById(R.id.q3Text);
        btn = (Button)findViewById(R.id.q3Button);


        txt.bringToFront();

        btn.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (GameObject object : mySurfaceView.gameObjects){

                    if(object.isCorrect && object.hasStopped){
                        CorrectFunction();
                    } else {
                        WrongFunction();
                    }
                }
            }
        });

    }

    void CorrectFunction (){
        btn.setBackgroundColor(Color.rgb(115,230,0));
        btn.setTextColor(Color.WHITE);

        // initilialise layout inflater
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

       // correctSound.start();

        // new layout, pass success pop up
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.successpopup, null);

        // create pop up window, pass created layout (with, height)
        popupWindow = new PopupWindow(container, 991,200);

        // show pop up window,
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);


        btn.setText("Continue");
        btn.setOnClickListener(clickListener);

    };

    void WrongFunction() {
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.wrongpopup, null);
        TextView wrongTxt = container.findViewById(R.id.WrongText);
        popupWindow = new PopupWindow(container, 990,200);
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

       wrongTxt.setText("Correct answer: Magnesium (Mg)" ); // "\n"

       // wrongSound.start();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            vibrator.vibrate(VibrationEffect.createOneShot(500,1));
//        }

        btn.setText("Continue");
        btn.setOnClickListener(clickListener);


    };

}
