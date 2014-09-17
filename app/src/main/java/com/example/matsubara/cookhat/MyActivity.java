package com.example.matsubara.cookhat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity implements View.OnClickListener {

    private Button button_segue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        button_segue = (Button)findViewById(R.id.mybutton2);
        button_segue.setOnClickListener(this);
    }

    public void changeLabel(View view){
        //Log.v("test", "click");
        TextView tv=(TextView)findViewById(R.id.myLabel);
        tv.setText("changed");
    }

    public  void onClick(View view) {
        //Log.v("test", "彼ごはん");
        if(view==button_segue) {
            //Log.v("test", "onClinckEvent");
            Intent intent = new Intent(this, myLecpi.class);
            startActivity(intent);
        }
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
