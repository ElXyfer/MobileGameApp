package com.example.lincolnchawora.mygame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LearnPage extends AppCompatActivity {

    TextView learnTxt, q1Answer, q1popUpText, q2Answer, q2popUpText, q3Answer, q3popUpText;
    String Q1Ans, Q2Ans, Q3Ans;
    Boolean PopUpActive;
    int clickCounter, Q1iAns, Q2iAns, Q3iAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_page);

        learnTxt = (TextView) findViewById(R.id.learn_Title);
        q1Answer = (TextView) findViewById(R.id.Q1Answer);
        q1popUpText = (TextView) findViewById(R.id.Q1PopUp);
        q2Answer = (TextView) findViewById(R.id.Q2Answer);
        q2popUpText = (TextView) findViewById(R.id.Q2PopUp);
        q3Answer = (TextView) findViewById(R.id.Q3Answer);
        q3popUpText = (TextView) findViewById(R.id.Q3PopUp);

        q1Answer.setOnClickListener(new MyAnswerClickListener());
        q2Answer.setOnClickListener(new MyAnswerClickListener());
        q3Answer.setOnClickListener(new MyAnswerClickListener());

        PopUpActive = false;

        clickCounter = 0;

        Bundle extras = getIntent().getExtras();
        q1popUpText.setAlpha(0.0f);
        q2popUpText.setAlpha(0.0f);
        q3popUpText.setAlpha(0.0f);

        if (extras != null) {
            String Ans1 = extras.getString("Q1Answer");
            String Answer2 = extras.getString("Q2Answer");
            String Ans3 = extras.getString("Q3Answer");
            int iA1 = extras.getInt("Q3Int1");
            int iA2 = extras.getInt("Q3Int2");
            int iA3 = extras.getInt("Q3Int3");

            Q1Ans = Ans1.toLowerCase();
            Q2Ans = Answer2.toLowerCase();
            Q3Ans = Ans3;

            Q1iAns = iA1;
            Q2iAns = iA2;
            Q3iAns = iA3;

            Toast.makeText(LearnPage.this, "Click on the tiles", Toast.LENGTH_LONG).show();

        }

        if(Q1Ans.equals("petal")) {
            q1Answer.setBackgroundColor(0xB349d049);
//            q1Answer.setEnabled(false);
            q1popUpText.setText("Your answer: " + Q1Ans + "\n" + "Correct Answer: petal");
        } else {
            q1Answer.setBackgroundColor(0xB3ff5555);

            q1popUpText.setText("Your answer: " + Q1Ans + "\n" + "Correct Answer: petal");
        }

        if(Q2Ans.equals("sepal")){
            q2Answer.setBackgroundColor(0xB349d049);
//            q2Answer.setEnabled(false);
            q2popUpText.setText("Your answer: " + Q2Ans + "\n" + "Correct Answer: sepal");
        } else {
            q2Answer.setBackgroundColor(0xB3ff5555);
            q2popUpText.setText("Your answer: " + Q2Ans + "\n" + "Correct Answer: sepal");
        }

        if(Q3iAns == 3){
            q3Answer.setBackgroundColor(0xB349d049);
            q3popUpText.setText("Your answer: magnesium" +"\n" + "Correct Answer: magnesium");
        } else {
            q3Answer.setBackgroundColor(0xB3ff5555);
            q3popUpText.setText("Correct Answer: " + Q3Ans);
        }

    }

    private final class MyAnswerClickListener implements TextView.OnClickListener{

        @Override
        public void onClick(View txtView) {

            clickCounter ++;

            // set text views as clicked items
            TextView clickedView = (TextView) txtView;

            // set tags to be id
            clickedView.setTag(clickedView.getId());

            // get tagged object
            Object TxtTag = clickedView.getTag();

//            hgg

            if(TxtTag.equals(2131492959) && clickCounter == 1) {
                q1popUpText.setAlpha(1.0f);
                q3popUpText.setAlpha(0.0f);
                q2popUpText.setAlpha(0.0f);
                clickCounter = 0;
            } else if(TxtTag.equals(2131492960) && clickCounter == 1) {
                q2popUpText.setAlpha(1.0f);
                q1popUpText.setAlpha(0.0f);
                q3popUpText.setAlpha(0.0f);
                clickCounter = 0;
            } else if(TxtTag.equals(2131492964) && clickCounter == 1) {
                q3popUpText.setAlpha(1.0f);
                q2popUpText.setAlpha(0.0f);
                q1popUpText.setAlpha(0.0f);
                clickCounter = 0;
            }

        }
    }

}
