package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class Post_news extends AppCompatActivity
{
    EditText textbox;
    Button post;
    FirebaseUser fuser;
    DatabaseReference reference;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);
        textbox=findViewById(R.id.post_textbox);
        post=findViewById(R.id.post);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        progressbar = findViewById(R.id.progressBar);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String msg=textbox.getText().toString();
                if(!msg.equals(""))
                {
                    sendNews(fuser.getUid(),msg);
                }
                else
                {
                    Toast.makeText(Post_news.this,"Enter something to send a message",Toast.LENGTH_SHORT).show();
                }
                textbox.setText("");

            }
        });
    }

    public void sendNews(String sender,String news)
    {
        progressbar.setVisibility(View.VISIBLE);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        final HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("news",news);
        hashMap.put("isanswered",false);
        hashMap.put("time", ServerValue.TIMESTAMP);


        DatabaseReference x =reference.child("News").push();
        hashMap.put("id", x.getKey());
        x.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    progressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Post_news.this,"News succesfully posted",Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Post_news.this,News_Analyse_Home.class);
                    startActivity(i);
                }
                else
                {
                    progressbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(Post_news.this,"News failed to post",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
