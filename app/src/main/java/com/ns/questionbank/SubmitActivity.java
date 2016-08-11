package com.ns.questionbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int score = 0;
        setContentView(R.layout.activity_submit);
        DBHandler db = new DBHandler(this);
        UserAnswer userAnswer = new UserAnswer();
        QuestionBank questionBank = new QuestionBank();
        //if(UserAnswer.getAnswer(counter) == Questionbank.correctanser(counter) ) score = score +1
        for (int i = 1 ; i<=2 ; i++)
        {
            if (db.isCorrectAnswer(i) == 1) score = score+1;
        }

        TextView textView_score = (TextView)findViewById(R.id.textView_score);
        TextView textView_label_score = (TextView)findViewById(R.id.textView_label_score);
        textView_label_score.setText("Your Score is ");
        textView_score.setText(String.valueOf(score));


    }
}
