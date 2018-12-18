package com.ggunlocked.thoagreplica.RestaturanList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ggunlocked.thoagreplica.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Drazzer on 10/23/2017.
 */

class RestaurantListAdapter extends BaseAdapter {

   /* ArrayList<String> arrayList=new ArrayList<String>();
    ArrayList<String> restaurant_logo_url=new ArrayList<String>();

    Context context;
    RestaurantListAdapter(ArrayList<String> arrayList,ArrayList<String> restaurant_logo_url,Context context)
    {
        this.arrayList=arrayList;
        this.restaurant_logo_url=restaurant_logo_url;
        this.context=context;
    }*/


   ArrayList <CustomList> arrayList=new ArrayList<>();
    Context context;

    public RestaurantListAdapter(ArrayList<CustomList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_restaurant_list_adapter,null);
        TextView name=(TextView)convertView.findViewById(R.id.Name);
        ImageView Icons=(ImageView)convertView.findViewById(R.id.Icons);
        RatingBar ratingBar=(RatingBar)convertView.findViewById(R.id.ratingBar);
        TextView payBy=(TextView)convertView.findViewById(R.id.payMethod);



        CustomList customList;
        customList=arrayList.get(position);
        ratingBar.setRating(Float.parseFloat(customList.getAverage_rating()));
        name.setText(customList.getRestaurant_name());
        payBy.setText(customList.getAccept_sadad()+ customList.getAccept_card()+customList.getAccept_cash_delivery());
        Picasso.with(context).load(customList.getRestaurant_logo()).into(Icons);



        return convertView;
    }
}
