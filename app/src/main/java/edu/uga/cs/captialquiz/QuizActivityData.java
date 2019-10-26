package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizActivityData extends AppCompatActivity {
    private ViewPager viewPager;
    private QuizFragmentCollectionAdapter adapter;
    private QuizDatabaseHelper myDatabase;

    public QuizActivityData(){}
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        myDatabase = new QuizDatabaseHelper(this); //creates the database upon activity startup
        new populateTableBackground().execute();
        viewPager = findViewById(R.id.pager);
        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);//view pager activity contains instances of the fragment
    }
    /**
     * Async function populates the quiz table upon activity start up
     */
    private class populateTableBackground extends AsyncTask<Void, Void, Void>{
//        @Override
//        protected void onPreExecute() { //clears all table values before populating
//            super.onPreExecute();
//            myDatabase = new QuizDatabaseHelper(getApplicationContext()); //creates the database upon activity startup
//            Cursor res = myDatabase.allQuizTableData(); //this is the cursor that reads the table
//            while(res.moveToNext()) {
//                Integer deleteRow = myDatabase.deleteTable(res.getString(0));
//                if(deleteRow > 0){
//                    Log.d("DATABASE_OPERATIONS", "row deleted");
//                }
//            }
//        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Resources res = getResources();
                InputStream inStream = res.openRawResource(R.raw.state_capitals1);
                CSVReader reader = new CSVReader( new InputStreamReader( inStream ) );
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {
                   // System.out.println(nextLine[0] +" "+nextLine[1] +" "+nextLine[2] +" "+ nextLine[3]);
                    boolean inserted = myDatabase.populateQuizTable(nextLine[0], nextLine[1], nextLine[2], nextLine[3]); //insert values into columns
                    if(inserted == true){
                        Log.d("DATABASE_OPERATIONS", "row created");
                    }else{
                        Log.d("DATABASE_OPERATIONS", "row not created");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        }


    }
