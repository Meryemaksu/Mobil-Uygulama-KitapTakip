package com.example.ktphanem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Toolbar actionbarlogin;
    private EditText txtemail,txtpassword;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnloginclick();
            }
        });
    }
    private void init() {
        actionbarlogin= ((Toolbar) findViewById(R.id.actionbarlogin));
        setSupportActionBar(actionbarlogin);
        getSupportActionBar().setTitle("Giriş yap");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth=FirebaseAuth.getInstance();
        currentUser=auth.getCurrentUser();
        txtemail=findViewById(R.id.emaillogin);
        txtpassword=findViewById(R.id.parolalogin);
        btnlogin=findViewById(R.id.girisyaplogin);
    }
    private void btnloginclick() {

        String email=txtemail.getText().toString();
        String password=txtpassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "E-mail boş bırakalamaz", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Parola alanı boş bırakılamaz", Toast.LENGTH_SHORT).show();
        }else
        {
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                        Intent kutuphaneactivity=new Intent(LoginActivity.this,kutuphaneActivity.class);
                        startActivity(kutuphaneactivity);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "E-mail veya parola yanlış", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


}