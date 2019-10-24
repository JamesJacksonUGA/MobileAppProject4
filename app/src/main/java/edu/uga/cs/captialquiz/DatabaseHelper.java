package edu.uga.cs.captialquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "USQUIZ.db";
    //---------------------------------------- QUIZ TABLE
    public static final String QUIZ_QUESTION_TABLE = "QUIZES";
    public static final String STATE_ID = "id";
    public static final String STATE_COLUMN_NAME = "State";
    public static final String STATE_COLUMN_CAPITAL = "Captial";
    public static final String STATE_COLUMN_CITY1 = "Second_City";
    public static final String STATE_COLUMN_CITY2 = "Third_City";
    //------------------------------------------

    public static final String CREATE_QUESTIONS_TABLE =
            "CREATE TABLE "+QUIZ_QUESTION_TABLE+" (" +STATE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +STATE_COLUMN_NAME+ " TEXT, "
            +STATE_COLUMN_CAPITAL+" TEXT, "
            +STATE_COLUMN_CITY1+ " TEXT, "
            +STATE_COLUMN_CITY2+ " TEXT "
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); // checks that our database is created
        Log.d("DATABASE_OPERATIONS", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
        Log.d("DATABASE_OPERATIONS", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+QUIZ_QUESTION_TABLE);
        onCreate(sqLiteDatabase);
        Log.d( "DATABASE_OPERATIONS", "Table  upgraded" );

    }
}
