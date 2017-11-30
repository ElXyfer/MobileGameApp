package com.example.lincolnchawora.mygame;

import android.content.ClipData; //
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.graphics.Typeface; //
import android.util.Log;

public class Question2 extends AppCompatActivity {

    private TextView option1, option2, option3, option4, choice1;
    Button conBtn;

    PopupWindow popupWindow;
    LayoutInflater layoutInflater;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        //views to drag
        option1 = (TextView)findViewById(R.id.option_1);
        option2 = (TextView)findViewById(R.id.option_2);
        option3 = (TextView)findViewById(R.id.option_3);
        option4 = (TextView)findViewById(R.id.option_4);

        //views to drop onto
        choice1 = (TextView)findViewById(R.id.choice_1);

        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
        option4.setOnTouchListener(new ChoiceTouchListener());

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());

        conBtn = (Button)findViewById(R.id.button3);

        conBtn.setEnabled(false);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint2);
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDragAndDrop(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    private class ChoiceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a target view
                    View view = (View) event.getLocalState();

                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);

                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;

                    //view being dragged and dropped
                    TextView dropped = (TextView) view;

                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());

                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

                    //if an item has already been dropped here, there will be a tag
                    Object tag = dropTarget.getTag();
                    Log.i("My tag", "the tag is " + dropped.getId());

                    //if there is already an item here, set it back visible in its original place
                    if(tag != null) {
                          //the tag is the view id already dropped here
                          int existingID = (Integer)tag;

                          //set the original view visible again
                          findViewById(existingID).setVisibility(View.VISIBLE);
                    }



                    //set the tag in the target view to the ID of the view being dropped
                    dropTarget.setTag(dropped.getId());


                    if(dropTarget.getText().toString().length() > 2) {
                        conBtn.setEnabled(true);

                        //if(dropped.getText().equals()){ // use text from view

                        //}

//                        conBtn.setBackgroundColor(Color.rgb(115,230,0));
//                        conBtn.setTextColor(Color.WHITE);
//
//                        // initilialise layout inflater
//                        layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//
////                        correctSound.start();
//
//                        // new layout, pass success pop up
//                        ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.successpopup, null);
//
//                        // create pop up window, pass created layout (with, height)
//                        popupWindow = new PopupWindow(container, 991,200);
//
//                        // show pop up window,
//                        popupWindow.showAtLocation(constraintLayout, Gravity.NO_GRAVITY, 50, 1250);
//
//
//
//                        conBtn.setText("Continue");

                        conBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent testIntent1 = new Intent(Question2.this, MainActivity.class); // change this to next question
                                startActivity(testIntent1);
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
}
