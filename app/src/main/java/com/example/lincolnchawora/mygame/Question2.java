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
import android.graphics.Typeface; //
import android.util.Log;

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

        //views to drag
        A1 = (TextView)findViewById(R.id.option_1);
        A2 = (TextView)findViewById(R.id.option_2);
        A3 = (TextView)findViewById(R.id.option_3);
        A4 = (TextView)findViewById(R.id.option_4);

        Ans1 = "";

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            Ans1 = extras.getString("Q1Answer");
        }

        //views to drop onto
        LocationBox = (TextView)findViewById(R.id.LBox);

        //set touch listeners
        A1.setOnTouchListener(new answerTouchListener());
        A2.setOnTouchListener(new answerTouchListener());
        A3.setOnTouchListener(new answerTouchListener());
        A4.setOnTouchListener(new answerTouchListener());

        //set drag listener for target
        LocationBox.setOnDragListener(new locationDragListener());

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
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    private class locationDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View dropView, DragEvent event) {

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being released over a target view
                    View view = (View) event.getLocalState();

                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
//                    view.setEnabled(false);

                    //view dragged item is being released on
                    TextView dropLocation = (TextView) dropView;

                    //view being dragged and released
                    TextView released = (TextView) view;

                    //update the text in the target view to reflect the data being released
                    dropLocation.setText(released.getText());

                    //make it bold to highlight the fact that an item has been released
                    dropLocation.setTypeface(Typeface.DEFAULT_BOLD);

                    //if an item has already been released here, there will be a tag
                    Object tag = dropLocation.getTag();

                    Log.d("ET", "" + tag);


                    //if there is already an item here, set it back visible in its original place
                    if(tag != null) {
                          //the tag is the view id already released here
                          int existingID = (Integer)tag;

                          //set the original view visible again
                          findViewById(existingID).setVisibility(View.VISIBLE);
                    }

                    final String Answer2 = released.getText().toString();

                    //set the tag in the target view to the ID of the view being released
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
                    break;
                default:
                    break;
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

    @Override
    protected void onStop(){
        super.onStop();
        if(popupWindow != null){
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
