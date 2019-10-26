package edu.uga.cs.captialquiz;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
    public static final String DEBUG_TAG = "DATABASE_INFO";
    private QuizDatabaseHelper myDatabase;
    private QuizObjects quiz1 = new QuizObjects();
    private QuizObjects quiz2 = new QuizObjects();
    private QuizObjects quiz3 = new QuizObjects();
    private QuizObjects quiz4 = new QuizObjects();
    private QuizObjects quiz5 = new QuizObjects();
    private QuizObjects quiz6 = new QuizObjects();
    private QuizObjects[] listofQuizes = {quiz1, quiz2, quiz3, quiz4,quiz5, quiz6};
    private int numberOfCorrectAnswers = 0;
    private TextView textView;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioGroup radioGroup;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new QuizDatabaseHelper(getContext());
        Cursor res = myDatabase.getQuizTableData(); //use limit of 50 states
        for(int i = 0; i < listofQuizes.length; i++){
            res.moveToPosition((int) (Math.random() * 51));
            listofQuizes[i].setId(res.getInt(0));
            listofQuizes[i].setStateName(res.getString(1));
            listofQuizes[i].setStateCapital(res.getString(2));
            listofQuizes[i].setSecondLargeCity(res.getString(3));
            listofQuizes[i].setThirdLargeCity(res.getString(4));
            System.out.println("OVER HERE: " +listofQuizes[i].toString());
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

        String message = getArguments().getString("message");
        int pageCounter = getArguments().getInt("counter"); //help with counting page and identifying question number


        if(pageCounter == 1){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[0].getStateName() +"?");
            button1.setText(listofQuizes[0].getStateCapital());
            button2.setText(listofQuizes[0].getSecondLargeCity());
            button3.setText(listofQuizes[0].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[0].getStateCapital())){
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if (pageCounter == 2){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[1].getStateName() +"?");
            button1.setText(listofQuizes[1].getStateCapital());
            button2.setText(listofQuizes[1].getSecondLargeCity());
            button3.setText(listofQuizes[1].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[1].getStateCapital())){
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
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else if (pageCounter == 4){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[3].getStateName() +"?");
            button1.setText(listofQuizes[3].getStateCapital());
            button2.setText(listofQuizes[3].getSecondLargeCity());
            button3.setText(listofQuizes[3].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[3].getStateCapital())){
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
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if (pageCounter == 6){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[5].getStateName() +"?");
            button1.setText(listofQuizes[5].getStateCapital());
            button2.setText(listofQuizes[5].getSecondLargeCity());
            button3.setText(listofQuizes[5].getThirdLargeCity());
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                    RadioButton checked = (RadioButton) view.findViewById(checkedId);
                    if(checked.getText().equals(listofQuizes[5].getStateCapital())){
                        Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
                    }

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

    /**This method handles picking random numbers for the quiz. use the returned array to map to the array of state objects.
     *create a new array containing 6 objects randomly selected from AOB, set radio text/question equal to info gotten from that
     * array[position]
     * */
//    public void fillQuizQuestions(){
//        for(int i = 0; i < listofQuizes.length; i++){
//            Cursor res = myDatabase.getQuizTableData();
//            res.moveToPosition((int) (Math.random() * 51));
//            listofQuizes[i].setId(res.getInt(0));
//            listofQuizes[i].setStateName(res.getString(1));
//            listofQuizes[i].setStateCapital(res.getString(2));
//            listofQuizes[i].setSecondLargeCity(res.getString(3));
//            listofQuizes[i].setThirdLargeCity(res.getString(4));
//            System.out.println("OVER HERE: " +listofQuizes[i].toString());
//        }
//    }




}
