package com.example.lincolnchawora.mygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Question1 extends AppCompatActivity {

    ImageView imgView;
    EditText textBox;
    Button conBtn;
    String answer;
    MediaPlayer correctSound, wrongSound;
    Vibrator vibrator;

    // pop ups
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Q1Intent = new Intent(Question1.this, Question2.class); // change this to next question

            String Ans1 = answer;
            Q1Intent.putExtra("Q1Answer", Ans1);
            startActivity(Q1Intent);
            finish();
        }
    };

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        answer = "petal";

        imgView = (ImageView)findViewById(R.id.q1_Image);
        textBox = (EditText)findViewById(R.id.editText);
        conBtn = (Button)findViewById(R.id.continueQ1);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);
        correctSound = MediaPlayer.create(this, R.raw.correct);
        wrongSound = MediaPlayer.create(this, R.raw.wrong);

        conBtn.setEnabled(false);

        textBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                String ed_text = textBox.getText().toString().trim();

                if(ed_text.isEmpty()) {
                    conBtn.setEnabled(false);

                } else {
                    conBtn.setEnabled(true);

                }
                return false;
            }
        });

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String givenAnswer = textBox.getText().toString().toLowerCase().replaceAll(" ", "");

                if(givenAnswer.equals(answer)){
                    CorrectFunction();
                } else {
                    WrongFunction();
                    answer = textBox.getText().toString();
                }
            }
        });

    }

    void CorrectFunction (){
        conBtn.setBackgroundColor(Color.rgb(115,230,0));
        conBtn.setTextColor(Color.WHITE);

        // initilialise layout inflater
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        correctSound.start();

        // new layout, pass success pop up
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.successpopup, null);

        // create pop up window, pass created layout (with, height)
        popupWindow = new PopupWindow(container, 991,200);

        // show pop up window,
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

        conBtn.setText("Continue");
        conBtn.setOnClickListener(clickListener);

    };

    void WrongFunction() {
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.wrongpopup, null);
        TextView wrongTxt = container.findViewById(R.id.WrongText);
        popupWindow = new PopupWindow(container, 990,200);
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

        wrongTxt.setText("Correct answer: " + answer); // "\n"

        wrongSound.start();

        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        vibrate.vibrate(250);


        conBtn.setText("Continue");
        conBtn.setOnClickListener(clickListener);

    };

    @Override
    protected void onStop(){
        super.onStop();
        if(popupWindow != null){
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

}
