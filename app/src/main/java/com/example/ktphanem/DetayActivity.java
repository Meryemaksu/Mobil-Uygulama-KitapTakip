package com.example.ktphanem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetayActivity extends AppCompatActivity {
    private Toolbar actionbarlogin;
    TextView kitapismi, kitapyazari, adet,rafyeri, fiyat;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reff;
    private FirebaseAuth auth;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        actionbarlogin= ((Toolbar) findViewById(R.id.actionbarlogin));
        setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Kitap Detay");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intent = getIntent();

        kitapismi = findViewById(R.id.kitapismi);
        kitapyazari = findViewById(R.id.yazarismi);
        rafyeri=findViewById(R.id.rafyeri);
        adet = findViewById(R.id.adet);
        fiyat = findViewById(R.id.fiyat);

        kitapismi.setText(intent.getStringExtra("Kitapadi"));
        kitapyazari.setText(intent.getStringExtra("Yazaradi"));
        rafyeri.setText(intent.getStringExtra("Rafyeri"));
        adet.setText(intent.getStringExtra("Adet"));
        fiyat.setText(intent.getStringExtra("Fiyat"));


    }

}