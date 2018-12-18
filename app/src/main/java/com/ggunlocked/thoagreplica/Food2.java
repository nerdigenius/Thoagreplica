package com.ggunlocked.thoagreplica;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.ggunlocked.thoagreplica.Cuisines.CuisineList;
import com.ggunlocked.thoagreplica.Location.CityList;
import com.ggunlocked.thoagreplica.Location.DistrictList;

import static com.ggunlocked.thoagreplica.Cuisines.CuisineList.cuisineID;
import static com.ggunlocked.thoagreplica.Cuisines.CuisineList.cuisineName;
import static com.ggunlocked.thoagreplica.Location.CityList.cityID;
import static com.ggunlocked.thoagreplica.Location.CityList.cityNames;
import static com.ggunlocked.thoagreplica.Location.DistrictList.districtID;
import static com.ggunlocked.thoagreplica.Location.DistrictList.districtName;


public class Food2 extends Activity {
    Button date,back,find,btnLoaction,btnCuisine;
   private int dates,month,year,hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food2);



            date=(Button) findViewById(R.id.button7);
            back=(Button) findViewById(R.id.back);
            find=(Button)findViewById(R.id.button4);
            btnLoaction=(Button)findViewById(R.id.btnLocation);
            btnCuisine=(Button)findViewById(R.id.btnCuisine);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c=Calendar.getInstance();
                    String setter="";
                    dates=c.get(Calendar.DAY_OF_MONTH);
                    month=c.get(Calendar.MONTH);
                    year=c.get(Calendar.YEAR);
                    hour=c.get(Calendar.HOUR);
                    minute=c.get(Calendar.MINUTE);


                   DatePickerDialog datePickerDialog=new DatePickerDialog(Food2.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                            //date.setText(dayOfMonth+"/"+month+"/"+year);
                            final TimePickerDialog timepicker=new TimePickerDialog(Food2.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    int months=month+1;
                                    if(hourOfDay>12)
                                    {
                                        hourOfDay=hourOfDay-12;
                                        date.setText(dayOfMonth+"/"+months+"/"+year+" "+"Time: "+hourOfDay+":"+minute+" pm");
                                    }
                                    else
                                    {
                                        date.setText(dayOfMonth+"/"+months+"/"+year+" "+"Time: "+hourOfDay+":"+minute+" am");
                                    }

                                }
                            },hour,minute,false);
                            timepicker.show();
                        }
                    },year,month,dates);

                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());



                    datePickerDialog.show();



                }
            });



        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(districtID.equals("")||cityID.equals(""))
                {
                    btnCuisine.setBackgroundResource(R.drawable.button_border3_error);
                    btnLoaction.setBackgroundResource(R.drawable.button_border3_error);
                }

                else
                {
                    int j=0;
                    btnCuisine.setBackgroundResource(R.drawable.button_border3);
                    btnLoaction.setBackgroundResource(R.drawable.button_border3);
                    Intent intent=new Intent(Food2.this,Getstarted.class);
                    intent.putExtra("intVariableName",j);
                    startActivity(intent);
                }



            }
        });



        btnLoaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Food2.this, CityList.class);
                startActivity(intent);
                finish();

            }
        });

        btnCuisine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Food2.this, CuisineList.class));
                finish();

            }
        });

        btnLoaction.setText(cityNames+districtName);
        btnCuisine.setText(cuisineName);





        }


}

