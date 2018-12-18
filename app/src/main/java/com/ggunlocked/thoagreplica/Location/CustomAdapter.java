package com.ggunlocked.thoagreplica.Location;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ggunlocked.thoagreplica.R;

import java.util.ArrayList;

/**
 * Created by Drazzer on 10/18/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {


    public CustomAdapter(@NonNull Context context,ArrayList<String> resource) {
        super(context, R.layout.custom_list, resource);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.custom_list,parent,false);

        String cities=getItem(position);
        TextView text=(TextView)view.findViewById(R.id.customtextList);
        text.setText(cities);

        return view;
    }
}
