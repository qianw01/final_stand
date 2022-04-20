package com.example.finalstand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    private List<DriverEntryModel> DriverList;

    DriverAdapter(List<DriverEntryModel> DriverList) {
        this.DriverList = DriverList;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverAdapter.ViewHolder holder, int position) {
        final DriverEntryModel DriverE = DriverList.get(position);

        holder.name.setText(DriverE.getName());
        holder.desc.setText(DriverE.getDesc());

    }

    @Override
    public int getItemCount() {
        return DriverList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardView;
        private TextView name;
        private TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView.findViewById(R.id.driverCard);
            name = cardView.findViewById(R.id.name);
            desc = cardView.findViewById(R.id.desc);
        }
    }
}
