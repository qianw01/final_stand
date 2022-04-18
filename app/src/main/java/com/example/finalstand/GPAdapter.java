package com.example.finalstand;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class GPAdapter extends RecyclerView.Adapter<GPAdapter.ViewHolder> {

    private List<GPEntryModel> GPList;

    GPAdapter(List<GPEntryModel> GPList) {
        this.GPList = GPList;
    }

    @NonNull
    @Override
    public GPAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GPAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.todoCardView);
        }
    }
}
