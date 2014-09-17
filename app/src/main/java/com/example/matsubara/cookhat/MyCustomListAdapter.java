package com.example.matsubara.cookhat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matsubara on 2014/09/10.
 */
public class MyCustomListAdapter extends ArrayAdapter<MyCustomListData> {

    private LayoutInflater layoutInflater;

    private Context myContext;

    public MyCustomListAdapter(Context context, int viewResourceId, List<MyCustomListData> objects) {
        super(context, viewResourceId, objects);
        myContext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyCustomListData item = (MyCustomListData)getItem(position);

        if( convertView==null ) {
            convertView = layoutInflater.inflate(R.layout.list, null);
        }

        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.listContainer);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.listImg);
        imageView.setImageBitmap(item.getBitmap());

        TextView listNameTextView = (TextView) convertView.findViewById(R.id.listName);
        listNameTextView.setText(item.getName());

        return convertView;
    }

}
