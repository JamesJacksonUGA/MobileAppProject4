package edu.uga.cs.captialquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "USQuiz.db";

    public static final String CREATE_QUESTIONS_TABLE="";

    //---------------------------------------- QUIZ TABLE
    public static final String QUIZ_TABLE = "QUIZES";
    public static final String STATE_ID = "id";
    public static final String STATE_COLUMN_NAME = "State";
    public static final String STATE_COLUMN_CAPITAL = "Captial";
    public static final String STATE_COLUMN_CITY1 = "Second_City";
    public static final String STATE_COLUMN_CITY2 = "Third_City";
    //------------------------------------------

    public static final String CREATE_QUIZ_TABLE =
            "create table "+QUIZ_TABLE+" ("+STATE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +STATE_COLUMN_NAME+ " TEXT, "
            +STATE_COLUMN_CAPITAL+" TEXT, "
            +STATE_COLUMN_CITY1+ " TEXT, "
            +STATE_COLUMN_CITY2+ " TEXT "
            +")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); // checks that our database is created
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUIZ_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+QUIZ_TABLE);
        onCreate(sqLiteDatabase);

    }
}
