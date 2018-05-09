package com.momotombodevs.pgalante.bmi_calculator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.models.BodyModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class BodyFromDatabaseAdapter extends RecyclerView.Adapter<BodyFromDatabaseAdapter.ViewHolder> {
    private RealmResults<BodyModel> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public TextView height;
        public TextView weight;
        public LinearLayout item;
        public Context context;

        public ViewHolder(View view, Context _context) {
            super(view);
            id = view.findViewById(R.id.idBody);
            height = view.findViewById(R.id.height);
            weight = view.findViewById(R.id.weight);
            item = view.findViewById(R.id.itemBody);
            context = _context;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BodyFromDatabaseAdapter(RealmResults<BodyModel> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BodyFromDatabaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_body, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new BodyFromDatabaseAdapter.ViewHolder(view, parent.getContext());
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final BodyFromDatabaseAdapter.ViewHolder holder, final int position) {
        final BodyModel bodyModel = mDataset.get(position);

        Log.i("id", bodyModel.getId());
        Log.i("height", bodyModel.getHeight());
        Log.i("weight", bodyModel.getWeight());

        holder.id.setText(bodyModel.getId());
        holder.height.setText(bodyModel.getHeight());
        holder.weight.setText(bodyModel.getWeight());


        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new MaterialDialog.Builder(holder.context)
                        .content("Desea borrar este registro.")
                        .positiveText("Borrar")
                        .negativeText("No")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(MaterialDialog dialog, DialogAction which) {
                                remoteItemFromDatabase(mDataset.get(position));
                            }
                        })
                        .show();
                return true;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void remoteItemFromDatabase(BodyModel bodyModel) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        bodyModel.deleteFromRealm();
        realm.commitTransaction();
    }
}
