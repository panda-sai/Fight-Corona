package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.fight_corona.models.People;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity
{
    private EditText username,password,confirmPassword,email;
    private Button register;
    private RadioGroup role;
    private RadioButton doctor,people,collaborators;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private ProgressBar progressbar;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirm_password);
        email=findViewById(R.id.email);
        register=findViewById(R.id.register);
        doctor=findViewById(R.id.radio_doctor);
        people=findViewById(R.id.radio_public);
        progressbar = findViewById(R.id.progressbar);
        checkBox=findViewById(R.id.checkbox);
        collaborators=findViewById(R.id.radio_collaborators);

        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressbar.setVisibility(View.VISIBLE);
                final String emailValue, passwordValue,usernameValue,confirmPasswordValue;
                final boolean isDoctor,isCollaborators;
                usernameValue=username.getText().toString();
                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();
                confirmPasswordValue = confirmPassword.getText().toString();
                isDoctor=doctor.isChecked();
                isCollaborators=collaborators.isChecked();
                if (TextUtils.isEmpty(usernameValue))
                {
                    Toast.makeText(getApplicationContext(),"Please enter Username!!",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (TextUtils.isEmpty(emailValue)||!(emailValue.contains("@")&&(emailValue.contains("."))))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid email!!",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (TextUtils.isEmpty(passwordValue))
                {
                    Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (passwordValue.length()<8)
                {
                    Toast.makeText(getApplicationContext(),"Password length should be greater than 8 characters",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(!passwordValue.equals(confirmPasswordValue))
                {
                    Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }
                if(!checkBox.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"Accept the terms and conditions",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.INVISIBLE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailValue,passwordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            FirebaseUser firebaseUser=mAuth.getCurrentUser();
                            String userid=firebaseUser.getUid();
                            HashMap<String, String> hashMap= new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",usernameValue);
                            if(isDoctor)
                            {
                                databaseReference= FirebaseDatabase.getInstance().getReference("Doctors").child(userid);
                            }
                            if(isCollaborators)
                            {
                                databaseReference= FirebaseDatabase.getInstance().getReference("Collaborators").child(userid);
                            }
                            else
                            {
                                databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            }
                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    Toast.makeText(getApplicationContext(), "Registration successful!",Toast.LENGTH_LONG).show();
                                    progressbar.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(Registration.this, Home.class);
                                    startActivity(intent);
                                }
                            });
                        }
                        else
                        {
                            // Registration failed
                            Toast.makeText(getApplicationContext(),"Registration failed!!"+ " Please try again later", Toast.LENGTH_LONG).show();
                            // hide the progress bar
                            progressbar.setVisibility(View.INVISIBLE);
                        }

                    }
                });


            }
        });





    }
    public void link2(View v)
    {
        Intent intent = new Intent(Registration.this, Login.class);
        startActivity(intent);

    }


}
