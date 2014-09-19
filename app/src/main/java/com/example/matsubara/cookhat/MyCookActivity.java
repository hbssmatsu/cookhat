package com.example.matsubara.cookhat;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.matsubara.cookhat.DBHelper;

import java.util.Map;


public class MyCookActivity extends Activity {

    private  DBHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cook);

        // intentからパラメータ取得
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        mDbHelper = new DBHelper(this);
        db = mDbHelper.getReadableDatabase();
        if(db==null) return;

        Map<Integer, Map> columus = mDbHelper.findAll("table_recipeLists", id, 0);
        Map<String, String> rowData;
        if(columus.size()==1) {
            rowData = columus.get(0);
            TextView titleView = (TextView)findViewById(R.id.cookTitle);
            titleView.setText(rowData.get("name"));
            //Log.v("test", rowData.get("name"));

        }
        //Log.v("test", String.valueOf(columus.size()) );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_cook, menu);
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
