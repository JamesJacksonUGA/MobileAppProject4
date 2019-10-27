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
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private QuizDatabaseHelper myDatabase;
    private Button startBtn;
    private Button historyButton;
    private QuizObjects[] listofQuizes = new QuizObjects[6];
    static int numAnswers = 0;
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
        Set<Integer> set = new HashSet<>(6);
        set.clear();
        for(int i = 0; i < 7; i++){
            int num =(int)( Math.random()*50);
            set.add(num);
        }

        final Object [] arr = set.toArray();

        startBtn = findViewById(R.id.startButton);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAnswers = 0;
                myDatabase = new QuizDatabaseHelper(getApplicationContext());
                Cursor res = myDatabase.getQuizTableData(); //use limit of 50 states
                for(int i = 0; i < listofQuizes.length; i++) {
                    res.moveToPosition((int) arr[i]);
                    listofQuizes[i] = new QuizObjects(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4));
                    System.out.println("OVER HERE: " + listofQuizes[i].toString());
                }
                Intent intent = new Intent(getApplicationContext(), QuizActivityData.class);
                intent.putExtra("quiz1", new String[]{
                        listofQuizes[0].getStateName(), listofQuizes[0].getStateCapital(), listofQuizes[0].getSecondLargeCity(), listofQuizes[0].getThirdLargeCity()
                });
                intent.putExtra("quiz2", new String[]{
                        listofQuizes[1].getStateName(), listofQuizes[1].getStateCapital(), listofQuizes[1].getSecondLargeCity(), listofQuizes[1].getThirdLargeCity()});
                intent.putExtra("quiz3", new String[]{
                        listofQuizes[2].getStateName(), listofQuizes[2].getStateCapital(), listofQuizes[2].getSecondLargeCity(), listofQuizes[2].getThirdLargeCity()});
                intent.putExtra("quiz4", new String[]{
                        listofQuizes[3].getStateName(), listofQuizes[3].getStateCapital(), listofQuizes[3].getSecondLargeCity(), listofQuizes[3].getThirdLargeCity()});
                intent.putExtra("quiz5", new String[]{
                        listofQuizes[4].getStateName(), listofQuizes[4].getStateCapital(), listofQuizes[4].getSecondLargeCity(), listofQuizes[4].getThirdLargeCity()});
                intent.putExtra("quiz6", new String[]{
                        listofQuizes[5].getStateName(), listofQuizes[5].getStateCapital(), listofQuizes[5].getSecondLargeCity(), listofQuizes[5].getThirdLargeCity()});
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
                    buffer.append("Quiz Session: "+res.getInt(0) +"\n");
                    buffer.append("Question 1: What is the capital of "+res.getString(1) +"?\n");
                    buffer.append("Question 2: What is the capital of "+res.getString(2) +"?\n");
                    buffer.append("Question 3: What is the capital of "+res.getString(3) +"?\n");
                    buffer.append("Question 4: What is the capital of "+res.getString(4) +"?\n");
                    buffer.append("Question 5: What is the capital of "+res.getString(5) +"?\n");
                    buffer.append("Question 6: What is the capital of "+res.getString(6) +"?\n");
                    buffer.append("Date Completed: "+res.getString(7) +"\n");
                    buffer.append("Correct Answers: "+res.getInt(8) +"\n");
                    buffer.append("\n");
                    buffer.append("\n");
                }
                showHistory("Quiz History", buffer.toString());
            }
        });
    }

    /**
     * This function shows all history of completed quizes
     * @param title
     * @param information
     */
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
