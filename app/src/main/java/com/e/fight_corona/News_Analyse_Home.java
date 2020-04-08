package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.e.fight_corona.Adapters.NewsAdapter;
import com.e.fight_corona.Adapters.QueryAdapter;
import com.e.fight_corona.Comparators.Newscomparator;
import com.e.fight_corona.models.News;
import com.e.fight_corona.models.People;
import com.e.fight_corona.models.Query;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class News_Analyse_Home extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<News> mNews;
    NewsAdapter newsAdapter;
    CircleImageView post_news;
    FirebaseUser fuser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__analyse__home);
        post_news=findViewById(R.id.post_news);
        recyclerView=findViewById(R.id.recycler_view_diplay_news);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Collaborators").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener()
        {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                People people=dataSnapshot.getValue(People.class);
                if(people==null)
                {
                    post_news.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mNews=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("News");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                mNews.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    News news=snapshot.getValue(News.class);
                    assert  news!=null;
                    mNews.add(news);
                    Collections.sort(mNews,new Newscomparator());
                    newsAdapter=new NewsAdapter(News_Analyse_Home.this,mNews);
                    recyclerView.setAdapter(newsAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
    public void post_news(View v)
    {
        Intent i=new Intent(News_Analyse_Home.this,Post_news.class);
        startActivity(i);

    }
}
