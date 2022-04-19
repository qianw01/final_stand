package com.example.finalstand;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GPAdapter.ViewHolder holder, int position) {
        final GPEntryModel GPE = GPList.get(position);

        holder.title.setText(GPE.getTitle());
        holder.desc.setText(GPE.getDesc());
    }

    @Override
    public int getItemCount() {
        return GPList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialCardView cardView;
        private TextView title;
        private TextView desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (MaterialCardView) itemView.findViewById(R.id.card);
            title = cardView.findViewById(R.id.title);
            desc = cardView.findViewById(R.id.desc);
        }
    }
}
