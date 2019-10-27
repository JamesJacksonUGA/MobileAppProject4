package edu.uga.cs.captialquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizDatabaseHelper extends SQLiteOpenHelper {
    ContentValues contentValues;
    SQLiteDatabase db = this.getWritableDatabase(); // checks that our database is created
    public static final String DATABASE_NAME = "USQUIZ.db";
    public static final int DATABASE_VERSION = 1;
    //---------------------------------------- QUIZ TABLE
    public static final String QUIZ_QUESTION_TABLE = "QUIZES";
    public static final String STATE_ID = "ID";
    public static final String STATE_COLUMN_NAME = "STATE";
    public static final String STATE_COLUMN_CAPITAL = "CAPITAL";
    public static final String STATE_COLUMN_CITY1 = "SECOND_CITY";
    public static final String STATE_COLUMN_CITY2 = "THIRD_CITY";

    public static final String CREATE_QUESTIONS_TABLE =
            "CREATE TABLE "+QUIZ_QUESTION_TABLE+" (" +STATE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +STATE_COLUMN_NAME+ " TEXT, "
                    +STATE_COLUMN_CAPITAL+" TEXT, "
                    +STATE_COLUMN_CITY1+ " TEXT, "
                    +STATE_COLUMN_CITY2+ " TEXT "
                    + ")";
    //------------------------------------------

    //------------------------------------------- Answered Quiz  table
    public static final String ANSWERED_QUIZ_QUESTION_TABLE = "COMPLETE_QUIZ";
    public static final String QUIZ_ID = "ID";
    public static final String QUIZ1 = "QUIZ1";
    public static final String QUIZ2 = "QUIZ2";
    public static final String QUIZ3 = "QUIZ3";
    public static final String QUIZ4 = "QUIZ4";
    public static final String QUIZ5 = "QUIZ5";
    public static final String QUIZ6 = "QUIZ6";
    public static final String QUIZ_DATE = "QUIZ_DATE";
    public static final String ANSWERS_CORRECT = "CORRECT_ANSWERS";

    public static final String CREATE_ANSWERS_TABLE =
            "CREATE TABLE "+ANSWERED_QUIZ_QUESTION_TABLE+" (" +QUIZ_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +QUIZ1+ " TEXT, "
                    +QUIZ2+" TEXT, "
                    +QUIZ3+ " TEXT, "
                    +QUIZ4+ " TEXT, "
                    +QUIZ5+ " TEXT, "
                    +QUIZ6+ " TEXT, "
                    +QUIZ_DATE+ " TEXT, "
                    +ANSWERS_CORRECT+ " INTEGER "
                    +")";

    //---------------------------------------------------


    public QuizDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE_OPERATIONS", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
            sqLiteDatabase.execSQL(CREATE_ANSWERS_TABLE);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * This function populates the quiz table
     * @param stateName
     * @param stateCapital
     * @param city1
     * @param city2
     * @return
     */
    public boolean populateQuizTable(String stateName, String stateCapital, String city1, String city2){
        contentValues = new ContentValues();
        contentValues.put(STATE_COLUMN_NAME, stateName);
        contentValues.put(STATE_COLUMN_CAPITAL, stateCapital);
        contentValues.put(STATE_COLUMN_CITY1, city1);
        contentValues.put(STATE_COLUMN_CITY2, city2);
        db.insert(QUIZ_QUESTION_TABLE,null,contentValues); //the specific table we want to populate is the quiz_question_table
        return true;
    }

    public void populateCompleteTable(String quiz1ID, String quiz2ID, String quiz3ID, String quiz4ID, String quiz5ID, String quiz6ID, String quizDate, int correctAnwers){
        contentValues = new ContentValues();
        contentValues.put(QUIZ1, quiz1ID);
        contentValues.put(QUIZ2, quiz2ID);
        contentValues.put(QUIZ3, quiz3ID);
        contentValues.put(QUIZ4, quiz4ID);
        contentValues.put(QUIZ5, quiz5ID);
        contentValues.put(QUIZ6, quiz6ID);
        contentValues.put(QUIZ_DATE, quizDate);
        contentValues.put(ANSWERS_CORRECT, correctAnwers);
        db.insert(ANSWERED_QUIZ_QUESTION_TABLE,null,contentValues); //the specific table we want to populate is the quiz_question_table
       // return true;
    }
    public Integer deleteTable(String id){
        return db.delete(QUIZ_QUESTION_TABLE, "ID = ?", new String[] {id});
    }

    /**
     * Want to iterate through quiz table with a limit of 50 rows
     * @return
     */
    public Cursor getQuizTableData(){
        Cursor res = db.rawQuery("SELECT * FROM "+QUIZ_QUESTION_TABLE+" LIMIT 50", null);
        return res;
    }
    public Cursor allQuizAnswers(){
        Cursor res = db.rawQuery("SELECT * FROM "+ANSWERED_QUIZ_QUESTION_TABLE, null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+QUIZ_QUESTION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+QUIZ_QUESTION_TABLE);
        onCreate(sqLiteDatabase);
        Log.d( "DATABASE_OPERATIONS", "Table  upgraded" );
    }
}
