package edu.uga.cs.captialquiz;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
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
    //------------------------------------------

    public static final String CREATE_QUESTIONS_TABLE =
            "CREATE TABLE "+QUIZ_QUESTION_TABLE+" (" +STATE_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +STATE_COLUMN_NAME+ " TEXT, "
            +STATE_COLUMN_CAPITAL+" TEXT, "
            +STATE_COLUMN_CITY1+ " TEXT, "
            +STATE_COLUMN_CITY2+ " TEXT "
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DATABASE_OPERATIONS", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_QUESTIONS_TABLE);
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
    public Integer deleteTable(String id){
        return db.delete(QUIZ_QUESTION_TABLE, "ID = ?", new String[] {id});
    }


    /**
     * Want to iterate through quiz table with a limit of 50 rows
     * @return
     */
    public Cursor getQuizTableData(){
        Cursor res = db.rawQuery("SELECT * FROM "+QUIZ_QUESTION_TABLE+" LIMIT 50", null);
//        Cursor res = db.rawQuery("SELECT * FROM "+QUIZ_QUESTION_TABLE, null);
        return res;
    }
    public Cursor allQuizTableData(){
        Cursor res = db.rawQuery("SELECT * FROM "+QUIZ_QUESTION_TABLE, null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+QUIZ_QUESTION_TABLE);
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
