package com.ns.questionbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        TextView textView_score = (TextView)findViewById(R.id.textView_score);
        TextView textView_label_score = (TextView)findViewById(R.id.textView_label_score);
        textView_label_score.setText("This is Label");
        textView_score.setText("Testing");


    }
}
