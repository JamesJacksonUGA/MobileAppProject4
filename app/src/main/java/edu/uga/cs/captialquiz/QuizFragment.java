package edu.uga.cs.captialquiz;


import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String message = getArguments().getString("message");
        textView.setText(message + getStateName);

        //ADD A LIST ADAPTER OR SUM TO DISPLAY CHOICES


        return view;
    }
    /**This method handles picking random numbers for the quiz. use the returned array to map to the array of state objects.
    *create a new array containing 6 objects randomly selected from AOB, set radio text/question equal to info gotten from that
     * array[position]
     * */

    public void viewAll(){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        Cursor res = db.getQuizTableData();
        res.moveToPosition((int) (Math.random() * 50));
        getID = res.getInt(0);
        getStateName = res.getString(1);
        getCapital = res.getString(2);
        getCity1 = res.getString(3);
        getCity2 = res.getString(4);
    }
    public int[] randomizeQuiz(){
        int[] arr = new int[6];

        for (int i = 0; i < arr.length; i ++){
            arr[i] = (int)(Math.random()*((50-0)+1))+0;
        }

        return arr;
    }

}
