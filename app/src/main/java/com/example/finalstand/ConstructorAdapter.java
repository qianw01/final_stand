package com.example.finalstand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ConstructorAdapter extends RecyclerView.Adapter<ConstructorAdapter.ViewHolder> {

    private List<ConstructorEntryModel> constructorList;
    private final ItemOnClickListener<ConstructorEntryModel> listener;

    ConstructorAdapter(List<ConstructorEntryModel> ConstructorList, ItemOnClickListener<ConstructorEntryModel> l) {
        this.constructorList = ConstructorList;
        this.listener = l;
    }

    @NonNull
    @Override
    public ConstructorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.constructor_card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstructorAdapter.ViewHolder holder, int position) {
        final ConstructorEntryModel constructorE = constructorList.get(position);

        holder.name.setText(constructorE.getName());

        ViewCompat.setTransitionName(holder.name, constructorE.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition(), constructorE, holder.name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return constructorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardView;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView.findViewById(R.id.constructorCard);
            name = cardView.findViewById(R.id.name);
        }
    }
}
