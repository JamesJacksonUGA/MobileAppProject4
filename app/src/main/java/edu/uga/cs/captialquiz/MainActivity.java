package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private QuizDatabaseHelper myDatabase;
    public static final String DEBUG_TAG = "DATABASE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new populateTableBackground().execute(); //populates database upon activity start
        TextView title = findViewById(R.id.titleTextView);
        TextView desc = findViewById(R.id.descTextView);
        Button startBtn = findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivityData.class);
                startActivity(intent);

            }
        });
    }
    /**
     * Async function populates the quiz table upon activity start up
     */
    private class populateTableBackground extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myDatabase = new QuizDatabaseHelper(getApplicationContext()); //creates the database upon activity startup
            Log.d(DEBUG_TAG, "MY DATABASE IS "+myDatabase.getWritableDatabase());

        }
        @Override
        protected Void doInBackground(Void... voids) {
            try{
//                myDatabase.getWritableDatabase();
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
