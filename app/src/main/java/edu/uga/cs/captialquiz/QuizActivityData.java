package edu.uga.cs.captialquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.os.Bundle;

public class QuizActivityData extends AppCompatActivity {
    private ViewPager viewPager;
    private QuizFragmentCollectionAdapter adapter;
    String[] quiz1, quiz2, quiz3, quiz4, quiz5, quiz6;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quiz1 = getIntent().getStringArrayExtra("quiz1");
        quiz2 = getIntent().getStringArrayExtra("quiz2");
        quiz3 = getIntent().getStringArrayExtra("quiz3");
        quiz4 = getIntent().getStringArrayExtra("quiz4");
        quiz5 = getIntent().getStringArrayExtra("quiz5");
        quiz6 = getIntent().getStringArrayExtra("quiz6");
        setContentView(R.layout.activity_quiz);
        viewPager = findViewById(R.id.pager);
        adapter = new QuizFragmentCollectionAdapter(getSupportFragmentManager(), quiz1, quiz2, quiz3, quiz4, quiz5, quiz6);
        viewPager.setAdapter(adapter);//view pager activity contains instances of the fragment
    }

}
