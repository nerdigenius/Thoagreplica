package com.ggunlocked.thoagreplica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button getstarted,signin;
    Intent i,k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getstarted=(Button)findViewById(R.id.button2);
        signin=(Button)findViewById(R.id.signin);


       i=new Intent(MainActivity.this,Getstarted.class);
        k=new Intent(MainActivity.this,Food2.class);


        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(k);
            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j=1;
                i.putExtra("intVariableName",j);
                startActivity(i);
            }
        });






    }




}
