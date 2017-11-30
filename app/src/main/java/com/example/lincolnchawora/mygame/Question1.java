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

public class Question1 extends AppCompatActivity {

    TextView q1Txt;
    ImageView imgView;
    EditText textBox;
    Button conBtn;
    String answer;
    MediaPlayer correctSound;
    MediaPlayer wrongSound;
    Vibrator vibrator;

    // pop ups
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        answer = "green";

        q1Txt = (TextView)findViewById(R.id.q1Text);
        q1Txt.setText("Name the highlighted part of the flower");
        q1Txt.setTextSize(30);
        q1Txt.setTextColor(Color.BLACK);

        imgView = (ImageView)findViewById(R.id.imageView);
        textBox = (EditText)findViewById(R.id.editText);

        conBtn = (Button)findViewById(R.id.buttonTest);
        conBtn.setText("Check answer");

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);

        conBtn.setEnabled(false);


        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        correctSound = MediaPlayer.create(this, R.raw.correct);
        wrongSound = MediaPlayer.create(this, R.raw.wrong);

        //InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        if(imm.isAcceptingText()) {
//            q1Txt.setTextColor(Color.RED);
//        } else {
//            q1Txt.setTextColor(Color.BLUE);
//        }

        //final View constraintLayout = findViewById(R.id.constraint);


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

                if(textBox.getText().toString().toLowerCase().equals(answer)){

                    CorrectFunction();

                } else if(!textBox.getText().toString().toLowerCase().equals(answer)) {

                    WrongFunction();

                }
            }
        });

//        if(imm.isAcceptingText()) {
//            Log.i("me tag","Software Keyboard was shown");
//        } else {
//            Log.i("me tag", "Software Keyboard was not shown");
//        }

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

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(Question1.this, Question2.class); // change this to next question
                startActivity(testIntent);
            }
        });
    };

    void WrongFunction() {
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.wrongpopup, null);
        TextView wrongTxt = container.findViewById(R.id.WrongText);
        popupWindow = new PopupWindow(container, 990,200);
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

        wrongTxt.setText("Correct answer: " + answer); // "\n"

        wrongSound.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,1));
        }

        conBtn.setText("Continue");

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent = new Intent(Question1.this, Question2.class); // change this to next question
                startActivity(testIntent);
            }
        });
    };

}
