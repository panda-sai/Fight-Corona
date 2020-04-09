package com.blogspot.vamsibhavani.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startup extends AppCompatActivity
{
    private Button register,login;
    FirebaseUser firebaseUser;
    ImageView imageView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        register=findViewById(R.id.Register);
        login=findViewById(R.id.login);
        linearLayout=findViewById(R.id.main_layout1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i =new Intent(startup.this,Registration.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i =new Intent(startup.this,Login.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Handler handler = new Handler();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        imageView=findViewById(R.id.loading_image);
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                // do something

                if(firebaseUser!=null)
                {

                    Intent intent = new Intent(startup.this, Home.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    imageView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                }

            }
        }, 1000);
    }
}
