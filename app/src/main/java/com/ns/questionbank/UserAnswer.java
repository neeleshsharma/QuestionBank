package com.ns.questionbank;

/**
 * Created by Sharma on 09/08/2016.
 */
public class UserAnswer {

    private int questionNumber;
    private int userAnswer;

    public UserAnswer()
    {

    }
    public UserAnswer(int questionNumber,int userAnswer)
    {
        this.questionNumber = questionNumber;
        this.userAnswer = userAnswer;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
    public int getUserAnswer() {
        return userAnswer;
    }

    public void setYourAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}

