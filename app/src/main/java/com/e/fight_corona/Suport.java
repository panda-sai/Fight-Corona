package com.e.fight_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Suport extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suport);
    }
    public void oc(View view) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        int id = rg.getCheckedRadioButtonId();
        switch (id) {
            case R.id.food:
                Intent intent = new Intent(this, foodactivity.class);
                startActivity(intent);
                break;

            case R.id.money:
                Intent intent1 = new Intent(this, moneyactivity.class);
                startActivity(intent1);
                break;
        }
    }
}
