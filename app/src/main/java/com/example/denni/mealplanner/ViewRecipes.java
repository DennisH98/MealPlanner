package com.example.denni.mealplanner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/*
* This activity contains a spinner with a list of the recipes
* When a item is selected, information regarding the meal is displayed
* This activity also has the ability to add the selected meal to the calendar
* */


public class ViewRecipes extends AppCompatActivity {
    DatabaseHelper dbhelp;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        dbhelp =DatabaseHelper.getInstance(this);

        //If calendar isn't working, it may require to sign in to gmail



        final Spinner mealSpinner = (Spinner)findViewById(R.id.spinnerMeal);
        final TextView txtMealTitle= (TextView)findViewById(R.id.txtMealTitle);
        final ImageView imgViewMeal= (ImageView)findViewById(R.id.imgViewMeal);
        final ImageView imgViewRec= (ImageView)findViewById(R.id.imgViewRec);
        final TextView txtViewIngred= (TextView)findViewById(R.id.txtViewIngred);
        final TextView txtTimeCal =(TextView)findViewById(R.id.txtTimeCal);
        final Button btnAdd= (Button)findViewById(R.id.btnAdd);
        final Button btnBack= (Button)findViewById(R.id.btnBack);

        final String mealClassify = getIntent().getStringExtra("id");

        ArrayList<String> mealList = new ArrayList<>();
        mealList = dbhelp.mealName(mealClassify);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, mealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mealSpinner.setAdapter(adapter);

        mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String mealItem = mealSpinner.getSelectedItem().toString();
                String mealResource = dbhelp.imgNameMeal(mealItem);
                String recResource = dbhelp.imgNameRec(mealItem);
                int calResource = dbhelp.calories(mealItem);
                String cookTime = dbhelp.cookTime(mealItem);

                String mealIngredients= dbhelp.ingredients(mealItem);
                String[] ingredArr = mealIngredients.split(",");

                String stringtoText= "Ingredients" + "\n";
                for (int x =0; x< ingredArr.length;x++){
                    stringtoText += "-" + ingredArr[x]+ "\n";

                }
                String timeCal =  "Cook time: "+ cookTime + "\n" + "Calories: " + calResource;
                txtViewIngred.setText(stringtoText);
                txtTimeCal.setText(timeCal);


                String drawRes = "drawable/"+mealResource;
                String drawResRec="drawable/"+recResource;

                // int imageResource = R.drawable.icon;
                int imageID = getResources().getIdentifier(drawRes, null, getPackageName());
                int recID= getResources().getIdentifier(drawResRec, null, getPackageName());
                //ImageView img = (ImageView)findViewById(R.id.iView);

               // Drawable image = getResources().getDrawable(imageID);
               // Drawable imageRec = getResources().getDrawable(recID);

                imgViewMeal.setBackgroundResource(imageID);
                imgViewRec.setBackgroundResource(recID);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendarEvent(v,mealItem,mealClassify);

                    }
                });




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewRecipes.this,ChooseMealTime.class));
            }
        });

    }

    public void calendarEvent(View view,String name,String id){
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //create var for hours/minutes depending on meal
        Calendar beginTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        if (id.equals("b")){
            beginTime.set(year,month,day,7,0);
            endTime.set(year,month,day,9,0);
        }else if (id.equals("l")){
            beginTime.set(year,month,day,12,0);
            endTime.set(year,month,day,13,30);
        }else{
            beginTime.set(year,month,day,18,30);
            endTime.set(year,month,day,20,0);
        }


        i.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());


        i.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());


        i.putExtra("title", name);
        //i.putExtra("description", "description test");
        startActivity(i);
    }
}
