package com.e.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.fight_corona.Adapters.AnswerAdapter;
import com.e.fight_corona.Adapters.QueryAdapter;
import com.e.fight_corona.models.Answer;
import com.e.fight_corona.models.People;
import com.e.fight_corona.models.Query;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Display_Q_A extends AppCompatActivity
{
    FirebaseUser fuser;
    DatabaseReference reference;
    EditText editText;
    TextView question_disp;
    Button submit,replay_btn;
    Intent intent;
    String questionId;
    String user;
    List<Answer> manswer;
    RecyclerView recyclerView;
    AnswerAdapter answerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__q_a);
        recyclerView=findViewById(R.id.recycler_view_display_Q_A);
        editText=findViewById(R.id.editbox);
        submit=findViewById(R.id.submit);
        replay_btn=findViewById(R.id.reply);
        intent=getIntent();
        questionId=intent.getStringExtra("questionId");
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        fuser= FirebaseAuth.getInstance().getCurrentUser();
        user=fuser.getUid();
        reference=FirebaseDatabase.getInstance().getReference("Doctors").child(user);
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
        question_disp=findViewById(R.id.question);
        reference=FirebaseDatabase.getInstance().getReference("Questions").child(questionId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Query query=dataSnapshot.getValue(Query.class);
                if(query!=null)
                question_disp.setText(query.getQuery());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        readAnswer();




    }
    public void replay(View v)
    {
        editText.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);



    }
    public void submit(View v)
    {
        String answer=editText.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();

        final HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("questionId",questionId);
        hashMap.put("answer",answer);
        hashMap.put("time", ServerValue.TIMESTAMP);
        hashMap.put("doctorId", user);


        DatabaseReference x =reference.child("Answers").push();
        hashMap.put("id", x.getKey());
        x.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(Display_Q_A.this,"your review is submitted ",Toast.LENGTH_SHORT).show();
                    editText.setVisibility(View.GONE);
                    submit.setVisibility(View.GONE);

                }
                else
                {
                    Toast.makeText(Display_Q_A.this,"Query failed to post",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void readAnswer()
    {
        manswer=new ArrayList<>();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Answers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                manswer.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Answer answer=snapshot.getValue(Answer.class);
                    assert answer!=null;
                    if(answer.getQuestionId().equals(questionId))
                    {
                        manswer.add(answer);
                        answerAdapter=new AnswerAdapter(Display_Q_A.this,manswer);
                        recyclerView.setAdapter(answerAdapter);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}

