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

import com.e.fight_corona.Display_News_Answers;
import com.e.fight_corona.Display_Q_A;
import com.e.fight_corona.R;
import com.e.fight_corona.models.News;
import com.e.fight_corona.models.People;
import com.e.fight_corona.models.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    private Context mcontext;
    private List<News> mNews;
    DatabaseReference reference;

    public NewsAdapter(Context mcontext, List<News> mNews)
    {
        this.mcontext = mcontext;
        this.mNews = mNews;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.query_item,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final NewsAdapter.ViewHolder holder, int position)
    {
        final News news=mNews.get(position);
        holder.showNews.setText(news.getNews());
        reference= FirebaseDatabase.getInstance().getReference("Users").child(news.getSender());
        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                People people=dataSnapshot.getValue(People.class);
                if(people==null)
                {
                    DatabaseReference reference2 =FirebaseDatabase.getInstance().getReference("Doctors").child(news.getSender());
                    reference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            People people2=dataSnapshot.getValue(People.class);
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
        if(news.isIsanswered())
        {
            holder.layoutcolor.setBackgroundColor(R.color.green);
        }
        else {
            holder.layoutcolor.setBackgroundColor(5);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mcontext, Display_News_Answers.class);
                intent.putExtra("newsId",news.getId());
                mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount()
    {
        return mNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView showNews,sender;
        public LinearLayout layoutcolor;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            showNews=itemView.findViewById(R.id.query);
            sender=itemView.findViewById(R.id.sender);
            layoutcolor=itemView.findViewById(R.id.layout_color);
        }

    }
}
