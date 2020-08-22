package com.example.ktphanem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class kutuphaneActivity extends AppCompatActivity {
    private Toolbar actionbarlogin;
    private Button btnekle,btnlistele,btnsil;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private Button cikisyapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kutuphane);
        init();

    }
    private void init() {
        actionbarlogin= ((Toolbar) findViewById(R.id.actionbarlogin));
        setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("İşlemler");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnekle=findViewById(R.id.kitapekle);
        btnlistele=findViewById(R.id.kitaplisetele);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        cikisyapp=findViewById(R.id.cikisyap);
        cikisyapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent=new Intent(kutuphaneActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentkitapeklegecis=new Intent(kutuphaneActivity.this,KitapEkleActivity.class);
                startActivity(intentkitapeklegecis);
                finish();
            }
        });
        btnlistele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kitapListele=new Intent(kutuphaneActivity.this,KitapListeleActivity.class);
                startActivity(kitapListele);
                finish();
            }
        });
    }

}