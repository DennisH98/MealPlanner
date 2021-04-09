package com.example.denni.mealplanner;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/*
* This activity is the first activity that is seen when app launches
* This activity is holds the user login page that will allow user to move to the ChooseMealTime Activity
* If the user does not have an account, they can press the button to go to the SignUp activity
*
* */

public class MainActivity extends AppCompatActivity {
    Toast toast;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Username: Dennis, Password: 12345 <- this account works

        ImageView img = (ImageView)findViewById(R.id.imgAni);
        img.setBackgroundResource(R.drawable.animation);

        AnimationDrawable animate = (AnimationDrawable) img.getBackground();
        animate.setEnterFadeDuration(600);
        animate.start();

        final Button btn = (Button)findViewById(R.id.btnLogin);
        final Button btnSign= (Button)findViewById(R.id.btnSignUp);
        final EditText userInput = (EditText)findViewById(R.id.editTextUser);
        final EditText passInput=(EditText)findViewById(R.id.editTextPass);
        final TextView errorText =(TextView)findViewById(R.id.txtInvalid);

        helper = DatabaseHelper.getInstance(this);

        //helper.addUser("Dennis","12345");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userInput.getText().toString();
                String pass = passInput.getText().toString();

                if(helper.verifyPassword(user,pass)){
                    //toast= Toast.makeText(MainActivity.this,"correct",Toast.LENGTH_LONG);
                    //toast.show();
                    errorText.setText("");
                    startActivity(new Intent(MainActivity.this,ChooseMealTime.class));
                }else{
                    errorText.setText("Username or Password is invalid");
                }

            }
        });

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

    }

}
