package com.e.fight_corona.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.fight_corona.Display_Q_A;
import com.e.fight_corona.R;
import com.e.fight_corona.models.People;
import com.e.fight_corona.models.Query;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder>
{
    private Context mcontext;
    private List<Query> mQuery;
    DatabaseReference reference;

    public QueryAdapter(Context mcontext, List<Query> mQuery)
    {
        this.mcontext = mcontext;
        this.mQuery = mQuery;
    }

    @NonNull
    @Override
    public QueryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.query_item,parent,false);
        return new QueryAdapter.ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final QueryAdapter.ViewHolder holder, int position)
    {
        final Query query=mQuery.get(position);
        holder.showQuery.setText(query.getQuery());
        reference= FirebaseDatabase.getInstance().getReference("Users").child(query.getSender());
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                final People people=dataSnapshot.getValue(People.class);
                if(people==null)
                {
                    DatabaseReference reference2 =FirebaseDatabase.getInstance().getReference("Collaborators").child(query.getSender());
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {

                            People people2=dataSnapshot.getValue(People.class);
                            if(people2==null)
                            {
                                DatabaseReference reference3=FirebaseDatabase.getInstance().getReference("Volunteers").child(query.getSender());
                                reference3.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        People people4=dataSnapshot.getValue(People.class);
                                        holder.sender.setText(people4.getUsername());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else
                            holder.sender.setText(people2.getUsername());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    holder.sender.setText(people.getUsername());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
        if(query.isIsanswered())
        {
            holder.layoutcolor.setBackgroundColor(mcontext.getResources().getColor(R.color.darkgreen));
        }
        else {
            holder.layoutcolor.setBackgroundColor(mcontext.getResources().getColor(R.color.red));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mcontext, Display_Q_A.class);
                intent.putExtra("questionId",query.getId());
                mcontext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mQuery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView showQuery,sender;
        public LinearLayout layoutcolor;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            showQuery=itemView.findViewById(R.id.query);
            sender=itemView.findViewById(R.id.sender);
            layoutcolor=itemView.findViewById(R.id.layout_color);
        }
    }
}
