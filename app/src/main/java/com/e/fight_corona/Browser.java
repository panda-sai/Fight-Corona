package com.e.fight_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Browser extends AppCompatActivity
{
    private WebView browser;
    Intent intent;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        browser=findViewById(R.id.webView);
        intent=getIntent();
        url=intent.getStringExtra("url");
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);

    }
}
