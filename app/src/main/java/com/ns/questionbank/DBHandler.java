package com.ns.questionbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharma on 05/08/2016.
 */
public class DBHandler extends SQLiteOpenHelper

{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "biologyQuestions";
    private static final String TABLE_QUESTION_BANK = "questionbank";
    private static final String KEY_QUESTION_NUMBER = "question_number";
    private static final String KEY_CORRECT_ANSWER = "correct_answer";
    private static final String KEY_QUESTION_TEXT = "question_text";
    private static final String KEY_OPTION_ONE = "option_one";
    private static final String KEY_OPTION_TWO = "option_two";
    private static final String KEY_OPTION_THREE = "option_three";
    private static final String KEY_OPTION_FOUR = "option_four";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_BANK);
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
       // db1.execSQL("TRUNCATE TABLE " + TABLE_QUESTION_BANK);
    }

    public int getTotalRecords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_QUESTION_BANK,null);
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

        return questionbank;
    }

}
