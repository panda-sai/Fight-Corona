package com.e.fight_corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity
{
    CardView medicalSupport,news_analyser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        medicalSupport=findViewById(R.id.notes_card);
        medicalSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Home.this,Medic_home.class);
                startActivity(i);

            }
        });
        news_analyser=findViewById(R.id.news_analyser);
        news_analyser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i= new Intent(Home.this,News_Analyse_Home.class);
                startActivity(i);
            }
        });

    }
}
