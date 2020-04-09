package com.blogspot.vamsibhavani.hackathonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
{
    private EditText emailTextView, passwordTextView;
    private Button Btn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    private TextView forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.email_login);
        passwordTextView = findViewById(R.id.password_login);
        Btn = findViewById(R.id.login_page);
        progressbar = findViewById(R.id.progressBar);
        forgot_password = findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,forgot_password.class));
            }
        });

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                progressbar.setVisibility(View.VISIBLE);
                String email, password;
                email = emailTextView.getText().toString();
                password = passwordTextView.getText().toString();
                if (TextUtils.isEmpty(email))
                {
                    Log.i("Login", "onClick: email checked :unsucess");
                    progressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(v.getContext(),"Please enter email!!", Toast.LENGTH_LONG).show();

                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Log.i("Login", "onClick: password checked :unsucess");
                    progressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(v.getContext(),"Please enter password!!",Toast.LENGTH_LONG).show();

                    return;
                }
                try
                {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                progressbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(v.getContext(),"Login successful!!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(v.getContext(),"Login failed!!",Toast.LENGTH_LONG).show();
                                // hide the progress bar
                                progressbar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
                catch (Exception e)
                {
                    Toast.makeText(v.getContext(),"Login failed!!"+e,Toast.LENGTH_LONG).show();
                }


            }
        });


    }
    public void link(View v)
    {
        Intent intent = new Intent(Login.this, Registration.class);
        startActivity(intent);

    }

}
