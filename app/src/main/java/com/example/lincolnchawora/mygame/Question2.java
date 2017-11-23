package com.example.lincolnchawora.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Question2 extends AppCompatActivity {

    Button dragButton;
    LinearLayout dropButton;
    TextView tv,sucess;
    int total, fail = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        dragButton = (Button)findViewById(R.id.one);
        dropButton = (LinearLayout) findViewById(R.id.topLinear);
    }
}
