package com.example.ktphanem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnuyekayit,btnuyegiris;
    private FirebaseUser currentUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        buttonMainClick();
        btnuyekayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKayit=new Intent(MainActivity.this,UyeKayitActivity    .class);
                startActivity(intentKayit);
            }
        });
        btnuyegiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentlogin);
            }
        });

    }

    private void buttonMainClick() {
        btnuyegiris= ((Button) findViewById(R.id.uyegiris));
        btnuyekayit= ((Button) findViewById(R.id.uyekayit));
    }

    @Override
    protected void onStart() {
        if (currentUser!=null) {
            Intent current = new Intent(MainActivity.this, kutuphaneActivity.class);
            startActivity(current);
            finish();
        }
        super.onStart();
    }
}