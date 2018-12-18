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
public class Account extends Fragment {

    Button Sign,signup;




    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account, container, false);
        Sign=(Button)view.findViewById(R.id.signin);
        signup=(Button)view.findViewById(R.id.signup2);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn signIn=new SignIn();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction().replace(R.id.contentLayout,signIn).commit();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp signUp=new SignUp();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction().replace(R.id.contentLayout,signUp).commit();
            }
        });


        return view;
    }

}
