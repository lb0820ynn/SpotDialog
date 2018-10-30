package com.jon.spotdialog.listener;

import android.view.View;

import com.jon.spotdialog.SpotDialog;
import com.jon.spotdialog.base.BindViewHolder;


public interface OnViewClickListener {
    void onViewClick(BindViewHolder viewHolder, View view, SpotDialog dialog);
}
