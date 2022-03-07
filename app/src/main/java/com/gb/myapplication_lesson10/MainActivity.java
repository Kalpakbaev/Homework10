package com.gb.myapplication_lesson10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,AnimalGuideFragment.newInstance()).commit();
        }
    }
}