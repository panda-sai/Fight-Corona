package com.blogspot.vamsibhavani.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<questionItem> questionList = new ArrayList<>();

        questionList.add(new questionItem("question1","author1"));
        questionList.add(new questionItem("question2","author2"));
        questionList.add(new questionItem("question3","author3"));

    }
}
