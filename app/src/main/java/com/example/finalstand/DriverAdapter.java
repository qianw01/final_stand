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

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    private List<DriverEntryModel> driverList;
    private final ItemOnClickListener<DriverEntryModel> listener;

    DriverAdapter(List<DriverEntryModel> DriverList, ItemOnClickListener<DriverEntryModel> l) {
        this.driverList = DriverList;
        this.listener = l;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.ViewHolder holder, int position) {
        final DriverEntryModel driverE = driverList.get(position);

        holder.name.setText(driverE.getName());
        holder.team.setText("Team: " + driverE.getTeam());
        holder.number.setText("Number: " + driverE.getNumber());

        ViewCompat.setTransitionName(holder.name, driverE.getName());
        ViewCompat.setTransitionName(holder.team, driverE.getTeam());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(holder.getAdapterPosition(), driverE, holder.name, holder.team);
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardView;
        private TextView name;
        private TextView team;
        private TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView.findViewById(R.id.driverCard);
            name = cardView.findViewById(R.id.name);
            team = cardView.findViewById(R.id.team);
            number = cardView.findViewById(R.id.number);
        }
    }
}
