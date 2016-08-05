package com.ns.questionbank;

/**
 * Created by Sharma on 05/08/2016.
 */
public class QuestionBank
{
    private int questionNumber;
    private String questionText;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;

    //Default Constructor
    public  QuestionBank()
    {
    }
    //Constructor with Arguments
    public QuestionBank(int questionNumber , String questionText ,String optionOne , String optionTwo , String optionThree , String optionFour)
    {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
    }
    //Setter Methids
    public void setQuestionNumber(int questionNumber) { this.questionNumber=questionNumber;}
    public void setQuestionText(String questionText) {this.questionText=questionText;}
    public void setOptionOne(String optionOne) { this.optionOne = optionOne;}
    public void setOptionTwo(String optionTwo) { this.optionTwo = optionTwo;}
    public void setOptionThree(String optionThree) { this.optionThree = optionThree;}
    public void setOptionFour(String optionFour) { this.optionFour = optionFour;}
        //Getter Methods
    public int getQuestionNumber() { return questionNumber ;}
    public String getQuestiontext() {return questionText;}
    public String  getOptionOne() { return optionOne;}
    public String  getOptionTwo() { return optionTwo;}
    public String  getOptionThree() { return optionThree;}
    public String  getOptionFour() { return optionFour;}
}
