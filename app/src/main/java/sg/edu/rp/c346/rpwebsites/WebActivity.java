package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    WebView wvMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wvMyPage = findViewById(R.id.webView);
        wvMyPage.setWebViewClient(new WebViewClient());

        Intent recieve = getIntent();
        String url = recieve.getStringExtra("url");

        wvMyPage.loadUrl(url);
//        wvMyPage.getSettings().setJavaScriptEnabled(true);
        wvMyPage.getSettings().setBuiltInZoomControls(true);


    }
}
