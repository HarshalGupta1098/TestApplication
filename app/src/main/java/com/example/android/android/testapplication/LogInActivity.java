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

public class LogInActivity extends AppCompatActivity {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);


        //Getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        /**
         * If the  getCurrentUser() of object is not null
         * that means the user is already logged in */
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(LogInActivity.this, ProfileActivity.class));
        }

        //Initializing progressDialog
        progressDialog = new ProgressDialog(this);

        //LOGIN BUTTON
        Button login = (Button) findViewById(R.id.log_in_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        //SIGN UP BUTTON
        Button switch_to_signup = (Button) findViewById(R.id.create_acc);
        switch_to_signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent_signup = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });
    }

    private void userLogin(){
        //Getting user email
        EditText emailEditTextView = (EditText) findViewById(R.id.user_email_login);
        String user_email = emailEditTextView.getText().toString().trim();

        //Getting user password
        EditText passwordEditTextView = (EditText) findViewById(R.id.user_password_login);
        String user_password = passwordEditTextView.getText().toString().trim();

        if(TextUtils.isEmpty(user_email)) {
            Toast.makeText(this, "enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(user_password)) {
            Toast.makeText(this, "enter valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        /**If all the details are filled, then
         * display the progress bar*/
        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //If task is successful
                        if(task.isSuccessful()){
                            finish();
                            Intent intent1 = new Intent(LogInActivity.this,
                                    ProfileActivity.class);
                            startActivity(intent1);
                        } else{
                            Toast.makeText(LogInActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
