package com.momotombodevs.pgalante.bmi_calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.BodyModel;

import java.util.List;

public class BodiesAdapter extends RecyclerView.Adapter<BodiesAdapter.ViewHolder> {
    private List<BodyModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public TextView weight;
        public TextView height;

        public ViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.idBody);
            weight = view.findViewById(R.id.weight);
            height = view.findViewById(R.id.height);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BodiesAdapter(List<BodyModel> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BodiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_body, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BodyModel bodyModel = mDataset.get(position);

        Log.i("id", bodyModel.getId());
        Log.i("height", bodyModel.getHeight());
        Log.i("weight", bodyModel.getWeight());

        holder.id.setText(bodyModel.getId());
        holder.height.setText(bodyModel.getHeight());
        holder.weight.setText(String.valueOf(bodyModel.getWeight()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
