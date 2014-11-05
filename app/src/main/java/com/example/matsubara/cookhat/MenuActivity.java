package com.example.matsubara.cookhat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.*;
import android.widget.Toast;


public class MenuActivity extends WebViewClient {
//public class MenuActivity extends Activity {

    public Activity owner;
    public WebView webView;

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        webView = new WebView(this);

        webView.setWebViewClient(new WebViewClient(){

            /*public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if(url.equals("appmenu://recipes")==true) {
                    Toast ts = Toast.makeText(owner, "レシピ表示", Toast.LENGTH_LONG);
                    ts.show();

                    //Intent intent = new Intent(MenuActivity.this, myLecpi.class);
                    //startActivity(Intent.createChooser(intent, "起動アプリを選択"));
                }else if(url.equals("appmenu://lessons")==true) {
                    Toast ts = Toast.makeText(owner, "彼につくる", Toast.LENGTH_LONG);
                    ts.show();
                }
                webView.stopLoading();

                //Activityを呼び出す場合
                return true;

                //WebView内に読み込み結果を表示する場合
                //return false;
            }
        });
    }*/
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.equals("intent://myLecpi")==true) {
            Toast ts = Toast.makeText(owner, "レシピ表示", Toast.LENGTH_LONG);
            ts.show();

            //Uri uri = Uri.parse("myLecpi.class");
            //Toast ts =Toast.makeText(owner, uri, Toast.LENGTH_LONG);
            Intent intent = new Intent(owner.getApplicationContext(), myLecpi.class);
            //Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            //intent.putExtra("url", url);
            //Intent intent =  new Intent(Intent.ACTION_VIEW, uri);
            owner.startActivity(intent);

            return false;

            //Intent intent = new Intent(MenuActivity.this, myLecpi.class);
            //startActivity(Intent.createChooser(intent, "起動アプリを選択"));
        }else if(url.equals("appmenu://lessons")==true) {
            Toast ts = Toast.makeText(owner, "彼につくる", Toast.LENGTH_LONG);
            ts.show();
        }
        view.stopLoading();

        //Activityを呼び出す場合
        return true;

        //WebView内に読み込み結果を表示する場合
        //return false;
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        Toast.makeText(owner, "ERROR :" + description, Toast.LENGTH_LONG).show();
    }
}
