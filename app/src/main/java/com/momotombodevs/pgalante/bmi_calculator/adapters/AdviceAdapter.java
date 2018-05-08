package com.momotombodevs.pgalante.bmi_calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momotombodevs.pgalante.bmi_calculator.holders.AdviceViewHolder;
import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;
import com.momotombodevs.pgalante.bmi_calculator.R;

import java.util.ArrayList;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceViewHolder> {
    ArrayList<AdviceModel> advices;

    public AdviceAdapter(ArrayList<AdviceModel> advices) {
        this.advices = advices;
    }

    @Override
    public AdviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advice, parent, false);
        return new AdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdviceViewHolder holder, int position) {
        AdviceModel advice = advices.get(position);

        holder.getId().setText(advice.getId());
        holder.getTitle().setText(advice.getTitle());
        holder.getDescription().setText(advice.getDescription());
        holder.getCategory().setText(advice.getCategory());
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return advices.size();
    }
}