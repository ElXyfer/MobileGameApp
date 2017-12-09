package com.example.lincolnchawora.mygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LearnPage extends AppCompatActivity {

    TextView learnTxt;
    ImageButton learnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_page);

        learnTxt = (TextView)findViewById(R.id.learn_Title);
        learnTxt.setTextSize(40);

        learnBtn = (ImageButton)findViewById(R.id.imageButton);

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent page2Intent = new Intent(LearnPage.this, Question3.class);
                startActivity(page2Intent);
//                finish();
            }
        });

    }
}
