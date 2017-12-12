package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by lincolnchawora on 01/12/2017.
 */

public class Question extends AppCompatActivity {

    TextView q1Txt, option1, option2, option3, option4, choice1;
    Button conBtn;
    ImageView imgView;
    EditText textBox;
    String answer;
    MediaPlayer correctSound, wrongSound;
    Vibrator vibrator;

    // pop ups
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // question 1
//        answer = "green";
//        conBtn = (Button)findViewById(R.id.continueQ1);
//        conBtn.setEnabled(false);
//
//        q1Txt = (TextView)findViewById(R.id.q1Text);
//        q1Txt.setText("Name the highlighted part of the flower");
//        q1Txt.setTextSize(30);
//        q1Txt.setTextColor(Color.BLACK);
//
//
//        imgView = (ImageView)findViewById(R.id.q1_Image);
//        textBox = (EditText)findViewById(R.id.editText);


//        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);
//        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
        correctSound = MediaPlayer.create(this, R.raw.correct);
        wrongSound = MediaPlayer.create(this, R.raw.wrong);

    }

    final public void CorrectSound(){
        correctSound.start();
    };

    void Correct (){
//        conBtn.setBackgroundColor(Color.rgb(115,230,0));
//        conBtn.setTextColor(Color.WHITE);

        // initilialise layout inflater
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        //correctSound.start();

        // new layout, pass success pop up
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.successpopup, null);

        // create pop up window, pass created layout (with, height)
        popupWindow = new PopupWindow(container, 991,200);

        // show pop up window,
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);




    };

}
