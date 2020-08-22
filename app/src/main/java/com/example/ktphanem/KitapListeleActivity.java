package com.example.ktphanem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;


public class KitapListeleActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<Kitapekle> options;
    private Toolbar actionbarlogin;
    private FirebaseRecyclerAdapter<Kitapekle, FirebaseViewHolder> adapter;
    private DatabaseReference reff;
    private FirebaseDatabase database;
    private ArrayList<Kitapekle> arrayList;
    private Button cikisyapp;
    private FirebaseAuth auth;
   // private SearchView search;
   private EditText search;
    private String user_id;
    private String searchh;
    ImageView iv_back_button;
    TextView tv_title;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_listele);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tv_title = findViewById(R.id.tv_title);
        iv_back_button = findViewById(R.id.iv_back_button);
        tv_title.setOnClickListener(this);
        tv_title.setText("Kitap Listele");
        iv_back_button.setOnClickListener(this);

        cikisyapp = findViewById(R.id.cikisyap);
        cikisyapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(KitapListeleActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        arrayList = new ArrayList<Kitapekle>();
        auth = FirebaseAuth.getInstance();
        user_id = auth.getCurrentUser().getUid();
        reff = FirebaseDatabase.getInstance().getReference().child("Kullanıcılar").child(user_id);
        reff.keepSynced(true);

        search=findViewById(R.id.search);
        search.addTextChangedListener(this);
        options = new FirebaseRecyclerOptions.Builder<Kitapekle>().setQuery(reff, Kitapekle.class).build();

        adapterQuery(options);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_button:
                Intent intent = new Intent(KitapListeleActivity.this, kutuphaneActivity.class);
                startActivity(intent);
                break;
        }

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchh=search.getText().toString();
        firebasesearch(searchh);
    }

    private void firebasesearch(String searchh) {

        Query firebaseSearchQuery = reff.orderByChild("kitapadi").startAt(searchh).endAt(searchh + "\uf8ff");
        options =
                new FirebaseRecyclerOptions.Builder<Kitapekle>()
                        .setQuery(firebaseSearchQuery, Kitapekle.class)
                        .setLifecycleOwner(this)
                        .build();
        adapterQuery(options);

    }

    private void adapterQuery(FirebaseRecyclerOptions<Kitapekle> options) {
        adapter = new FirebaseRecyclerAdapter<Kitapekle, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder firebaseViewHolder, int i, @NonNull final Kitapekle kitapekle) {
                firebaseViewHolder.listelefirabe.setText(kitapekle.getKitapadi());

                firebaseViewHolder.listelefirabe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(KitapListeleActivity.this, DetayActivity.class);
                        intent.putExtra("Kitapadi", kitapekle.getKitapadi());
                        intent.putExtra("Yazaradi", kitapekle.getYazaradi());
                        intent.putExtra("Rafyeri", kitapekle.getRafyeri());
                        intent.putExtra("Adet", String.valueOf(kitapekle.getAdett()));
                        intent.putExtra("Fiyat", String.valueOf(kitapekle.getFiyat()));
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kitaplistele, parent, false);
                return new FirebaseViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}