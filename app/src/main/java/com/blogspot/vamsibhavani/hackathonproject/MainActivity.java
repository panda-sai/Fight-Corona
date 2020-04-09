package com.blogspot.vamsibhavani.hackathonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<questionItem> questionList = new ArrayList<>();

        questionList.add(new questionItem("question1","author1"));
        questionList.add(new questionItem("question2","author2"));
        questionList.add(new questionItem("question3","author3"));

        liveNews();

    }

    private void liveNews() {

        String url ="http://newsapi.org/v2/top-headlines?country=in&apiKey=4b02ecab85ac4ca3a30e01c827a7bed8";
        String api = "4b02ecab85ac4ca3a30e01c827a7bed8";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(

                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            Log.e("Response",response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            Log.e("Response",error.toString());
                    }
                }


        );

    }
}
