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
import android.widget.ProgressBar;

import com.e.fight_corona.Adapters.QueryAdapter;
import com.e.fight_corona.Comparators.Querycomparator;
import com.e.fight_corona.models.People;
import com.e.fight_corona.models.Query;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Medic_home extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Query> mquery;
    QueryAdapter queryAdapter;
    DatabaseReference reference;
    CircleImageView floatingActionButton;
    FirebaseUser fuser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_home);
        recyclerView=findViewById(R.id.recycler_view);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        progressBar=findViewById(R.id.progressbar);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Doctors").child(fuser.getUid());
        progressBar.setVisibility(View.VISIBLE);
        reference.addValueEventListener(new ValueEventListener()
        {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                People people=dataSnapshot.getValue(People.class);
                if(people==null)
                {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mquery=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("Questions");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                mquery.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Query query=snapshot.getValue(Query.class);
                    assert  query!=null;
                    mquery.add(query);
                    Log.i("Query",""+query);


                }
                Collections.sort(mquery,new Querycomparator());
                progressBar.setVisibility(View.INVISIBLE);
                queryAdapter=new QueryAdapter(Medic_home.this,mquery);
                recyclerView.setAdapter(queryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });




    }
    public void Call_to_post_question(View v)
    {
        Intent i=new Intent(Medic_home.this,Post_Question.class);
        startActivity(i);

    }
}
