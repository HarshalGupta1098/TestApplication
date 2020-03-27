package com.example.android.android.testapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    //Defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    //Defining ProgressDialog object
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        /**Onclick listener for sign up*/
         Button signup_btn = (Button) findViewById(R.id.sign_up_btn);
         signup_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 registerUser();
             }
         });

        /**Onclick listener for switch_to_login*/
         Button switch_to_login = (Button) findViewById(R.id.switch_to_login);
         switch_to_login.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View view){
                 Intent intent_login = new Intent(SignUpActivity.this, LogInActivity.class);
                 startActivity(intent_login);
             }
         });
    }

    private void registerUser(){
        //Getting user name
        EditText userEditTextView = (EditText) findViewById(R.id.user_name);
        String user_name = userEditTextView.getText().toString();

        //Getting user email
        EditText emailEditTextView = (EditText) findViewById(R.id.user_email);
        String user_email = emailEditTextView.getText().toString().trim();

        //Getting user password
        EditText passwordEditTextView = (EditText) findViewById(R.id.user_password);
        String user_password = passwordEditTextView.getText().toString().trim();

        if(TextUtils.isEmpty(user_name)) {
            Toast.makeText(this, "enter user name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(user_email)) {
            Toast.makeText(this, "enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(user_password)) {
            Toast.makeText(this, "enter valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        /**If all the details are filled, then
         * display the progress bar */
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        //Create a new user
        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Successfully registered",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Registration Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

        //Checks if the user is already logged in or nor
    }

}
