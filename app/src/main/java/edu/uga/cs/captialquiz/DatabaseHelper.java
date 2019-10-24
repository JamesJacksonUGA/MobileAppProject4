package edu.uga.cs.captialquiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
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
    ContentValues contentValues;
    SQLiteDatabase db;
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
        Log.d("DATABASE_OPERATIONS", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
        Log.d("DATABASE_OPERATIONS", "Table created");
    }

    public void insertData(String stateName, String stateCapital, String city1, String city2){
        db = this.getWritableDatabase(); // checks that our database is created
        contentValues = new ContentValues();
        contentValues.put(STATE_COLUMN_NAME, stateName);
        contentValues.put(STATE_COLUMN_CAPITAL, stateCapital);
        contentValues.put(STATE_COLUMN_CITY1, city1);
        contentValues.put(STATE_COLUMN_CITY2, city2);
        db.insert(QUIZ_QUESTION_TABLE,null,contentValues); //the specific table we want to populate is the quiz_question_table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+QUIZ_QUESTION_TABLE);
        onCreate(sqLiteDatabase);
        Log.d( "DATABASE_OPERATIONS", "Table  upgraded" );
    }

//    public ArrayList<HashMap<String, String>> getAllStates(){
//        ArrayList<HashMap<String, String>> stateList;
//        stateList = new ArrayList<HashMap<String, String>>();
//        String selectQuery = "SELECT * FROM QUIZ_TABLE";
//        SQLiteDatabase database = this.getWritableDatabase();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        if(cursor.moveToFirst()){
//            do{
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put(STATE_ID, cursor.getString(0));
//                map.put(STATE_COLUMN_NAME, cursor.getString(1));
//                map.put(STATE_COLUMN_CAPITAL, cursor.getString(2));
//                map.put(STATE_COLUMN_CITY1, cursor.getString(3));
//                map.put(STATE_COLUMN_CITY2, cursor.getString(4));
//                stateList.add(map);
//            }while (cursor.moveToNext());
//        }
//        return stateList;
//    }
}
