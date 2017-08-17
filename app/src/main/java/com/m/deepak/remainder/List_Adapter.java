package com.m.deepak.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DEEPAK on 8/16/2017.
 */

public class List_Adapter extends ArrayAdapter<User> {
    private LayoutInflater mInflator;
    private ArrayList<User> users;
    private int mViewResourceId;


    public List_Adapter(Context context,int textViewResourceId,ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users=users;
        mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflator.inflate(mViewResourceId, null);

        User user = users.get(position);
        if(user !=null){
            TextView textView= (TextView) convertView.findViewById(R.id.textView);
            TextView textView1= (TextView) convertView.findViewById(R.id.textView2);
            TextView textView2= (TextView) convertView.findViewById(R.id.textView3);
            if(textView!=null){
                textView.setText(user.getTitle());
            }
            if(textView1!=null){
                textView1.setText(user.getDescription());
            }
            if(textView2!=null){
                textView2.setText(user.getDate());
            }
        }
        return convertView;
    }
    public void change(){
        notifyDataSetChanged();

    }
}
