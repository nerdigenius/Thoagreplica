/*package com.ggunlocked.thoagreplica.RestaturanList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by Drazzer on 10/23/2017.
 */

/*public class ParsedData {

    Context context;
    JSONObject object1;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Bitmap> ImageList=new ArrayList<>();

    ParsedData(Context context)
    {
        this.context=context;
        GetCityNames();
    }




    public void GetCityNames(){

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, "http://plx5.website/thoag/api/v1/find-restaurant", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject res=new JSONObject(response);
                    String text = res.getJSONObject("response").get("message").toString();
                    arrayList=setClosedRestaurantName(res);
                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String, String>();
                params.put("city_id","1");
                params.put("district_id","1");
                params.put("delivery_date","2017-10-02");
                params.put("delivery_time","10:00");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }






    public void setClosedRestaurantImage(JSONObject object) throws JSONException {
        JSONArray jsonArray=object.getJSONObject("response").getJSONArray("closed_restaurant");
        for (int i=0;i<jsonArray.length();i++)
        {
            JSONObject object1= jsonArray.getJSONObject(i);
            //ImageList.add(object1.get("restaurant_logo"));
        }

    }



}*/
