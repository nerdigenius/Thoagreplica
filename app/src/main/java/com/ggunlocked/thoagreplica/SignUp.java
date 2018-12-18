package com.ggunlocked.thoagreplica;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {
    Button Sign,back;


    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        back=(Button)view.findViewById(R.id.back);
        Sign=(Button)view.findViewById(R.id.signin2);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn signIn=new SignIn();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction().replace(R.id.contentLayout,signIn).commit();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        return view;
    }

}
