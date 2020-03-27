package com.example.android.android.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();

        EditText messageEditTextView = (EditText) findViewById(R.id.user_msg);


        UserPost userpost = new UserPost(messageEditTextView.getText().toString());
        mDatabaseReference = mDatabase.getReference().child("user");
        mDatabaseReference.setValue(userpost);

        //LOG OUT BUTTON
        Button log_out = (Button) findViewById(R.id.log_out_btn);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
    }

}
