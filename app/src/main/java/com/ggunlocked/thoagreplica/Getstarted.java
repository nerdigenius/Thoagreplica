package com.ggunlocked.thoagreplica;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.ggunlocked.thoagreplica.RestaturanList.List;

import static com.ggunlocked.thoagreplica.SignIn.status;

public class Getstarted extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager manager=getFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_account:
                    if(status==200)
                    {
                        UserAccount userAccount=new UserAccount();
                        manager.beginTransaction().replace(R.id.contentLayout,userAccount).commit();
                    }
                    else
                    {
                        Account account=new Account();
                        manager.beginTransaction().replace(R.id.contentLayout,account).commit();

                    }
                    return true;

                case R.id.navigation_food:
                    List list=new List();
                    manager.beginTransaction().replace(R.id.contentLayout,list).commit();
                    return true;
                case R.id.navigation_search:
                    Search search=new Search();
                    manager.beginTransaction().replace(R.id.contentLayout,search).commit();
                    return true;
                case R.id.navigation_cart:
                    Cart cart=new Cart();
                    manager.beginTransaction().replace(R.id.contentLayout,cart).commit();
                    return true;
                case R.id.navigation_order:
                    Order order=new Order();
                    manager.beginTransaction().replace(R.id.contentLayout,order).commit();
                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        Intent mintent=getIntent();
        int message=mintent.getIntExtra("intVariableName",0);
        checkString(message);






    }

    private void checkString(int message)
    {

         if(message==1)
        {
            SignIn signIn=new SignIn();
            FragmentManager manager=getFragmentManager();
            manager.beginTransaction().replace(R.id.contentLayout,signIn).commit();
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }

        else if (message==0)
         {
             List list=new List();
             FragmentManager manager=getFragmentManager();
             manager.beginTransaction().replace(R.id.contentLayout,list).commit();
             BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
             navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

         }
    }

}
