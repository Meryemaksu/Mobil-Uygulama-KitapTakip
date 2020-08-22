package com.example.ktphanem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView listelefirabe;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        listelefirabe=itemView.findViewById(R.id.listelekitapadi);
    }
}
