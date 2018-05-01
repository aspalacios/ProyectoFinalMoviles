package com.momotombodevs.pgalante.bmi_calculator.holders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.momotombodevs.pgalante.bmi_calculator.activities.DeleteUser;
import com.momotombodevs.pgalante.bmi_calculator.activities.UpdateUser;
import com.momotombodevs.pgalante.bmi_calculator.activities.MainActivity;
import com.momotombodevs.pgalante.bmi_calculator.R;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView id;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView password;
    private ImageButton delete;
    private ImageButton edit;

    private Context context;
    private Intent intent;

    public UserViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        setId((TextView) itemView.findViewById(R.id.userId));
        setFirstName((TextView) itemView.findViewById(R.id.userFirstName));
        setLastName((TextView) itemView.findViewById(R.id.userLastName));
        setEmail((TextView) itemView.findViewById(R.id.userEmail));
        setPassword((TextView) itemView.findViewById(R.id.userPassword));
        setDelete((ImageButton) itemView.findViewById(R.id.btnDelete));
        setEdit((ImageButton) itemView.findViewById(R.id.btnEdit));

    }

    public void setOnClickListeners(){
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDelete:
                AlertDialog dialog = deleteDialog();
                dialog.show();
                break;
            case R.id.btnEdit:
                intent = new Intent(context, UpdateUser.class);
                intent.putExtra("Id", id.getText().toString());
                intent.putExtra("First name", firstName.getText().toString());
                intent.putExtra("Last name", lastName.getText().toString());
                intent.putExtra("Email", email.getText().toString());
                intent.putExtra("Password", password.getText().toString());
                context.startActivity(intent);
                break;
        }
    }

    public AlertDialog deleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Delete Product")
                .setMessage("The element will be deleted")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent = new Intent(context, DeleteUser.class);
                                intent.putExtra("Id", id.getText().toString());
                                context.startActivity(intent);
                            }
                        })
                .setNegativeButton("CANCELAR",
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

    public TextView getFirstName() {
        return firstName;
    }

    public void setFirstName(TextView firstName) {
        this.firstName = firstName;
    }

    public TextView getLastName() {
        return lastName;
    }

    public void setLastName(TextView lastName) {
        this.lastName = lastName;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public TextView getPassword() {
        return password;
    }

    public void setPassword(TextView password) {
        this.password = password;
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
