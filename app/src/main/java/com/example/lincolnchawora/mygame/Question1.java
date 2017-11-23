package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Timer;

public class Question1 extends AppCompatActivity {

    TextView q1Txt;
    TextView warnTxt;
    ImageView imgView;
    EditText textBox;
    Button conBtn;
    String answer;

    // pop ups
    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        answer = "green";

        q1Txt = (TextView)findViewById(R.id.q1Text);
        q1Txt.setText("Name the highlighted part of the flower");
        q1Txt.setTextSize(30);
        q1Txt.setTextColor(Color.BLACK);

        imgView = (ImageView)findViewById(R.id.imageView);
        textBox = (EditText)findViewById(R.id.editText);



        warnTxt = (TextView)findViewById(R.id.warningTxt);
        warnTxt.setTextSize(15);
        warnTxt.setTextColor(Color.RED);

        conBtn = (Button)findViewById(R.id.buttonTest);
        conBtn.setText("Check answer");



        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);

        conBtn.setEnabled(false); // check


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

                if(textBox.getText().toString().equals("")) {


                } else if(textBox.getText().toString().toLowerCase().equals(answer)){
                    conBtn.setBackgroundColor(Color.rgb(115,230,0));
                    conBtn.setTextColor(Color.WHITE);

                    // initilialise layout inflater
                    layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                    // new layout, pass success pop up
                    ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.successpopup, null);

                    // create pop up window, pass created layout (with, height)
                    popupWindow = new PopupWindow(container, 990,200);

                    // show pop up window,
                    popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

                    conBtn.setText("Continue");

                    conBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent testIntent = new Intent(Question1.this, LearnPage.class); // change this to next question
                            startActivity(testIntent);
                        }
                    });


                } else if(!textBox.getText().toString().toLowerCase().equals(answer)) {
                    layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.wrongpopup, null);
                    TextView wrongTxt = container.findViewById(R.id.WrongText);
                    popupWindow = new PopupWindow(container, 990,200);
                    popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

                    wrongTxt.setText("Correct answer: " + answer); // "\n"

                    conBtn.setText("Continue");

                    conBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent testIntent = new Intent(Question1.this, LearnPage.class); // change this to next question
                            startActivity(testIntent);
                        }
                    });

                }
            }
        });

    }
}
