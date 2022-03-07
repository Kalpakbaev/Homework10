package com.gb.myapplication_lesson10.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gb.myapplication_lesson10.R;

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