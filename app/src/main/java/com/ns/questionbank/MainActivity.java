package com.ns.questionbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        Log.d("Insert: ", "Inserting ..");
        db.addQuestionBank(new QuestionBank( 1 , "Q What is your Name" , "A Neelesh","B XXXXX","C ZZZZZ","D YYYYYY"));
        Log.d("Reading","Reading all Questions");
        List<QuestionBank> questionBanks = db.getAllQuestion();
        for (QuestionBank questionBank : questionBanks)
        {
            String log = "Number"+ questionBank.getQuestionNumber()+ questionBank.getQuestiontext()
                            +questionBank.getOptionOne()
                            +questionBank.getOptionTwo()
                            +questionBank.getOptionThree()
                            +questionBank.getOptionFour();
            Log.d("Question", log);
        }

    }
}
