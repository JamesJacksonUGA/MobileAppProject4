package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private QuizFragmentCollectionAdapter adapter;
    private DatabaseHelper myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        myDatabase = new DatabaseHelper(this);

        viewPager = findViewById(R.id.pager);
        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);//view pager activity contains instances of the fragment
    }
}
