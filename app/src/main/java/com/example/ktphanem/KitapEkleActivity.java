package com.example.ktphanem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KitapEkleActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar actionbarlogin;
    private Button cikisyapp;
    private EditText txtkitapadi, txtyazarismi, txtraf, txtfiyat, txtadet;
    private Button btnyenikitapekle;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reff;
    private FirebaseAuth auth;
    private String user_id;
    ImageView iv_back_button;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_ekle);
        init();
        insertdatabase();
    }
    private void init() {
        actionbarlogin = ((Toolbar) findViewById(R.id.actionbarlogin));
        auth = FirebaseAuth.getInstance();
        user_id = auth.getCurrentUser().getUid();
        tv_title = findViewById(R.id.tv_title);
        iv_back_button = findViewById(R.id.iv_back_button);
        tv_title.setOnClickListener(this);
        tv_title.setText("Kitap Ekle");
        iv_back_button.setOnClickListener(this);
        cikisyapp=findViewById(R.id.cikisyap);
        cikisyapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent=new Intent(KitapEkleActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txtkitapadi = findViewById(R.id.kitapismi);
        txtyazarismi = findViewById(R.id.yazarismi);
        txtkitapadi = findViewById(R.id.kitapismi);
        txtraf = findViewById(R.id.kitapraf);
        txtfiyat = findViewById(R.id.fiyat);
        txtadet = findViewById(R.id.adet);
        btnyenikitapekle = findViewById(R.id.yenikitapekle);
    }

    private void insertdatabase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        btnyenikitapekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kitapadi = txtkitapadi.getText().toString();
                final String yazarismi = txtyazarismi.getText().toString();
                final String raf = txtraf.getText().toString();
                final int fiyat = Integer.parseInt(txtfiyat.getText().toString());
                final int adett = Integer.parseInt(txtadet.getText().toString());
                Kitapekle kitapeklejava = new Kitapekle(kitapadi, yazarismi, raf, fiyat, adett);
                reff = firebaseDatabase.getReference().child("Kullanıcılar").child(user_id);
                String key = reff.push().getKey();
                final DatabaseReference keylireff = firebaseDatabase.getReference().child("Kullanıcılar").child(user_id).child(key);
                keylireff.setValue(kitapeklejava).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(KitapEkleActivity.this, "başarılı", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(KitapEkleActivity.this, "hata", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
                Intent intent=new Intent(KitapEkleActivity.this,kutuphaneActivity.class);
                startActivity(intent);
                break;

        }

    }
}