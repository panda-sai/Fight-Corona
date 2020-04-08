package com.e.fight_corona.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.fight_corona.Display_News_Answers;
import com.e.fight_corona.R;
import com.e.fight_corona.Volunteer;
import com.e.fight_corona.models.Food;
import com.e.fight_corona.models.News;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>
{
    private Context mcontext;
    private List<Food> mfood;

    public FoodAdapter(Context mcontext, List<Food> mfood)
    {
        this.mcontext = mcontext;
        this.mfood = mfood;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.food_item,parent,false);
        return new FoodAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final Food food=mfood.get(position);
        holder.name.setText(food.getName());
        holder.no.setText(food.getMobile_no());
        holder.food.setText("Food Quantity :"+food.getFood_capacity());
        holder.date.setText("Available Date :"+food.getDate());
        holder.time.setText("Available Time :"+food.getTime());

        holder.address.setText("Address :"+food.getLocation());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



                String uri="geo:0,0?q="+food.getLocation();
                Uri gmmIntentUri=Uri.parse(uri);
                Intent mapIntent=new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if(mapIntent.resolveActivity(mcontext.getPackageManager()) != null)
                {
                    mcontext.startActivity(mapIntent);
                }

            }
        });




    }

    @Override
    public int getItemCount() {
        return mfood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name,food,date,time,address,no;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            food=itemView.findViewById(R.id.food);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            address=itemView.findViewById(R.id.address);
            no=itemView.findViewById(R.id.no);

        }
    }
}
