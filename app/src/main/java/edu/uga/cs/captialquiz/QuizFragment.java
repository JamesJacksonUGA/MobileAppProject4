package edu.uga.cs.captialquiz;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.opencsv.CSVReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
    public static final String DEBUG_TAG = "DATABASE_INFO";
    private QuizDatabaseHelper myDatabase;
    private int numberOfCorrectAnswers;
    String[] quiz1;
    String[] quiz2;
    String[] quiz3;
    String[] quiz4;
    String[] quiz5;
    String[] quiz6;
    private TextView textView;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private Button complete;
    private RadioGroup radioGroup;

    public QuizFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new QuizDatabaseHelper(getContext());
       // MainActivity.numAnswers =0;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) { //what do you want to save
        super.onSaveInstanceState(outState);
        outState.putInt("CORRECT_ANSWERS",numberOfCorrectAnswers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        textView = view.findViewById(R.id.fragText);
        button1 = view.findViewById(R.id.radioButton);
        button2 = view.findViewById(R.id.radioButton2);
        button3 = view.findViewById(R.id.radioButton3);
        radioGroup = view.findViewById(R.id.radioGroup3);
        complete = view.findViewById(R.id.complete);
        complete.setVisibility(View.GONE);
        numberOfCorrectAnswers = 0;


        quiz1 = getArguments().getStringArray("quiz1");
        quiz2 = getArguments().getStringArray("quiz2");
        quiz3 = getArguments().getStringArray("quiz3");
        quiz4 = getArguments().getStringArray("quiz4");
        quiz5 = getArguments().getStringArray("quiz5");
        quiz6 = getArguments().getStringArray("quiz6");
        String message = getArguments().getString("message");
        int pageCounter = getArguments().getInt("counter"); //help with counting page and identifying question number

        if(pageCounter == 1){
            textView.setText("Question "+pageCounter +": "+message + quiz1[0] +"?");
            button3.setText(quiz1[1]);
            button1.setText(quiz1[2]);
            button2.setText(quiz1[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz1[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (pageCounter == 2){
            textView.setText("Question "+pageCounter +": "+message + quiz2[0] +"?");
            button2.setText(quiz2[1]);
            button1.setText(quiz2[2]);
            button3.setText(quiz2[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz2[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (pageCounter == 3){
            textView.setText("Question "+pageCounter +": "+message + quiz3[0] +"?");
            button1.setText(quiz3[1]);
            button2.setText(quiz3[2]);
            button3.setText(quiz3[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz3[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (pageCounter == 4){
            textView.setText("Question "+pageCounter +": "+message + quiz4[0] +"?");
            button2.setText(quiz4[1]);
            button1.setText(quiz4[2]);
            button3.setText(quiz4[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz4[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if (pageCounter == 5){
            textView.setText("Question "+pageCounter +": "+message + quiz5[0] +"?");
            button2.setText(quiz5[1]);
            button3.setText(quiz5[2]);
            button1.setText(quiz5[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz5[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if (pageCounter == 6){
            textView.setText("Question "+pageCounter +": "+message + quiz6[0] +"?");
            button1.setText(quiz6[1]);
            button2.setText(quiz6[2]);
            button3.setText(quiz6[3]);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(quiz6[1])){
                        MainActivity.numAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {
            textView.setVisibility(View.GONE);
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            complete.setVisibility(View.VISIBLE);
            complete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new populateQuizSessionToDatabase().execute();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
        //ADD A LIST ADAPTER OR SUM TO DISPLAY CHOICES
        return view;
    }


    @Override
    public void onResume() {
        myDatabase.getWritableDatabase(); //get my database back
        super.onResume();

        Log.d(DEBUG_TAG, "Database Resumed "+myDatabase.getWritableDatabase());
    }

    @Override
    public void onPause() {
        myDatabase.close(); //close my database
        super.onPause();
        Log.d(DEBUG_TAG, "Database Closed "+myDatabase.getWritableDatabase());
    }
    @Override
    public void onStart() {
        super.onStart();

        Log.d(DEBUG_TAG, "Database Started "+myDatabase.getWritableDatabase());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "Database Destroyed "+myDatabase.getWritableDatabase());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(DEBUG_TAG, "Database Stopped "+myDatabase.getWritableDatabase());
    }

    /**
     * Async task to add quiz session into database
     */
    private class populateQuizSessionToDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                Date date = new Date();
                myDatabase.populateCompleteTable(quiz1[0],quiz2[0],quiz3[0],quiz4[0],quiz5[0],quiz6[0],formatter.format(date),MainActivity.numAnswers);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
