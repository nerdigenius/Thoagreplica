package com.ggunlocked.thoagreplica;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends Fragment {

    private Button signup,signIn,back;
    private EditText edt1,edt2;
    static int status;
    static String username;


    public SignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_in, container, false);


        signup=(Button)view.findViewById(R.id.signup1);
        signIn=(Button)view.findViewById(R.id.btnSignIn);
        back=(Button)view.findViewById(R.id.back);
        edt1=(EditText)view.findViewById(R.id.edtEmail);
        edt2=(EditText)view.findViewById(R.id.edtPassword);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp signUp=new SignUp();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction().replace(R.id.contentLayout,signUp).commit();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SigningIn();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    private void SigningIn()
    {
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, "http://devteam.website/thoag_dev/api/v1/sign-in",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject res=new JSONObject(response);
                            String text = res.getJSONObject("response").get("message").toString();
                            int responses=res.getJSONObject("meta").getInt("status");
                            Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
                            if(responses==200)
                            {
                                FragmentManager manager=getFragmentManager();
                                UserAccount userAccount=new UserAccount();
                                manager.beginTransaction().replace(R.id.contentLayout,userAccount).commit();
                                status=responses;
                                username=res.getJSONObject("response").getJSONObject("user_detail").get("first_name").toString();

                            }
                            else
                            {
                                status=100;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "There Was A Fatal Error!!!!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String, String>();
                params.put("user_login_id",edt1.getText().toString());
                params.put("password",edt2.getText().toString());
                params.put("device_token","uyri43242rfasdfy7348328");
                return params;
            }
        };

        requestQueue.add(stringRequest);

    }

}
