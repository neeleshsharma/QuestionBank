package com.ns.questionbank;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
public int COUNT = 1;
    public int[] your_answer = new int[29];
    public int MAXIMUM_QUESTIONS ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        MAXIMUM_QUESTIONS = db.getTotalRecords();
        Button button_next = (Button) findViewById(R.id.button_next);
        Button button_previous = (Button) findViewById(R.id.button_previous);
        Button button_submit = (Button) findViewById(R.id.button_submit);
        RadioGroup radiogroup = (RadioGroup)findViewById(R.id.radioGroup_question);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



            }
        });
        button_next.setOnClickListener(this);
        button_previous.setOnClickListener(this);
        button_submit.setOnClickListener(this);

        db.deleteAll();

        Log.d("Insert: ", "Inserting ..");
        db.addQuestionBank(new QuestionBank(1,2,
                "Q1) The Outer most and inner most wall layers of microsporangium in an anther are respectively:",
                "A) Endothecium and Tapetum",
                "B) Epidermis and Endodermis",
                "C) Epidermis and Middle Layer",
                "D) Epidermis and Tapetum"));
        db.addQuestionBank(new QuestionBank(2,2,
                "Q2) During micosporogenesis meiosis occurs in :                                               ",
                "A) Endothecium                        ",
                "B) Microspore mother cell             ",
                "C) Microspore tetrads                 ",
                "D) Pollen grains                      "));
        Log.d("Reading", "Reading all Questions");
        QuestionBank questionbank = db.getSingleQuestion(COUNT);
        TextView textView_question_text = (TextView) findViewById(R.id.textView_question_number);

        RadioButton radioButton_option1 = (RadioButton)findViewById(R.id.radioButton_option1) ;
        RadioButton radioButton_option2 = (RadioButton)findViewById(R.id.radioButton_option2) ;
        RadioButton radioButton_option3 = (RadioButton)findViewById(R.id.radioButton_option3) ;
        RadioButton radioButton_option4 = (RadioButton)findViewById(R.id.radioButton_option4) ;
        textView_question_text.setText(questionbank.getQuestiontext());
        radioButton_option1.setText(questionbank.getOptionOne());
        radioButton_option2.setText(questionbank.getOptionTwo());
        radioButton_option3.setText(questionbank.getOptionThree());
        radioButton_option4.setText(questionbank.getOptionFour());


        /* Commenting logic for getting all questions
        List<QuestionBank> questionBanks = db.getAllQuestion();
        for (QuestionBank questionBank : questionBanks) {
            String log = "Number" + questionBank.getQuestionNumber() + questionBank.getQuestiontext()
                    + questionBank.getOptionOne()
                    + questionBank.getOptionTwo()
                    + questionBank.getOptionThree()
                    + questionBank.getOptionFour();
            Log.d("Question", log);
        } */

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
                case R.id.button_next:
                    Log.d("Clicked Next: ", "Clicked Next");
                    Toast toast = Toast.makeText(getApplicationContext(), "Clicked Next", Toast.LENGTH_SHORT);
                    toast.show();
                    COUNT = COUNT +1;
                    if (COUNT >= MAXIMUM_QUESTIONS) COUNT = MAXIMUM_QUESTIONS;

                    DisplayMCQ(COUNT);
                    break;
                case R.id.button_previous:
                    Log.d("Clicked Previous: ", "Clicked Previous");
                    COUNT = COUNT - 1;
                    if (COUNT <=1) COUNT = 1;
                    DisplayMCQ(COUNT);
                    break;
                case R.id.button_submit:
                    Log.d("Clicked Submit: ", "Clicked Submit");
                    break;
        }
    }

    public void DisplayMCQ(int count)
    {
        DBHandler db = new DBHandler(this);
        QuestionBank questionbank = db.getSingleQuestion(count);
        RadioGroup radioGroup_question = (RadioGroup)findViewById(R.id.radioGroup_question);
        radioGroup_question.clearCheck();
        TextView textView_question_text = (TextView) findViewById(R.id.textView_question_number);
        RadioButton radioButton_option1 = (RadioButton)findViewById(R.id.radioButton_option1) ;
        RadioButton radioButton_option2 = (RadioButton)findViewById(R.id.radioButton_option2) ;
        RadioButton radioButton_option3 = (RadioButton)findViewById(R.id.radioButton_option3) ;
        RadioButton radioButton_option4 = (RadioButton)findViewById(R.id.radioButton_option4) ;
        textView_question_text.setText(questionbank.getQuestiontext());
        radioButton_option1.setText(questionbank.getOptionOne());
        radioButton_option2.setText(questionbank.getOptionTwo());
        radioButton_option3.setText(questionbank.getOptionThree());
        radioButton_option4.setText(questionbank.getOptionFour());

    }
}

