package com.e.fight_corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.e.fight_corona.Adapters.CoronoNewsAdapter;
import com.e.fight_corona.Adapters.QueryAdapter;
import com.e.fight_corona.models.CoronoNews;
import com.e.fight_corona.models.Query;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class corono_news extends AppCompatActivity
{
    TextView textView;
    RecyclerView recyclerView;
    List<CoronoNews> mnews;
    CoronoNewsAdapter coronoNewsAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corono_news);
        recyclerView=findViewById(R.id.recycler_view);
        textView=findViewById(R.id.text2);
        progressBar=findViewById(R.id.progressbar);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        progressBar.setVisibility(View.VISIBLE);
        String myUrl = "https://newsapi.org/v2/everything?q=corona&apiKey=4b02ecab85ac4ca3a30e01c827a7bed8";

        HttpGetRequest getRequest = new HttpGetRequest();
        getRequest.execute(myUrl);



    }
    public class HttpGetRequest extends AsyncTask<String, Void, List<CoronoNews>>
    {

        @Override
        protected List<CoronoNews> doInBackground(String... strings)
        {
            mnews = new ArrayList<>();
            String stringUrl = strings[0];
            String result = null;
            String inputLine;
            try
            {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection =(HttpURLConnection)myUrl.openConnection();
                connection.setRequestMethod("GET");
                Log.i("background:","Task1 Completed");
                connection.connect();
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();
                JSONObject root =new JSONObject(result);
                JSONArray articles=root.getJSONArray("articles");
                for(int i=0;i<articles.length();i++)
                {
                    JSONObject object=articles.getJSONObject(i);
                    String author=object.getString("author");
                    String title=object.getString("title");
                    String description=object.getString("description");
                    String url=object.getString("url");
                    String urlToImage=object.getString("urlToImage");
                    CoronoNews coronoNews=new CoronoNews(author,title,description,url,urlToImage);
                    mnews.add(coronoNews);


                }



            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return mnews;
        }

        @Override
        protected void onPostExecute(List<CoronoNews> result)
        {
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            coronoNewsAdapter=new CoronoNewsAdapter(corono_news.this,mnews);
            recyclerView.setAdapter(coronoNewsAdapter);
        }
    }
}
