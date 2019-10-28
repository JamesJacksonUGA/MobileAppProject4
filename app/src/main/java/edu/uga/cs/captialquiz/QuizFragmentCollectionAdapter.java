package edu.uga.cs.captialquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class QuizFragmentCollectionAdapter extends FragmentPagerAdapter {
    private String[] quiz1, quiz2, quiz3, quiz4, quiz5, quiz6;

    /**
     * User defined constructor that contains the state information to be viewed as quiz questions for the fragment
     * @param fm
     * @param quiz1 quiz1 information
     * @param quiz2 quiz2 information
     * @param quiz3 quiz3 information
     * @param quiz4 quiz4 information
     * @param quiz5 quiz5 information
     * @param quiz6 quiz6 information
     */
    public QuizFragmentCollectionAdapter(FragmentManager fm, String[] quiz1, String[] quiz2, String[] quiz3, String[] quiz4, String[] quiz5, String[] quiz6){
        super (fm);
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.quiz5 = quiz5;
        this.quiz6 = quiz6;
    }

    @Override
    public Fragment getItem(int position){ //fragment object created with quiz questions
        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putStringArray("quiz1", quiz1);
        bundle.putStringArray("quiz2", quiz2);
        bundle.putStringArray("quiz3", quiz3);
        bundle.putStringArray("quiz4", quiz4);
        bundle.putStringArray("quiz5", quiz5);
        bundle.putStringArray("quiz6", quiz6);
        bundle.putInt("counter", position);
        //IF POSITION == X SET MESSAGE TO QUIZ QUESTION OR WHATEVER
        bundle.putString("message","What is the capital of: ");
        quizFragment.setArguments(bundle);
        return quizFragment;
    }
    @Override
    public int getCount(){
        return 7;
    }
}
