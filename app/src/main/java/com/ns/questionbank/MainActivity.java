package com.ns.questionbank;

import android.content.DialogInterface;
import android.content.Intent;
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
    public int[] correct_answer = new int[29];
    public int MAXIMUM_QUESTIONS,final_score ;
    private UserAnswer userAnswer = new UserAnswer();
    DBHandler db1 = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        MAXIMUM_QUESTIONS = db.getTotalRecords();
        Button button_next = (Button) findViewById(R.id.button_next);
        Button button_previous = (Button) findViewById(R.id.button_previous);
        Button button_submit = (Button) findViewById(R.id.button_submit);
        Button button_save = (Button) findViewById(R.id.button_save);
        RadioGroup radiogroup = (RadioGroup)findViewById(R.id.radioGroup_question);

        button_next.setOnClickListener(this);
        button_previous.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        button_save.setOnClickListener(this);

        db.deleteAll();

        Log.d("Insert: ", "Inserting ..");
        db.addQuestionBank(new QuestionBank(1,2,
                "Q1)The Outer most and inner most wall layers of microsporangium in an anther are respectively:",
                "A) Endothecium and Tapetum                  ",
                "B) Epidermis and Endodermis                 ",
                "C) Epidermis and Middle Layer               ",
                "D) Epidermis and Tapetum                    "));
        db.addQuestionBank(new QuestionBank(2,2,
                "Q2)During micosporogenesis meiosis occurs in :                                               ",
                "A) Endothecium                        ",
                "B) Microspore mother cell             ",
                "C) Microspore tetrads                 ",
                "D) Pollen grains                      "));
        Log.d("Reading", "Reading all Questions");
        for (int j = 1 ; j<=30;j++) {
            db.defaultInsertYourAnswer(new UserAnswer(j,0));
        }
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


        // Commenting logic for getting all questions
        List<QuestionBank> questionBanks = db.getAllQuestion();
        for (QuestionBank questionBank : questionBanks) {
            String log = "Number" + questionBank.getQuestionNumber()
                    +questionBank.getCorrectAnswer()
                    + questionBank.getQuestiontext()
                    + questionBank.getOptionOne()
                    + questionBank.getOptionTwo()
                    + questionBank.getOptionThree()
                    + questionBank.getOptionFour();
            Log.d("Question", log);
        }


    }

    @Override
    public void onClick(View v) {

        //submit_intent.putExtra("Ten");
        QuestionBank questionBank = new QuestionBank();

        switch (v.getId()) {
                case R.id.button_next:
                    Log.d("Clicked Next: ", "Clicked Next");
                   // Toast toast = Toast.makeText(getApplicationContext(), "Clicked Next", Toast.LENGTH_SHORT);
                   // toast.show();


                      //  your_answer[COUNT] = SetAnswer(COUNT);
                        //  db1.addUpdateYourAnswer(new UserAnswer(COUNT,your_answer[COUNT]));
                        COUNT = COUNT +1;
                        if (COUNT >= MAXIMUM_QUESTIONS) COUNT = MAXIMUM_QUESTIONS;
                        DisplayMCQ(COUNT);



                    break;
                case R.id.button_previous:
                    COUNT = COUNT - 1;
                    if (COUNT <=1) COUNT = 1;
                    DisplayMCQ(COUNT);
                    break;
                case R.id.button_submit:
                    Intent submit_intent =  new Intent(this,SubmitActivity.class);

                    startActivity(submit_intent);
                    break;
                case R.id.button_save:
                    your_answer[COUNT] = SetAnswer(COUNT);
                    db1.UpdateYourAnswer(new UserAnswer(COUNT,your_answer[COUNT]));
                    break;

        }
    }

    public void DisplayMCQ(int count)
    {
        DBHandler db = new DBHandler(this);
        QuestionBank questionbank = db.getSingleQuestion(count);
        RadioGroup radioGroup_question = (RadioGroup)findViewById(R.id.radioGroup_question);
        int j = 0;
        j = db1.GetUserAnswer(COUNT) ;
        Toast toast = Toast.makeText(getApplicationContext(), "Value of j:" + String.valueOf(j), Toast.LENGTH_SHORT);
        toast.show();
        if (j ==0) {
            radioGroup_question.clearCheck();
        TextView textView_question_text = (TextView) findViewById(R.id.textView_question_number);
        RadioButton radioButton_option1 = (RadioButton) findViewById(R.id.radioButton_option1);
        RadioButton radioButton_option2 = (RadioButton) findViewById(R.id.radioButton_option2);
        RadioButton radioButton_option3 = (RadioButton) findViewById(R.id.radioButton_option3);
        RadioButton radioButton_option4 = (RadioButton) findViewById(R.id.radioButton_option4);
        textView_question_text.setText(questionbank.getQuestiontext());
        radioButton_option1.setText(questionbank.getOptionOne());
        radioButton_option2.setText(questionbank.getOptionTwo());
        radioButton_option3.setText(questionbank.getOptionThree());
        radioButton_option4.setText(questionbank.getOptionFour());
    }
        else {
            TextView textView_question_text = (TextView) findViewById(R.id.textView_question_number);
            RadioButton radioButton_option1 = (RadioButton) findViewById(R.id.radioButton_option1);
            RadioButton radioButton_option2 = (RadioButton) findViewById(R.id.radioButton_option2);
            RadioButton radioButton_option3 = (RadioButton) findViewById(R.id.radioButton_option3);
            RadioButton radioButton_option4 = (RadioButton) findViewById(R.id.radioButton_option4);
            textView_question_text.setText(questionbank.getQuestiontext());
            radioButton_option1.setText(questionbank.getOptionOne());
            radioButton_option2.setText(questionbank.getOptionTwo());
            radioButton_option3.setText(questionbank.getOptionThree());
            radioButton_option4.setText(questionbank.getOptionFour());
            if (j==1) radioButton_option1.setChecked(true);
            if (j==2) radioButton_option2.setChecked(true);
            if (j==3) radioButton_option3.setChecked(true);
            if (j==4) radioButton_option4.setChecked(true);

        }

    }

    public int SetAnswer(int question_number)
    {
       RadioGroup radiogroup_question = (RadioGroup)findViewById(R.id.radioGroup_question);
       int selected_option = radiogroup_question.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected_option);
        if (radioButton == (RadioButton)findViewById(R.id.radioButton_option1)) your_answer[question_number] = 1;
        if (radioButton == (RadioButton)findViewById(R.id.radioButton_option2)) your_answer[question_number] = 2;
        if (radioButton == (RadioButton)findViewById(R.id.radioButton_option3)) your_answer[question_number] = 3;
        if (radioButton == (RadioButton)findViewById(R.id.radioButton_option4)) your_answer[question_number] = 4;


        return your_answer[question_number];
    }


}

