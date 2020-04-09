package com.blogspot.vamsibhavani.hackathonproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.fight_corona.R;
import com.e.fight_corona.models.Answer;
import com.e.fight_corona.models.People;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder>
{

    private Context mcontext;
    private List<Answer> mAnswer;
    DatabaseReference reference;

    public AnswerAdapter(Context mcontext, List<Answer> mAnswer)
    {
        this.mcontext = mcontext;
        this.mAnswer = mAnswer;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.answer_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position)
    {
        final Answer answer=mAnswer.get(position);
        holder.showAnswer.setText(answer.getAnswer());
        reference= FirebaseDatabase.getInstance().getReference("Doctors").child(answer.getDoctorId());
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                People people=dataSnapshot.getValue(People.class);
                holder.sender_doctor.setText(people.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mAnswer.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView showAnswer,sender_doctor;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            showAnswer=itemView.findViewById(R.id.answer);
            sender_doctor=itemView.findViewById(R.id.sender_doctor);
        }
    }
}
