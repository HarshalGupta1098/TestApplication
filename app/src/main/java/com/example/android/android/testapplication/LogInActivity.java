package com.example.android.android.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        Button login = (Button) findViewById(R.id.log_in_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this, "logged in", Toast.LENGTH_SHORT).show();
            }
        });

        Button switch_to_signup = (Button) findViewById(R.id.create_acc);
        switch_to_signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent_signup = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent_signup);
            }
        });
    }
}
