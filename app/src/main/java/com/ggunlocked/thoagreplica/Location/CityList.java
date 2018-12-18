package com.ggunlocked.thoagreplica.Location;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import com.ggunlocked.thoagreplica.Food2;
import com.ggunlocked.thoagreplica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CityList extends AppCompatActivity {

    private String URL="http://devteam.website/thoag_dev/api/v1/get-locations";
    private ArrayList<String>arrayList,cityIDList;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    public static String cityNames="Select location",cityID="";
    private Button findRestaurantCity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_list);

        arrayList=new ArrayList<String>();
        cityIDList=new ArrayList<String>();
        findRestaurantCity=(Button)findViewById(R.id.findRestaurantCity);

        listView=(ListView)findViewById(R.id.cities);

        GetCityNames();

        adapter=new CustomAdapter(this,arrayList);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        cityNames=arrayList.get(position).toString();
                        cityID=cityIDList.get(position).toString();
                        Intent intent=new Intent(getApplicationContext(),DistrictList.class);
                        intent.putExtra("SELECTED_ITEM",position);
                        startActivity(intent);
                        finish();
                    }
                }
        );


        findRestaurantCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Food2.class);
                startActivity(intent);
                finish();
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),Food2.class);
        startActivity(intent);
        finish();

    }

    public void GetCityNames(){
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject res=new JSONObject(response);
                    String text = res.getJSONObject("response").get("message").toString();
                    JSONArray jsonArray= (JSONArray) res.getJSONObject("response").get("location_list");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object=jsonArray.getJSONObject(i);
                        arrayList.add(object.get("city_name").toString());
                        cityIDList.add(object.get("id").toString());


                    }

                    listView.setAdapter(adapter);

                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context=getApplicationContext();

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
        });
        requestQueue.add(stringRequest);
    }

}
