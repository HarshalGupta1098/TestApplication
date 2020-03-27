package com.example.android.android.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //firebase auth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        /**
         * If the  getCurrentUser() of object is not null
         * that means the user is already logged in */
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        Button btn1 = (Button) findViewById(R.id.signup_mainactivity);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(a);
            }
        });

        Button btn2 = (Button) findViewById(R.id.login_mainactivity);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(b);
            }
        });
    }
}
