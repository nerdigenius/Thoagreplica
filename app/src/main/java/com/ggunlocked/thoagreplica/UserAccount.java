package com.ggunlocked.thoagreplica;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.ggunlocked.thoagreplica.SignIn.username;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccount extends Fragment {

    TextView textUserName;

    public UserAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_user_account, container, false);
        textUserName= (TextView) view.findViewById(R.id.textUserName);
        textUserName.setText(username);



        return view;
    }

}
