package com.example.denni.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
* This activity allows user to enter in a username and password to create a new account
*
* */

public class SignUp extends AppCompatActivity {
    DatabaseHelper dbhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dbhelp= DatabaseHelper.getInstance(this);

        final Button btnCreate = (Button)findViewById(R.id.btnCreate);
        final EditText userReg =(EditText)findViewById(R.id.editUserReg);
        final EditText passReg =(EditText)findViewById(R.id.editPassReg);
        final EditText passConfirmReg= (EditText)findViewById(R.id.editPassConfirm);
        final TextView txtSuccess =(TextView)findViewById(R.id.txtSuccess);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAccount =userReg.getText().toString();
                String userPass = passReg.getText().toString();
                String userPassConfirm = passConfirmReg.getText().toString();

                if (userAccount.equals("")|| userPass.equals("")|| userPassConfirm.equals("")){
                    txtSuccess.setText("SOME OF THE REQUIRED ENTRIES ARE EMPTY");
                }else if (!userPass.equals(userPassConfirm)){
                    txtSuccess.setText("PASSWORD IS NOT THE SAME");

                }else{
                    txtSuccess.setText("Account created!");
                    dbhelp.addUser(userAccount,userPass);
                }

            }
        });
    }
}
