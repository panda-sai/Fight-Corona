package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.fight_corona.models.News;
import com.e.fight_corona.models.People;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
public class Display_News_Answers extends AppCompatActivity
{
    FirebaseUser fuser;
    DatabaseReference reference,reference_news;
    RadioGroup radioGroup;
    RadioButton radiotrue,radiofalse,radionotsure;
    TextView news_display ,display_answer ,answer2;
    Button submit,replay_btn;
    Intent intent;
    String newsId;
    String user;
    News news;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__news__answers);
        radioGroup=findViewById(R.id.radio_group);
        radiotrue=findViewById(R.id.radio_true);
        radiofalse=findViewById(R.id.radio_false);
        radionotsure=findViewById(R.id.radio_notsure);
        submit=findViewById(R.id.submit);
        replay_btn=findViewById(R.id.reply);
        news_display=findViewById(R.id.news);
        display_answer=findViewById(R.id.answer);
        answer2=findViewById(R.id.answer2);
        intent=getIntent();
        newsId=intent.getStringExtra("newsId");



        fuser= FirebaseAuth.getInstance().getCurrentUser();
        user=fuser.getUid();
        reference=FirebaseDatabase.getInstance().getReference("Collaborators").child(user);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                People people=dataSnapshot.getValue(People.class);
                if(people!=null)
                {
                    replay_btn.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        reference_news= FirebaseDatabase.getInstance().getReference("News").child(newsId);
        reference_news.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                news=dataSnapshot.getValue(News.class);
                news_display.setText(news.getNews());
                readAnswer();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void replay(View v)
    {
        replay_btn.setVisibility(View.GONE);
        if(news.getAnswer()==null|| news.getAnswer().contentEquals("Not Sure"))
        {
            radioGroup.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);

        }
        else
        {
            Toast.makeText(Display_News_Answers.this,"Already this got a proper answer",Toast.LENGTH_SHORT).show();
            replay_btn.setVisibility(View.VISIBLE);
        }

    }
    public void submit(View v)
    {

        final String answer;
        if(radiotrue.isChecked())
        {
            answer="True";
        }
        else if(radionotsure.isChecked())
        {
            answer="Not Sure";
        }
        else
        {
            answer="False";
        }
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("isanswered",true);
        hashMap.put("answer",answer);
        hashMap.put("answered_collaborators",user);
        reference_news.updateChildren(hashMap);
        radioGroup.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);

        Toast.makeText(Display_News_Answers.this,"Your response successfully recorded",Toast.LENGTH_SHORT).show();
    }

    public void readAnswer()
    {


        if(news.isIsanswered())
        {
            display_answer.setVisibility(View.VISIBLE);
            answer2.setVisibility(View.VISIBLE);
            DatabaseReference reference_coll= FirebaseDatabase.getInstance().getReference("Collaborators").child(news.getAnswered_collaborators());
            reference_coll.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    People people=dataSnapshot.getValue(People.class);
                    display_answer.setText(news.getAnswer());
                    answer2.setText(people.getUsername());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            display_answer.setVisibility(View.VISIBLE);
            display_answer.setText("Not yet answered");
        }


    }
}
