package com.momotombodevs.pgalante.bmi_calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momotombodevs.pgalante.bmi_calculator.holders.UserViewHolder;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;
import com.momotombodevs.pgalante.bmi_calculator.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    ArrayList<UserModel> users;

    public UserAdapter(ArrayList<UserModel> users) {
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserModel user = users.get(position);

        holder.getId().setText(user.getId());
        holder.getFirstName().setText("First name: " + user.getFirstName());
        holder.getLastName().setText("Last name: " + user.getLastName());
        holder.getEmail().setText("Email: " + user.getEmail());
        holder.getPassword().setText("Password: " + user.getPassword());
        holder.setOnClickListeners();
    }

    @Override
    public int getItemCount(){
        return users.size();
    }
}
