package com.example.denni.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 300264443 on 3/28/2018.
 *
 * This class extends SQLiteOpenHelper that will create a database and tables for meals, and user
 * Also the class contains multiple methods that add to the tables and retrieve data using queries
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "meal_db";
    private static final int DB_VER = 1;
    private static DatabaseHelper instance;

    //TABLES NAME
    //private static final String TABLE_USER = "user";
    //User Column
    //private static final String COL_USER = "username";
    //private static final String COL_PASS = "password";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createStudents = "CREATE TABLE IF NOT EXISTS user(username text primary key,password text);";
        String createMeal = "CREATE TABLE IF NOT EXISTS meal(mName text primary key,ingred text,mealImg text,recipeImg text,time text, cal integer,mealID text);";

        sqLiteDatabase.execSQL(createStudents);
        sqLiteDatabase.execSQL(createMeal);

    }

    public void addUser(String username, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues userCV =new ContentValues();
        userCV.put("username",username);
        userCV.put("password",password);

        try{
            db.insert("user",null,userCV);


        }catch (Exception ex){



        }
    }

    public void addMeal(String mName, String ingred,String mealImg,String recipeImg, String time, int cal,String mealID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues mealCV =new ContentValues();
        mealCV.put("mName",mName);
        mealCV.put("ingred",ingred);
        mealCV.put("mealImg",mealImg);
        mealCV.put("recipeImg",recipeImg);
        mealCV.put("time",time);
        mealCV.put("cal",cal);
        mealCV.put("mealID",mealID);

        try{
            db.insert("meal",null,mealCV);
            //Log.e("check", "not an error but a check");

        }catch (Exception ex){



        }
    }
    //testing purposes
    public String browse(){
        String browseUser = "SELECT * FROM meal";
        SQLiteDatabase db = getReadableDatabase();
        String name ="";
        try{
            Cursor cursor =db.rawQuery(browseUser,null);
            //browse password
            if (cursor != null){
                cursor.moveToFirst();
                do{
                    name += cursor.getString(1)+ " ";

                }while (cursor.moveToNext());
                cursor.close();


            }
        }catch (Exception ex){

        }
        return name;
    }

    public void deleteUserTable() {
        SQLiteDatabase db = getWritableDatabase();

        try {

            db.delete("user", null, null);

        } catch (Exception e) {

        }

    }

    public boolean verifyPassword(String user,String pass){
        boolean verify =false;
        String verUser = "SELECT username FROM user";
        SQLiteDatabase db = getReadableDatabase();

        try{
            Cursor cursor =db.rawQuery(verUser,null);
            //browse password
            if (cursor != null){
                cursor.moveToFirst();
                do{
                    String dbUser = cursor.getString(0);
                    if (dbUser.equals(user)){

                        //verify password
                        String valPass ="SELECT password FROM user WHERE username = ?";
                        Cursor cursorPass =db.rawQuery(valPass,new String[]{dbUser});

                        cursorPass.moveToFirst();
                        String dbPass= cursorPass.getString(0);
                        if (dbPass.equals(pass)){
                            verify = true;
                        }else
                        {
                            verify = false;
                        }

                        break;

                    }else{
                        verify = false;
                    }

                }while (cursor.moveToNext());
                cursor.close();


            }
        }catch (Exception ex){

        }

        return verify;
    }

    public String imgNameMeal(String mealName){
        String browseMeal = "SELECT mealImg FROM meal WHERE mName = ?";
        SQLiteDatabase db = getReadableDatabase();
        String imgName ="";
        try{
            Cursor cursor =db.rawQuery(browseMeal,new String[]{mealName});
            //browse password

                cursor.moveToFirst();

                    imgName = cursor.getString(0);

                cursor.close();



        }catch (Exception ex){

        }
        return imgName;
    }

    public String imgNameRec(String mealName){
        String browseRec = "SELECT recipeImg FROM meal WHERE mName = ?";
        SQLiteDatabase db = getReadableDatabase();
        String imgName ="";
        try{
            Cursor cursor =db.rawQuery(browseRec,new String[]{mealName});
            //browse password
           // if (cursor != null){
                cursor.moveToFirst();
               // do{
                    imgName = cursor.getString(0);
              //  }while (cursor.moveToNext());
                cursor.close();


            //}
        }catch (Exception ex){

        }
        return imgName;
    }

    public int calories(String mealName){
        String findCal = "SELECT cal FROM meal WHERE mName = ?";
        SQLiteDatabase db = getReadableDatabase();
        int calories =0;
        try{
            Cursor cursor =db.rawQuery(findCal,new String[]{mealName});
            //browse password
            // if (cursor != null){
            cursor.moveToFirst();
            // do{
            calories = cursor.getInt(0);
            //  }while (cursor.moveToNext());
            cursor.close();
            //}
        }catch (Exception ex){

        }
        return calories;
    }

    public String cookTime(String mealName){
        String findCal = "SELECT time FROM meal WHERE mName = ?";
        SQLiteDatabase db = getReadableDatabase();
        String cTime ="";
        try{
            Cursor cursor =db.rawQuery(findCal,new String[]{mealName});
            //browse password
            // if (cursor != null){
            cursor.moveToFirst();
            // do{
            cTime = cursor.getString(0);
            //  }while (cursor.moveToNext());
            cursor.close();
            //}
        }catch (Exception ex){

        }
        return cTime;
    }
    //create method for recipe img
    public String ingredients(String mealName){
        String ingredients="";
        String getIngred = "SELECT ingred FROM meal WHERE mName = ?";

        SQLiteDatabase db = getReadableDatabase();

        try{
            Cursor cursor =db.rawQuery(getIngred,new String[]{mealName});
            //browse password


                cursor.moveToFirst();

                    ingredients= cursor.getString(0);


                cursor.close();


        }catch (Exception ex){

        }
        return ingredients;
    }

    public ArrayList<String> mealName(String id){
        ArrayList<String> meal= new ArrayList<>();
        String findMealName = "SELECT mName FROM meal WHERE mealID = ?";
        //where mealID = b/l/d
        SQLiteDatabase db = getReadableDatabase();

        try{
            Cursor cursor =db.rawQuery(findMealName,new String[]{id});
            //browse password
            if (cursor != null){
                cursor.moveToFirst();
                do{
                    meal.add(cursor.getString(0));

                }while (cursor.moveToNext());
                cursor.close();


            }
        }catch (Exception ex){

        }

        return meal;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}

