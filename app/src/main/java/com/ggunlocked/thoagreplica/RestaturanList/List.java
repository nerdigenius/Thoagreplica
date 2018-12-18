package com.ggunlocked.thoagreplica.RestaturanList;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ggunlocked.thoagreplica.R;
import com.ggunlocked.thoagreplica.Sort_page;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.ggunlocked.thoagreplica.Location.CityList.cityID;
import static com.ggunlocked.thoagreplica.Location.DistrictList.districtID;


public class List extends Fragment {

    ListView listView;
    String restaurant_logo_url;
    ArrayList<CustomList> customListArrayList;
    String restaurant_url,restaurant_logo,restaurant_name,restaurant_owner,contact_person,
            contact_number,contact_email,average_rating,accept_cash_delivery,accept_card
            ,accept_sadad;
    RestaurantListAdapter restaurantListAdapter;
    Button sort;


    public List() {



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list, container, false);

        customListArrayList=new ArrayList<CustomList>();


        listView=(ListView)view.findViewById(R.id.LoactionList);
        sort=(Button)view.findViewById(R.id.Sort);
        GetCityNames();
        restaurantListAdapter =new RestaurantListAdapter(customListArrayList,getContext());
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment duedateFrag = new Sort_page();
                FragmentTransaction ft  = getFragmentManager().beginTransaction();
                ft.replace(R.id.contentLayout, duedateFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });




        return  view;
    }





    public void GetCityNames(){

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, "http://devteam.website/thoag_dev/api/v1/find-restaurant", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject res=new JSONObject(response);
                    String text = res.getJSONObject("response").get("message").toString();
                    JSONArray jsonArray=res.getJSONObject("response").getJSONArray("closed_restaurant");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object1= jsonArray.getJSONObject(i);

                        restaurant_url =object1.get("restaurant_url").toString();
                        restaurant_logo=object1.get("restaurant_logo").toString();
                        restaurant_name=object1.get("restaurant_name").toString();
                        restaurant_logo_url="http://devteam.website/thoag_dev/content-dir/restaurant/"+restaurant_url+"/images/"+restaurant_logo;
                        restaurant_owner=object1.get("restaurant_owner").toString();
                        average_rating=object1.get("average_rating").toString();
                        accept_cash_delivery=object1.get("accept_cash_delivery").toString();
                        accept_card=object1.get("accept_card").toString();
                        accept_sadad=object1.get("accept_sadad").toString();


                        CustomList customList=new CustomList(restaurant_url,restaurant_logo_url,restaurant_name,average_rating,accept_sadad,accept_card,accept_cash_delivery);
                        customListArrayList.add(customList);



                    }
                    Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
                    listView.setAdapter(restaurantListAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context=getContext();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context,
                            "Connection Timed Out",
                            Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    //TODO
                } else if (error instanceof NetworkError) {
                    //TODO
                } else if (error instanceof ParseError) {
                    //TODO
                }
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String, String>();
                params.put("city_id",cityID);
                params.put("district_id",districtID);
                params.put("delivery_date","2017-10-02");
                params.put("delivery_time","10:00");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }





}

class CustomList{
    String restaurant_url,restaurant_logo,restaurant_name,restaurant_owner,average_rating,accept_sadad="",accept_card="",accept_cash_delivery;



    public CustomList(String restaurant_url, String restaurant_logo, String restaurant_name, String average_rating, String accept_sadad, String accept_card, String accept_cash_delivery) {
        this.restaurant_url = restaurant_url;
        this.restaurant_logo = restaurant_logo;
        this.restaurant_name = restaurant_name;
        this.average_rating= average_rating;
        if(accept_card.equals("1"))
        {
            this.accept_card=" Card";
        }
        if (accept_sadad.equals("1"))
        {
            this.accept_sadad=" Sadat";
        }
        if (accept_cash_delivery.equals("1"))
        {
            this.accept_cash_delivery=" Cash On Delivery";
        }
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public String getRestaurant_url() {
        return restaurant_url;
    }

    public String getRestaurant_logo() {
        return restaurant_logo;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getAccept_sadad() {
        return accept_sadad;
    }

    public String getAccept_card() {
        return accept_card;
    }
    public String getAccept_cash_delivery() {
        return accept_cash_delivery;
    }
}



