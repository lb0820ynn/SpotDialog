package com.jon.spotdialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.jon.spotdialog.base.BindViewHolder;
import com.jon.spotdialog.listener.OnViewClickListener;
import com.jon.spotdialog.utils.SpotDialogUtil;

/**
 * Created by liubin on 2018/10/30.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_basic).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_basic:
                SpotDialogUtil.showDialog(getSupportFragmentManager(), Gravity.CENTER, "描述描述描述", "标题标题", "", true, new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, SpotDialog dialog) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }
}
