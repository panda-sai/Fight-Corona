package com.e.fight_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void linkedIn(View v)
    {
        int id=v.getId();
        String url;
        if(id==R.id.panda)
            url="https://www.linkedin.com/in/sai-sankara-kesava-nath-panda-ab3b8a18b";
        else if(id==R.id.heshwa)
            url="https://www.linkedin.com/in/heshwa";
        else
            url="http://www.linkedin.com/in/badam-nikhil-7253a3184";


        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
