package com.example.denni.mealplanner;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
/*
* This activity allows user to choose between breakfast, lunch and dinner
* Also contains a view calendar button to bring of the schedule
*
* */


public class ChooseMealTime extends AppCompatActivity {
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meal_time);

        ConstraintLayout conLayout =(ConstraintLayout)findViewById(R.id.con);

        //Breakfast
        final ImageButton btnBreak = (ImageButton)findViewById(R.id.btnBreak);
        final ImageButton btnBreakView=(ImageButton)findViewById(R.id.btnBreakView);
        //Hidden
        final TextView breakView = (TextView)findViewById(R.id.txtMealBreak);
        final TextView breakRecView= (TextView)findViewById(R.id.txtViewRec);

        //Lunch
        final ImageButton btnLunch = (ImageButton)findViewById(R.id.btnLunch);
        final ImageButton btnLunchView=(ImageButton)findViewById(R.id.btnLunchView);
        //Hidden
        final TextView lunchView = (TextView)findViewById(R.id.txtMealLunch);
        final TextView lunchRecView= (TextView)findViewById(R.id.txtViewRecL);

        //Dinner
        final ImageButton btnDinner = (ImageButton)findViewById(R.id.btnDinner);
        final ImageButton btnDinnerView=(ImageButton)findViewById(R.id.btnDinnerView);
        //Hidden
        final TextView dinnerView = (TextView)findViewById(R.id.txtMealDinner);
        final TextView dinnerRecView= (TextView)findViewById(R.id.txtViewRecD);

        final Button btnView =(Button)findViewById(R.id.btnView);


        btnBreak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //toast= Toast.makeText(ChooseMealTime.this,"hi",Toast.LENGTH_LONG);
                //toast.show();
                btnBreak.setVisibility(View.GONE);
                breakView.setVisibility(View.GONE);
                btnBreakView.setVisibility(View.VISIBLE);
                breakRecView.setVisibility(View.VISIBLE);

                btnLunch.setVisibility(View.VISIBLE);
                lunchView.setVisibility(View.VISIBLE);
                btnLunchView.setVisibility(View.GONE);
                lunchRecView.setVisibility(View.GONE);

                btnDinner.setVisibility(View.VISIBLE);
                dinnerView.setVisibility(View.VISIBLE);
                btnDinnerView.setVisibility(View.GONE);
                dinnerRecView.setVisibility(View.GONE);


        btnBreakView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseMealTime.this,ViewRecipes.class);
                intent.putExtra("id","b");
                startActivity(intent);

            }
        });

            }
        });

        btnLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLunch.setVisibility(View.GONE);
                lunchView.setVisibility(View.GONE);
                btnLunchView.setVisibility(View.VISIBLE);
                lunchRecView.setVisibility(View.VISIBLE);

                btnBreak.setVisibility(View.VISIBLE);
                breakView.setVisibility(View.VISIBLE);
                btnBreakView.setVisibility(View.GONE);
                breakRecView.setVisibility(View.GONE);

                btnDinner.setVisibility(View.VISIBLE);
                dinnerView.setVisibility(View.VISIBLE);
                btnDinnerView.setVisibility(View.GONE);
                dinnerRecView.setVisibility(View.GONE);

                btnLunchView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ChooseMealTime.this,ViewRecipes.class);
                        intent.putExtra("id","l");
                        startActivity(intent);

                    }
                });
            }
        });

        btnDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnDinner.setVisibility(View.GONE);
                dinnerView.setVisibility(View.GONE);
                btnDinnerView.setVisibility(View.VISIBLE);
                dinnerRecView.setVisibility(View.VISIBLE);

                btnBreak.setVisibility(View.VISIBLE);
                breakView.setVisibility(View.VISIBLE);
                btnBreakView.setVisibility(View.GONE);
                breakRecView.setVisibility(View.GONE);

                btnLunch.setVisibility(View.VISIBLE);
                lunchView.setVisibility(View.VISIBLE);
                btnLunchView.setVisibility(View.GONE);
                lunchRecView.setVisibility(View.GONE);

                btnDinnerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ChooseMealTime.this,ViewRecipes.class);
                        intent.putExtra("id","d");
                        startActivity(intent);

                    }
                });

            }
        });

        conLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBreak.setVisibility(View.VISIBLE);
                breakView.setVisibility(View.VISIBLE);
                btnBreakView.setVisibility(View.GONE);
                breakRecView.setVisibility(View.GONE);

                btnLunch.setVisibility(View.VISIBLE);
                lunchView.setVisibility(View.VISIBLE);
                btnLunchView.setVisibility(View.GONE);
                lunchRecView.setVisibility(View.GONE);

                btnDinner.setVisibility(View.VISIBLE);
                dinnerView.setVisibility(View.VISIBLE);
                btnDinnerView.setVisibility(View.GONE);
                dinnerRecView.setVisibility(View.GONE);

            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button will bring up schedule
                calendarView(v);
            }
        });
    }

    public void calendarView(View view){
        Calendar calendarView = Calendar.getInstance();
        long startMillis = 0;
        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
        startActivity(intent);
    }
}
