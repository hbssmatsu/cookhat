package com.example.matsubara.cookhat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

//import com.example.matsubara.cookhat.DatabaseHelper;
import com.example.matsubara.cookhat.DBHelper;
//import com.example.matsubara.cookhat.MyCustomListAdapter;
//import com.example.matsubara.cookhat.MyCustomListData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class myLecpi extends Activity {

    private  DBHelper mDbHelper;
    private SQLiteDatabase db;

    //private CustomAdapter customAdapter;
    //private ArrayList<MyData> mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lecpi);

        // sample.txt
        /*InputStream is = null;
        BufferedReader br = null;
        try {
            try {
                is = this.getAssets().open("sample.txt");
                br = new BufferedReader( new InputStreamReader(is));

                String samp_str;
                String samp_text = "";
                while ((samp_str=br.readLine())!=null) {
                    samp_text += samp_str + "\n";
                }
                Log.v("test", samp_text);
            }finally {
                 if(is != null) is.close();
                if(br != null ) {
                    br.close();
                }
            }
        }catch (Exception e) {
            Log.v("test", "ERROR");
        }*/

        // here

        mDbHelper = new DBHelper(this);
        mDbHelper.createEmptyDataBase(); //DB更新
        db = mDbHelper.getReadableDatabase();

        if(db!=null) {

            Map<Integer, Map> columus = mDbHelper.findAll("table_recipeLists");

            // ListView のため配列を初期化
            String[] listMenu = new String[columus.size()];
            String[] listId = new String[columus.size()];
            int[] mIcon = new int[columus.size()];

            // Image用に画像の設定
            for (int i=0; i<columus.size(); i++) {
                mIcon[i] = R.drawable.wanko;
            }

            List<MyCustomListData> objects = new ArrayList<MyCustomListData>();

            //Log.v("test", String.valueOf(columus.size()) );

            //String[] test_data = {};
            Iterator iterator = columus.keySet().iterator();
            //Map<String, String> rowData;
            String rowMunu;
            int key;
            String keyStr;
            while (iterator.hasNext()) {
                Object o = iterator.next();
                //rowData = columus.get(o);
                rowMunu = (String) columus.get(o).get("name");
                keyStr = o.toString();
                key = new Integer(keyStr).intValue();
                listMenu[key] = rowMunu;

                rowMunu = (String) columus.get(o).get("id");
                listId[key] = rowMunu;
                //Log.v("test", rowMunu);
                //System.out.println(o + " = " + columus.get(o));
            }

            for(int i=0; i<columus.size(); i++) {
                MyCustomListData tmpItem = new MyCustomListData();

                tmpItem.setBitmap(BitmapFactory.decodeResource(getResources(), mIcon[i]));
                tmpItem.setName(listMenu[i]);
                objects.add(tmpItem);
            }
            MyCustomListAdapter myCustomListAdapter = new MyCustomListAdapter(this, 0, objects);

            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(myCustomListAdapter);

            // アイテムクリック時ののイベントを追加
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent,
                                        View view, int pos, long id) {

                    // 選択アイテムを取得
                    ListView listView = (ListView)parent;
                    //String item = (String)listView.getItemAtPosition(pos);

                    // 画面起動
                    Intent intent = new Intent(getApplicationContext(), MyCookActivity.class);
                    intent.putExtra("id", pos);
                    startActivity(intent);
                }
            });

            // List Viewに表示
            /*ListView listView = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listMenu);
            listView.setAdapter(adapter);*/


            //Cursor cursor = db.query("table_recipeLists",null, null, null, null, null,null);

            //Log.v("test", "kita");

            /*String[] from = {"name"};
            int[] to = new int[]{android.R.layout.simple_expandable_list_item_2};
            ListAdapter adapter = new SimpleCursorAdapter(this, R.id.list, cursor, from, to, 0);

            // listView
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);*/

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_lecpi, menu);
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

   @Override
    protected void onResume() {
       super.onResume();

       mDbHelper = new DBHelper(this);

       try {
           Log.v("test", "createEmptyDataBase");
           mDbHelper.createEmptyDataBase();
           db = mDbHelper.openDataBase();
       }catch (SQLException sqle) {
           Log.v("test", "SQL ERROR");
       }
       // database instance
       //DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
       // create database
       //dbHelper.openWrite();

       //dbHelper.close();
   }

    /*private class CustomAdapter extends ArrayAdapter<MyData> {

        public CustomAdapter(Context context, ArrayList<MyData> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyData md = this.getItem(position);
            Context context = myLecpi.this;

            if (convertView==null) {
                LinearLayout ll = new LinearLayout(context);
                convertView = ll;

                TextView nameView = new TextView(context);
                ll.addView(nameView);

                TextView idView = new TextView(context);
                li.addView(idView);
            }

            TextView textView;
            textView = (TextView)convertView.findViewWithTag("str");
            textView.setText(md.str);

        }

    }
    */

}
