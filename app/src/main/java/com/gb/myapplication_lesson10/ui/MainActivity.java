package com.gb.myapplication_lesson10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gb.myapplication_lesson10.R;
import com.gb.myapplication_lesson10.publisher.Publisher;
import com.gb.myapplication_lesson10.ui.main.AnimalGuideFragment;
import com.gb.myapplication_lesson10.ui.main.Navigation;

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