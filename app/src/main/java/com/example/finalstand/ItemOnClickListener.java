package com.example.finalstand;

import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.card.MaterialCardView;

public interface ItemOnClickListener<T> {
    void onItemClick(int pos, T item, TextView textView);
}
