package com.example.matsubara.cookhat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity implements View.OnClickListener {

    //private Button button_segue;

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        findViews();
        webView.loadUrl("file:///android_asset/index.html");

        //setContentView(webView, new ActionBar.LayoutParams(WC, WC));

        //button_segue = (Button)findViewById(R.id.mybutton2);
        //button_segue.setOnClickListener(this);
    }

    public void findViews() {
        webView = (WebView)findViewById(R.id.webview);

        // muenu
        MenuActivity mvc = new MenuActivity();
        mvc.owner = this;
        //webView = mvc.webView;

        // アプリ内に遷移する。
        //webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient((WebViewClient)mvc);


        //JavaScript enable
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // display
        webSettings.setUseWideViewPort(true); // ワイドビューポート
        webSettings.setLoadWithOverviewMode(true); // ズームアウト
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        // 背景透過
        webView.setBackgroundColor(0);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    public void changeLabel(View view){
        //Log.v("test", "click");
        //TextView tv=(TextView)findViewById(R.id.myLabel);
        //tv.setText("changed");
    }

    public  void onClick(View view) {
        //Log.v("test", "彼ごはん");
        //if(view==button_segue) {
            //Log.v("test", "onClinckEvent");
            //Intent intent = new Intent(this, myLecpi.class);
            //startActivity(intent);
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
