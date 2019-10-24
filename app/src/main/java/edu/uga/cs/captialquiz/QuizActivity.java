package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuizActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private QuizFragmentCollectionAdapter adapter;
    private DatabaseHelper myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        new populateTableBackground().execute();
        viewPager = findViewById(R.id.pager);
        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);//view pager activity contains instances of the fragment
    }
    private class populateTableBackground extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            myDatabase = new DatabaseHelper(getApplicationContext()); //creates the database upon activity startup
            Resources res = getResources();
            InputStream inStream = res.openRawResource(R.raw.state_capitals1);
            CSVReader reader = new CSVReader( new InputStreamReader( inStream ) );
            try{
                String [] nextLine;
                while( ( nextLine = reader.readNext() ) != null ) {
                    System.out.println(nextLine[0] +" "+nextLine[1] +" "+nextLine[2] +" "+ nextLine[3]);
                    myDatabase.insertData(nextLine[0], nextLine[1], nextLine[2], nextLine[3]); //insert values into columns
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        }
    }
