package com.example.ayushyadav.networkingdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ayushyadav on 12/03/18.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ArrayListForListView> arrayListForListViews;

    public CustomAdapter(Context context, ArrayList<ArrayListForListView> arrayListForListViews) {
        this.context = context;
        this.arrayListForListViews = arrayListForListViews;
    }

    @Override
    public int getCount() {
        return arrayListForListViews.size();
    }

    @Override
    public ArrayListForListView getItem(int position) {
        return arrayListForListViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View output = convertView;
        if(output == null){
            output = layoutInflater.inflate(R.layout.activity_user,parent,false);
            CustomViewHolder holder = new CustomViewHolder(output);
            output.setTag(holder);
        }
        CustomViewHolder holder = (CustomViewHolder) output.getTag();
        ArrayListForListView custom = getItem(position);

        holder.nameTextView.setText(custom.getName());
        holder.userNameTextView.setText(custom.getUserName());
        holder.emailTextView.setText(custom.getEmailId());

        return output;
    }

    class CustomViewHolder{

        TextView nameTextView;
        TextView userNameTextView;
        TextView emailTextView;

        CustomViewHolder (View rowLayout){
            nameTextView = rowLayout.findViewById(R.id.nameTextView);
            userNameTextView = rowLayout.findViewById(R.id.userNameTextView);
            emailTextView = rowLayout.findViewById(R.id.emailTextView);
        }

    }
}
