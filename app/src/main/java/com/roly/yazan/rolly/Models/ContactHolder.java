package com.roly.yazan.rolly.Models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.roly.yazan.rolly.R;

public class ContactHolder extends RecyclerView.ViewHolder {
    public TextView nameTextview;
    public TextView companyTextview;
    public TextView notesTextview;
    public TextView usefullnessTextview;


    public ContactHolder(View v) {
        super(v);
        nameTextview = v.findViewById(R.id.name);
    }

    public void bindView(final ContactModel contact) {
        nameTextview.setText(contact.getName());
    }
}
