package com.naresh.kingupadhyay.mylistapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter( Context context, String[] fruits) {
        super(context,R.layout.coustom_row,fruits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myinflater=LayoutInflater.from(getContext());
        View CustomView=myinflater.inflate(R.layout.coustom_row,parent,false);

        //get a reference.
        String singlefruitItem=getItem(position);
        TextView myText=(TextView) CustomView.findViewById(R.id.myText);
        ImageView myImage=(ImageView) CustomView.findViewById(R.id.myImage);

        myText.setText(singlefruitItem);
        myImage.setImageResource(R.drawable.apple);
        return CustomView;
    }
}
