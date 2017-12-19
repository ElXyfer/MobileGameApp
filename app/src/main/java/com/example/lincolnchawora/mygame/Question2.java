package com.example.lincolnchawora.mygame;

import android.content.ClipData; //
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; //
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent; //
import android.view.View; //
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


public class Question2 extends AppCompatActivity {

    TextView A1, A2, A3, A4, LocationBox;
    Button conBtn;
    String Ans1, Answer2;
    MediaPlayer correctSound, wrongSound;
    Vibrator vibrator;

    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            popupWindow = null;
            Intent Q2Intent = new Intent(Question2.this, Question3.class); // change this to next question

            Q2Intent.putExtra("Q1Answer", Ans1);

            Q2Intent.putExtra("Q2Answer", Answer2);

            startActivity(Q2Intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        A1 = (TextView)findViewById(R.id.ans_1);
        A2 = (TextView)findViewById(R.id.ans_2);
        A3 = (TextView)findViewById(R.id.ans_3);
        A4 = (TextView)findViewById(R.id.ans_4);

        LocationBox = (TextView)findViewById(R.id.LBox);

        Ans1 = "";

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            Ans1 = extras.getString("Q1Answer");
        }

        LocationBox.setOnDragListener(new locationDragListener());


        A1.setOnTouchListener(new answerTouchListener());
        A2.setOnTouchListener(new answerTouchListener());
        A3.setOnTouchListener(new answerTouchListener());
        A4.setOnTouchListener(new answerTouchListener());

        conBtn = (Button)findViewById(R.id.button3);

        conBtn.setEnabled(false);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint2);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        correctSound = MediaPlayer.create(this, R.raw.correct);
        wrongSound = MediaPlayer.create(this, R.raw.wrong);


    }

    private final class answerTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder AnswerShadow = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, AnswerShadow, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    private class locationDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View dropView, DragEvent event) {

            final int action = event.getAction();

            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    return true;
                case DragEvent.ACTION_DROP:
                    //reference of item being dropped
                    View AnswerView = (View) event.getLocalState();

                    //stop displaying view in previous location
                    AnswerView.setVisibility(View.INVISIBLE);

                    //the view the dragged item is being released on
                    TextView dropLocation = (TextView) dropView;

                    //the view being dragged and released
                    TextView released = (TextView) AnswerView;

                    //update the text in the target view to reflect the data being released
                    dropLocation.setText(released.getText());

                    //if an item has already been released here, there will be a tag
                    Object viewTag = dropLocation.getTag();

                    if(viewTag != null) {
                          int viewID = (Integer)viewTag;

                          //set view visible
                          findViewById(viewID).setVisibility(View.VISIBLE);
                    }

                    final String Answer2 = released.getText().toString();

                    dropLocation.setTag(released.getId());

                    if(dropLocation.getText().toString().length() > 2) {
                        conBtn.setEnabled(true);
                        conBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            if(Answer2.equals("Sepal")){
                                CorrectFunction(Answer2);
                            } else {
                                WrongFunction(Answer2);
                            }
                            }
                        });
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
                default:
                    return true;
            }
            return true;
        }
    }

    void CorrectFunction(String answer) {
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

        Answer2 = answer;


        conBtn.setOnClickListener(clickListener);
    }

    void WrongFunction(String answer) {
        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.wrongpopup, null);
        TextView wrongTxt = container.findViewById(R.id.WrongText);
        popupWindow = new PopupWindow(container, 990,200);
        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);

        wrongTxt.setText("Correct answer: " + A2.getText().toString()); // "\n"

        wrongSound.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,1));
        }

        Answer2 = answer;

        conBtn.setText("Continue");

        conBtn.setOnClickListener(clickListener);

    };

}
