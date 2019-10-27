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
    private QuizObjects[] listofQuizes = new QuizObjects[6];
    private int numberOfCorrectAnswers = 0;
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("answers", numberOfCorrectAnswers);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myDatabase = new QuizDatabaseHelper(getContext());
        Cursor res = myDatabase.getQuizTableData(); //use limit of 50 states
        for(int i = 0; i < listofQuizes.length; i++){
            res.moveToPosition((int) (Math.random() * 50));
            listofQuizes[i] = new QuizObjects(res.getInt(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4));
//            listofQuizes[i].setId(res.getInt(0));
//            listofQuizes[i].setStateName(res.getString(1));
//            listofQuizes[i].setStateCapital(res.getString(2));
//            listofQuizes[i].setSecondLargeCity(res.getString(3));
//            listofQuizes[i].setThirdLargeCity(res.getString(4));
            System.out.println("OVER HERE: " +listofQuizes[i].toString());
        }
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null ){
            numberOfCorrectAnswers = 0;
        }else {
            numberOfCorrectAnswers = savedInstanceState.getInt("answers", 0);
        }

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

        String message = getArguments().getString("message");
        int pageCounter = getArguments().getInt("counter"); //help with counting page and identifying question number
        numberOfCorrectAnswers = getArguments().getInt("counter"); //help with counting page and identifying question number

        if(pageCounter == 1){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[0].getStateName() +"?");
            button1.setText(listofQuizes[0].getThirdLargeCity());
            button2.setText(listofQuizes[0].getSecondLargeCity());
            button3.setText(listofQuizes[0].getStateCapital());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[0].getStateCapital())){
                        numberOfCorrectAnswers--;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (pageCounter == 2){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[1].getStateName() +"?");
            button1.setText(listofQuizes[1].getSecondLargeCity());
            button2.setText(listofQuizes[1].getStateCapital());
            button3.setText(listofQuizes[1].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[1].getStateCapital())){
                        numberOfCorrectAnswers--;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (pageCounter == 3){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[2].getStateName() +"?");
            button1.setText(listofQuizes[2].getStateCapital());
            button2.setText(listofQuizes[2].getSecondLargeCity());
            button3.setText(listofQuizes[2].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[2].getStateCapital())){
                        numberOfCorrectAnswers--;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (pageCounter == 4){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[3].getStateName() +"?");
            button1.setText(listofQuizes[3].getThirdLargeCity());
            button2.setText(listofQuizes[3].getStateCapital());
            button3.setText(listofQuizes[3].getSecondLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[3].getStateCapital())){
                        numberOfCorrectAnswers--;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if (pageCounter == 5){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[4].getStateName() +"?");
            button1.setText(listofQuizes[4].getStateCapital());
            button2.setText(listofQuizes[4].getSecondLargeCity());
            button3.setText(listofQuizes[4].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[4].getStateCapital())){
                        numberOfCorrectAnswers++;
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if (pageCounter == 6){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[5].getStateName() +"?");
            button1.setText(listofQuizes[5].getThirdLargeCity());
            button2.setText(listofQuizes[5].getSecondLargeCity());
            button3.setText(listofQuizes[5].getStateCapital());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[5].getStateCapital())){
                        numberOfCorrectAnswers--;
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                myDatabase.populateCompleteTable(listofQuizes[0].getStateName(),listofQuizes[1].getStateName(),listofQuizes[2].getStateName(),listofQuizes[3].getStateName(),listofQuizes[4].getStateName(),listofQuizes[5].getStateName(),formatter.format(date),numberOfCorrectAnswers);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listofQuizes = null; //empty array

        }
    }
}
