package com.ggunlocked.thoagreplica.Location;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.ggunlocked.thoagreplica.Food2;
import com.ggunlocked.thoagreplica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DistrictList extends AppCompatActivity {

    private int i;
    private ArrayList arrayList,districtIDlist;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String URL="http://devteam.website/thoag_dev/api/v1/get-locations";
    public static String districtName="",districtID="";
    private Button chooseYourCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_list2);

        Intent intent=getIntent();
        i=intent.getIntExtra("SELECTED_ITEM",0);
        arrayList=new ArrayList<String>();
        districtIDlist=new ArrayList<String>();
        listView=(ListView)findViewById(R.id.districts);
        chooseYourCity=(Button)findViewById(R.id.chooseYourCIty);

        GetDistrictNames();

        adapter=new CustomAdapter(this,arrayList);

        chooseYourCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CityList.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),CityList.class);
        startActivity(intent);
        finish();

    }



    private void GetDistrictNames()
    {

        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject res=new JSONObject(response);
                    String text = res.getJSONObject("response").get("message").toString();
                    JSONArray jsonArray= (JSONArray) res.getJSONObject("response").get("location_list");
                   // JSONObject cityObject= (JSONObject) res.getJSONObject("response").get("location_list");
                  //  cityName=cityObject.getString("city_name");
                    JSONObject object=jsonArray.getJSONObject(i);
                    JSONArray districts=object.getJSONArray("district_list");
                    for(int i=0;i<districts.length();i++)
                    {
                        JSONObject object2=districts.getJSONObject(i);
                        arrayList.add(object2.get("district_name").toString());
                        districtIDlist.add(object2.get("id").toString());

                    }

                    listView.setAdapter(adapter);


                    Toast.makeText(getApplicationContext(),districts.length()+" Locations Found",Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
                }

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        districtName=", "+arrayList.get(position).toString();
                        Toast.makeText(getApplicationContext(),arrayList.get(position).toString(),Toast.LENGTH_SHORT).show();
                        districtID=districtIDlist.get(position).toString();
                        Intent intent=new Intent(DistrictList.this, Food2.class);
                        startActivity(intent);
                        finish();
                    }
                });

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
