package com.example.ktphanem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class UyeKayitActivity extends AppCompatActivity {
private Toolbar actionbarKayit;
private EditText txtusername,txtpassword,txtemail;
private Button btnuyekayit;
private FirebaseAuth auth;
private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_kayit);
        init();
        btnuyekayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yeni_uye_kayit();
            }
        });
    }

    private void init() {
        //Kontrollaerimiz oluşturmak için
        actionbarKayit= ((Toolbar) findViewById(R.id.actionbarkayit));
        setSupportActionBar(actionbarKayit);
        getSupportActionBar().setTitle("Uye Kayıt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth=FirebaseAuth.getInstance();
        txtusername=findViewById(R.id.txtusername);
        txtemail=findViewById(R.id.email);
        txtpassword=findViewById(R.id.parola);
        btnuyekayit=findViewById(R.id.btnuyekayit);

    }

    private void yeni_uye_kayit() {
         String username=txtusername.getText().toString();
        String email=txtemail.getText().toString();
        String password=txtpassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "E-mail alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Parola alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        toast=Toast.makeText(UyeKayitActivity.this, "Kullanıcı kaydı gerçekleşti", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,35,0);
                        View view=toast.getView();
                        view.setBackgroundColor(Color.rgb(174,241,156));
                        toast.show();
                        Intent loginIntent=new Intent(UyeKayitActivity.this,LoginActivity.class);
                        startActivity(loginIntent);
                        finish();//bunun sayesinde kullanıcı login ekranından uye kayıt ekranına gelmeyi engelleriz
                        }else {
                        toast=Toast.makeText(UyeKayitActivity.this, "Kayıt başarısız...", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,35,0);
                        View view=toast.getView();
                        view.setBackgroundColor(Color.RED);
                        toast.show();
                    }
                }
            });
        }
    }
}