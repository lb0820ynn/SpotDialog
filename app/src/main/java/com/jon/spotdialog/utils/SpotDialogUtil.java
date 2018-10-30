package com.jon.spotdialog.utils;

import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jon.spotdialog.R;
import com.jon.spotdialog.SpotDialog;
import com.jon.spotdialog.base.BindViewHolder;
import com.jon.spotdialog.listener.OnBindViewListener;
import com.jon.spotdialog.listener.OnViewClickListener;

/**
 * Created by liubin on 2018/10/30.
 */
public class SpotDialogUtil {

    public static SpotDialog showDialog(final FragmentManager fragmentManager, int gravity, final CharSequence msg, final String title, final String yes, boolean cancel,
                                        final OnViewClickListener listener) {
        SpotDialog spotDialog = new SpotDialog.Builder(fragmentManager)
                .setGravity(gravity)
                .setCancelableOutside(cancel)
                .setLayoutRes(R.layout.dialog_simple)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        TextView tvTitle = viewHolder.getView(R.id.tv_title);
                        TextView tvContent = viewHolder.getView(R.id.tv_content);
                        TextView tvConfirm = viewHolder.getView(R.id.btn_confirm);
                        if (!TextUtils.isEmpty(title)) {
                            tvTitle.setVisibility(View.VISIBLE);
                            tvTitle.setText(title);
                        } else {
                            tvTitle.setVisibility(View.GONE);
                        }
                        tvContent.setText(msg);
                        if (!TextUtils.isEmpty(yes)) {
                            tvConfirm.setText(yes);
                        }
                    }
                })
                .addOnClickListener(R.id.btn_confirm, R.id.btn_cancel)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, SpotDialog dialog) {
                        switch (view.getId()) {
                            case R.id.btn_confirm:
                                if (listener != null) {
                                    listener.onViewClick(viewHolder, view, dialog);
                                } else {
                                    dialog.dismiss();
                                }
                                break;
                            case R.id.btn_cancel:
                                dialog.dismiss();
                                break;
                        }
                    }
                }).create();

        spotDialog.show();
        return spotDialog;
    }
}
