package com.momotombodevs.pgalante.bmi_calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.momotombodevs.pgalante.bmi_calculator.holders.UserViewHolder;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;
import com.momotombodevs.pgalante.bmi_calculator.R;

import java.util.ArrayList;

public class UserAdapter {
    ArrayList<UserModel> users;

    public UserAdapter(ArrayList<UserModel> users) {
        this.users = users;
    }

    public int getItemCount(){
        return users.size();
    }
}
