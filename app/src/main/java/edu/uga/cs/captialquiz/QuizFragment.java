package edu.uga.cs.captialquiz;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewAll();
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        textView = view.findViewById(R.id.fragText);
        button1 = view.findViewById(R.id.radioButton);
        button2 = view.findViewById(R.id.radioButton2);
        button3 = view.findViewById(R.id.radioButton3);
     //   String [] filterAnswer = {getCapital, getCity1,getCity2};
        String message = getArguments().getString("message");
        int pageCounter = getArguments().getInt("counter"); //help with counting page and identifying question number
        textView.setText("Question "+pageCounter +": "+message + getStateName +"?");
        button1.setText(getCapital);
        button2.setText(getCity1);
        button3.setText(getCity2);

        //ADD A LIST ADAPTER OR SUM TO DISPLAY CHOICES


        return view;
    }
    /**This method handles picking random numbers for the quiz. use the returned array to map to the array of state objects.
    *create a new array containing 6 objects randomly selected from AOB, set radio text/question equal to info gotten from that
     * array[position]
     * */

    public void viewAll(){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Cursor res = db.getQuizTableData(); //this is the cursor that reads the table
        res.moveToPosition((int) (Math.random() * 50));
        getID = res.getInt(0);//id
        getStateName = res.getString(1); //statename
        getCapital = res.getString(2); //capital
        getCity1 = res.getString(3);//second largest
        getCity2 = res.getString(4); //third largest
    }
    public int[] randomizeQuiz(){
        int[] arr = new int[6];

        for (int i = 0; i < arr.length; i ++){
            arr[i] = (int)(Math.random()*((50-0)+1))+0;
        }

        return arr;
    }

}
