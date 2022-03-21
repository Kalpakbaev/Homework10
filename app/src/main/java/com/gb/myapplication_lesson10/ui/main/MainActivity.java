package com.gb.myapplication_lesson10.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.publisher.Publisher;

public class MainActivity extends AppCompatActivity {

   private Publisher publisher;
    private Navigation navigation;

    public Navigation getNavigation() {
        return navigation;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          publisher = new Publisher();
          navigation = new Navigation( getSupportFragmentManager());
        if (savedInstanceState==null){
            navigation.replaceFragment(AnimalGuideFragment.newInstance(),false);
        }
    }
}