package com.momotombodevs.pgalante.bmi_calculator.holders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.momotombodevs.pgalante.bmi_calculator.R;
import com.momotombodevs.pgalante.bmi_calculator.activities.DeleteAdvice;
import com.momotombodevs.pgalante.bmi_calculator.activities.UpdateAdvice;

public class AdviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView id;
    private TextView title;
    private TextView description;
    private TextView category;
    private ImageButton delete;
    private ImageButton edit;

    private Context context;
    private Intent intent;

    public AdviceViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        setId((TextView) itemView.findViewById(R.id.adviceId));
        setTitle((TextView) itemView.findViewById(R.id.titleAdvice));
        setDescription((TextView) itemView.findViewById(R.id.descriptionAdvice));
        setCategory((TextView) itemView.findViewById(R.id.categoryAdvice));
        setDelete((ImageButton) itemView.findViewById(R.id.btnDelete));
        setEdit((ImageButton) itemView.findViewById(R.id.btnEdit));

    }

    public void setOnClickListeners() {
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDelete:
                AlertDialog dialog = deleteDialog();
                dialog.show();
                break;
            case R.id.btnEdit:
                intent = new Intent(context, UpdateAdvice.class);
                intent.putExtra("Id", id.getText().toString());
                intent.putExtra("Título", title.getText().toString());
                intent.putExtra("Descripción", description.getText().toString());
                intent.putExtra("Categoría", description.getText().toString());
                context.startActivity(intent);
                break;
        }
    }

    public AlertDialog deleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Eliminar Consejo")
                .setMessage("El elemento será eliminado")
                .setPositiveButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent = new Intent(context, DeleteAdvice.class);
                                intent.putExtra("Id", id.getText().toString());
                                context.startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        return builder.create();
    }

    public TextView getId() {
        return id;
    }

    public void setId(TextView id) {
        this.id = id;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getCategory() {
        return category;
    }

    public void setCategory(TextView category) {
        this.category = category;
    }

    public ImageButton getDelete() {
        return delete;
    }

    public void setDelete(ImageButton delete) {
        this.delete = delete;
    }

    public ImageButton getEdit() {
        return edit;
    }

    public void setEdit(ImageButton edit) {
        this.edit = edit;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}