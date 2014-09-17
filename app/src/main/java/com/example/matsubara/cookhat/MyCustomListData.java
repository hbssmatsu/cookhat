package com.example.matsubara.cookhat;

import android.graphics.Bitmap;

/**
 * Created by matsubara on 2014/09/10.
 */
public class MyCustomListData {
    private Bitmap bitmap;
    private String name;
    private String content;

    public void setBitmap(Bitmap img) {
        bitmap = img;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setName(String str) {
        name = str;
    }

    public String getName() {
        return name;
    }

    public void setContent(String str) {
        content = str;
    }

    public String getContent() {
        return content;
    }
}
