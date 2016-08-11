package com.ns.questionbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharma on 05/08/2016.
 */
public class DBHandler extends SQLiteOpenHelper

{
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "biologyQuestions";
    private static final String TABLE_QUESTION_BANK = "questionbank";
    private static final String KEY_QUESTION_NUMBER = "question_number";
    private static final String KEY_CORRECT_ANSWER = "correct_answer";
    private static final String KEY_QUESTION_TEXT = "question_text";
    private static final String KEY_OPTION_ONE = "option_one";
    private static final String KEY_OPTION_TWO = "option_two";
    private static final String KEY_OPTION_THREE = "option_three";
    private static final String KEY_OPTION_FOUR = "option_four";

    private static final String TABLE_USER_ANSWER = "useranswer";
    private static final String KEY_USER_QUESTION_NUMBER = "question_number";
    private static final String KEY_USER_ANSWER = "user_answer";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTION_BANK_TABLE = "CREATE TABLE " + TABLE_QUESTION_BANK + "("
                + KEY_QUESTION_NUMBER + " INTEGER PRIMARY KEY ,"
                + KEY_CORRECT_ANSWER + " INTEGER, "
                + KEY_QUESTION_TEXT + " TEXT, "
                + KEY_OPTION_ONE + " TEXT, "
                + KEY_OPTION_TWO + " TEXT, "
                + KEY_OPTION_THREE + " TEXT, "
                + KEY_OPTION_FOUR + " TEXT "
                + ")";
        db.execSQL(CREATE_QUESTION_BANK_TABLE);

        String CREATE_USER_ANSWER_TABLE = " CREATE TABLE "+ TABLE_USER_ANSWER+ "("
                +KEY_USER_QUESTION_NUMBER + " INTEGER PRIMARY KEY , "
                +KEY_USER_ANSWER + " INTEGER"
                + ")";
        try
        {
            db.execSQL(CREATE_USER_ANSWER_TABLE);
        }catch (SQLException e) { Log.d("errorininsert",null,null);e.printStackTrace();};
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_BANK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ANSWER);
// Creating tables again
        onCreate(db);
    }

    //Adding new Question
    public void addQuestionBank(QuestionBank questionbank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION_NUMBER, questionbank.getQuestionNumber());
        values.put(KEY_CORRECT_ANSWER,questionbank.getCorrectAnswer());
        values.put(KEY_QUESTION_TEXT, questionbank.getQuestiontext());
        values.put(KEY_OPTION_ONE, questionbank.getOptionOne());
        values.put(KEY_OPTION_TWO, questionbank.getOptionTwo());
        values.put(KEY_OPTION_THREE, questionbank.getOptionThree());
        values.put(KEY_OPTION_FOUR, questionbank.getOptionFour());

        db.insert(TABLE_QUESTION_BANK, null, values);
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUESTION_BANK,null,null);
        db.delete(TABLE_USER_ANSWER,null,null);
       // db1.execSQL("TRUNCATE TABLE " + TABLE_QUESTION_BANK);
    }

    public int getTotalRecords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_QUESTION_BANK,null);
       // c.close();
        return c.getCount();

    }

    public int getAnswerCount()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_USER_ANSWER,null);
        c.close();
        return c.getCount();

    }

    public List<QuestionBank> getAllQuestion() {
        List<QuestionBank> questionBankList = new ArrayList<QuestionBank>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION_BANK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                QuestionBank questionBank = new QuestionBank();
                questionBank.setQuestionNumber(Integer.parseInt(cursor.getString(0)));
                questionBank.setCorrectAnswer(Integer.parseInt(cursor.getString(1)));
                questionBank.setQuestionText(cursor.getString(2));
                questionBank.setOptionOne(cursor.getString(3));
                questionBank.setOptionTwo(cursor.getString(4));
                questionBank.setOptionThree(cursor.getString(5));
                questionBank.setOptionFour(cursor.getString(6));
                questionBankList.add(questionBank);

            } while (cursor.moveToNext());
        }
        //cursor.close();
        db.close();
        return questionBankList;


    }

    public QuestionBank getSingleQuestion(int questionNumber)
    {
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION_BANK +" WHERE question_number = " +questionNumber;
       // QuestionBank questionbank = new QuestionBank();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor != null)
            cursor.moveToFirst();
        QuestionBank questionbank = new QuestionBank(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        //cursor.close();
        db.close();
        return questionbank;
    }
    public void defaultInsertYourAnswer(UserAnswer userAnswer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        values.put(KEY_USER_QUESTION_NUMBER, userAnswer.getQuestionNumber());
        values.put(KEY_USER_ANSWER,userAnswer.getUserAnswer());
        //values1.put(KEY_USER_ANSWER,userAnswer.getUserAnswer());
        db.insert(TABLE_USER_ANSWER, null, values);
        /*****************************
        Cursor cr = db.rawQuery("SELECT * FROM "+ TABLE_USER_ANSWER +" WHERE question_number = " + userAnswer.getQuestionNumber(),null);
        if (cr == null) db.insert(TABLE_USER_ANSWER, null, values);
        //else  db.update(TABLE_USER_ANSWER,values1,null);
        else db.execSQL("Update "+ TABLE_USER_ANSWER +" SET "+ KEY_USER_ANSWER+ " = "+userAnswer.getUserAnswer() +" WHERE "
        + KEY_QUESTION_NUMBER +" = "+ userAnswer.getQuestionNumber()
        );*************************/
        db.close();
    }
    public void UpdateYourAnswer(UserAnswer userAnswer)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String updateQuery ="UPDATE " + TABLE_USER_ANSWER+ " SET " + KEY_USER_ANSWER +" = "+userAnswer.getUserAnswer()
                +" WHERE " +KEY_QUESTION_NUMBER +" = "+ userAnswer.getQuestionNumber();
        Cursor c = db.rawQuery(updateQuery,null);
        c.moveToFirst();
        c.close();

    }

    public List<UserAnswer> getUserAnswer()
    {
        List<UserAnswer> userAnswerList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER_ANSWER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst())
        {
            do
            {
                  UserAnswer userAnswer = new UserAnswer();
                    userAnswer.setQuestionNumber(Integer.parseInt(cursor.getString(0)));
                    userAnswer.setYourAnswer(Integer.parseInt(cursor.getString(1)));
                }while (cursor.moveToNext());

        }
        //cursor.close();
        return userAnswerList;

    }

    public int isCorrectAnswer(int questionNumber)
    {
        int i=0;
        int j=0;

        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + KEY_CORRECT_ANSWER +" FROM " +TABLE_QUESTION_BANK
                +" WHERE question_number = "  +questionNumber;
        Cursor cursor = db.rawQuery(selectquery,null);

        if (cursor !=null) {
            if (cursor.moveToFirst()) i = cursor.getInt(0);
        }
        //i=Integer.parseInt(cursor.getString(0));

        String selectquery1 = "SELECT " + KEY_USER_ANSWER +" FROM " +TABLE_USER_ANSWER
                +" WHERE question_number = "  +questionNumber;
        Cursor cursor1 = db.rawQuery(selectquery1,null);
       // j=Integer.parseInt(cursor.getString(0));
        if (cursor !=null) {
            if (cursor1.moveToFirst()) j= cursor1.getInt(0);
        }
        if ( i == j) return 1 ;else return 0;
        //Select correct_answer from questionbank where question_number = questionNumber
    }

    public int GetUserAnswer(int questionNumber)
    {
        int i=0;
        int j=0;

        SQLiteDatabase db = this.getReadableDatabase();
        String selectquery = "SELECT " + KEY_USER_ANSWER +" FROM " +TABLE_USER_ANSWER
                +" WHERE question_number = "  +questionNumber;
        Cursor cursor = db.rawQuery(selectquery,null);
        //  i=(int)Integer.parseInt(cursor.getString(0));
        if (cursor !=null) {
            if (cursor.moveToFirst()) i = cursor.getInt(0);
        }
        return i;
    }

}
