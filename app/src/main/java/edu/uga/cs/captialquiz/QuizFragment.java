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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
    public static final String DEBUG_TAG = "DATABASE_INFO";
    private QuizDatabaseHelper db;
    private QuizObjects quiz1 = new QuizObjects();
    private QuizObjects quiz2 = new QuizObjects();
    private QuizObjects quiz3 = new QuizObjects();
    private QuizObjects quiz4 = new QuizObjects();
    private QuizObjects quiz5 = new QuizObjects();
    private QuizObjects quiz6 = new QuizObjects();
    public QuizObjects[] listofQuizes = {quiz1, quiz2, quiz3, quiz4,quiz5, quiz6};
    private int getID;
    private String getStateName;
    private String getCapital;
    private String getCity1;
    private String getCity2;
    private TextView textView;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new QuizDatabaseHelper(getActivity());
        fillQuizQuestions();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //viewAll();
        //collectStates();
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        textView = view.findViewById(R.id.fragText);
        button1 = view.findViewById(R.id.radioButton);
        button2 = view.findViewById(R.id.radioButton2);
        button3 = view.findViewById(R.id.radioButton3);


        String message = getArguments().getString("message");
        int pageCounter = getArguments().getInt("counter"); //help with counting page and identifying question number
        if(pageCounter == 1){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[0].getStateName() +"?");
            button1.setText(listofQuizes[0].getStateCapital());
            button2.setText(listofQuizes[0].getSecondLargeCity());
            button3.setText(listofQuizes[0].getThirdLargeCity());
        }else if (pageCounter == 2){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[1].getStateName() +"?");
            button1.setText(listofQuizes[1].getStateCapital());
            button2.setText(listofQuizes[1].getSecondLargeCity());
            button3.setText(listofQuizes[1].getThirdLargeCity());
        }else if (pageCounter == 3){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[2].getStateName() +"?");
            button1.setText(listofQuizes[2].getStateCapital());
            button2.setText(listofQuizes[2].getSecondLargeCity());
            button3.setText(listofQuizes[2].getThirdLargeCity());
        }else if (pageCounter == 4){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[3].getStateName() +"?");
            button1.setText(listofQuizes[3].getStateCapital());
            button2.setText(listofQuizes[3].getSecondLargeCity());
            button3.setText(listofQuizes[3].getThirdLargeCity());
        }else if (pageCounter == 5){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[4].getStateName() +"?");
            button1.setText(listofQuizes[4].getStateCapital());
            button2.setText(listofQuizes[4].getSecondLargeCity());
            button3.setText(listofQuizes[4].getThirdLargeCity());
        }else if (pageCounter == 6){
            textView.setText("Question "+pageCounter +": "+message + listofQuizes[5].getStateName() +"?");
            button1.setText(listofQuizes[5].getStateCapital());
            button2.setText(listofQuizes[5].getSecondLargeCity());
            button3.setText(listofQuizes[5].getThirdLargeCity());
        }
//        State state = stateArray[pageCounter];
//        textView.setText("Question "+pageCounter +": "+message + quiz1.getStateName() +"?");
//        button1.setText(quiz1.getStateCapital());
//        button2.setText(quiz1.getSecondLargeCity());
//        button3.setText(quiz1.getThirdLargeCity());
        //ADD A LIST ADAPTER OR SUM TO DISPLAY CHOICES
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        db.getWritableDatabase();
        super.onResume();
        Log.d(DEBUG_TAG, "Database Resumed");
    }

//    @Override
//    public void onPause() {
//        db.close();
//        super.onPause();
//        Log.d(DEBUG_TAG, "Database Closed");
//
//    }

    public void fillQuizQuestions(){
        for(int i = 0; i < 6; i++){
            Cursor res = db.getQuizTableData();
            res.moveToPosition((int) (Math.random() * 51));
            listofQuizes[i].setId(res.getInt(0));
            listofQuizes[i].setStateName(res.getString(1));
            listofQuizes[i].setStateCapital(res.getString(2));
            listofQuizes[i].setSecondLargeCity(res.getString(3));
            listofQuizes[i].setThirdLargeCity(res.getString(4));
            System.out.println("OVER HERE: " +listofQuizes[i].toString());
        }
    }

    /**This method handles picking random numbers for the quiz. use the returned array to map to the array of state objects.
    *create a new array containing 6 objects randomly selected from AOB, set radio text/question equal to info gotten from that
     * array[position]
     * */

    public void viewAll(){
        Cursor res = db.getQuizTableData();
        res.moveToPosition((int) (Math.random() * 51));
//        getID = res.getInt(0);//id
//        getStateName = res.getString(1); //statename
//        getCapital = res.getString(2); //capital
//        getCity1 = res.getString(3);//second largest
//        getCity2 = res.getString(4); //third largest
    }



}
