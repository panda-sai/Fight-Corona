package com.e.fight_corona.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.e.fight_corona.Browser;
import com.e.fight_corona.Login;
import com.e.fight_corona.R;
import com.e.fight_corona.models.CoronoNews;
import com.e.fight_corona.models.News;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CoronoNewsAdapter extends RecyclerView.Adapter<CoronoNewsAdapter.ViewHolder>
{
    private Context mcontext;
    private List<CoronoNews> mNews;

    public CoronoNewsAdapter(Context mcontext, List<CoronoNews> mNews)
    {
        this.mcontext = mcontext;
        this.mNews = mNews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.news_item,parent,false);
        return new CoronoNewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final CoronoNews news=mNews.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());
        Glide.with(mcontext).load(news.getUrlToImage()).into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(mcontext, Browser.class);
                intent.putExtra("url",news.getUrl());
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
        public TextView title,description;
        public ImageView image;
        public CardView card;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            image=itemView.findViewById(R.id.news_image);
            card=itemView.findViewById(R.id.layout_news);
        }
    }
}
