package com.example.matsubara.cookhat;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.*;
import android.widget.Toast;


public class MenuActivity extends WebViewClient {

    public Activity owner;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.equals("appmenu://recipes")==true) {
            Toast ts = Toast.makeText(owner, "レシピ表示", Toast.LENGTH_LONG);
            ts.show();
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
}
