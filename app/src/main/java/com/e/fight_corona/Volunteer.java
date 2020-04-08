package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.e.fight_corona.Adapters.FoodAdapter;
import com.e.fight_corona.Adapters.QueryAdapter;
import com.e.fight_corona.Comparators.Querycomparator;
import com.e.fight_corona.models.Food;
import com.e.fight_corona.models.Query;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Volunteer extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Food> mfood;
    FoodAdapter foodAdapter;
    DatabaseReference reference;
    FirebaseUser fuser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mfood=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference("Food Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                mfood.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Food food=snapshot.getValue(Food.class);
                    assert  food!=null;
                    mfood.add(food);


                }
//                Collections.sort(mfood,new Querycomparator());
                foodAdapter=new FoodAdapter(Volunteer.this,mfood);
                recyclerView.setAdapter(foodAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
