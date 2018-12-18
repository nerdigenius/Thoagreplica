package com.ggunlocked.thoagreplica;


import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Sort_page extends Fragment {

    Button payment;


    public Sort_page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view=inflater.inflate(R.layout.fragment_sort_page, container, false);

        payment=(Button)view.findViewById(R.id.payment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder= new AlertDialog.Builder(Sort_page.this.getContext());
                View mview= inflater.inflate(R.layout.dialogue_payment,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();
            }
        });






        return view;
    }

}
