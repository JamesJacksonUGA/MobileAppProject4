package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private QuizDatabaseHelper myDatabase;
    private Button startBtn;
    private Button historyButton;
    public static final String DEBUG_TAG = "DATABASE_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new populateTableBackground().execute(); //populates database upon activity start
        mainFunction();
        TextView title = findViewById(R.id.titleTextView);
        TextView desc = findViewById(R.id.descTextView);


    }

    public void mainFunction(){
        startBtn = findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizActivityData.class);
                startActivity(intent);

            }
        });
        historyButton = findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDatabase.allQuizAnswers();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Error Nothing Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("SessionID: "+res.getInt(0) +"\n");
                    buffer.append("QUIZ1ID: "+res.getInt(1) +"\n");
                    buffer.append("QUIZ2ID: "+res.getInt(2) +"\n");
                    buffer.append("QUIZ3ID: "+res.getInt(3) +"\n");
                    buffer.append("QUIZ4ID: "+res.getInt(4) +"\n");
                    buffer.append("QUIZ5ID: "+res.getInt(5) +"\n");
                    buffer.append("QUIZ6ID: "+res.getInt(6) +"\n");
                    buffer.append("Date Completed: "+res.getString(7) +"\n");
                    buffer.append("Correct Answers: "+res.getInt(8) +"\n");
                }
                showHistory("Quiz History", buffer.toString());
            }
        });
    }
    public void showHistory(String title, String information){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(information);
        builder.show();
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
