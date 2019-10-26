package edu.uga.cs.captialquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class QuizFragmentCollectionAdapter extends FragmentPagerAdapter {
    public QuizFragmentCollectionAdapter(FragmentManager fm){
        super (fm);
    }

    @Override
    public Fragment getItem(int position){
        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();
        position = position+1;
        bundle.putInt("counter", position);
        //IF POSITION == X SET MESSAGE TO QUIZ QUESTION OR WHATEVER
        bundle.putString("message","What is the capital of: ");
        quizFragment.setArguments(bundle);

        return quizFragment;
    }

    @Override
    public int getCount(){
        return 6;
    }
}
