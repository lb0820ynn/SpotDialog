package com.jon.spotdialog;

import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jon.spotdialog.base.SpotListDialogBaseAdapter;
import com.jon.spotdialog.listener.OnBindViewListener;


/**
 * Created by liubin on 2018/9/6.
 */

public class SpotListDialog extends SpotDialog {

    @Override
    protected void initView(View view) {
        super.initView(view);
        if (mController.getAdapter() != null) {
            RecyclerView recyclerView = view.findViewById(mController.getListViewId() > 0 ? mController.getListViewId() : R.id.recycler_view);
            if (recyclerView == null) {
                throw new IllegalArgumentException("list dialog recyclerView can not be null");
            }
            mController.getAdapter().setDialog(this);

            if (mController.getLayoutManager() != null) {
                recyclerView.setLayoutManager(mController.getLayoutManager());
            } else {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
            }
            if (mController.getItemDecoration() != null) {
                recyclerView.addItemDecoration(mController.getItemDecoration());
            }
            recyclerView.setAdapter(mController.getAdapter());
            mController.getAdapter().notifyDataSetChanged();
            if (mController.getAdapterItemClickListener() != null) {
                mController.getAdapter().setOnAdapterItemClickListener(mController.getAdapterItemClickListener());
            }
        }
    }

    public static class Builder extends SpotDialog.Builder {

        public Builder(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public SpotListDialog.Builder setListLayoutRes(@LayoutRes int layoutRes, RecyclerView.LayoutManager layoutManager) {
            params.listLayoutRes = layoutRes;
            params.layoutManager = layoutManager;
            return this;
        }

        public SpotListDialog.Builder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            params.itemDecoration = itemDecoration;
            return this;
        }

        public SpotListDialog.Builder setListViewId(int listViewId) {
            params.listViewId = listViewId;
            return this;
        }

        @Override
        public SpotListDialog.Builder setOnBindViewListener(OnBindViewListener listener) {
            super.setOnBindViewListener(listener);
            return this;
        }

        public <A extends SpotListDialogBaseAdapter> SpotListDialog.Builder setAdapter(A adapter) {
            params.adapter = adapter;
            return this;
        }

        public SpotListDialog.Builder setOnAdapterItemClickListener(SpotListDialogBaseAdapter.OnAdapterItemClickListener listener) {
            params.adapterItemClickListener = listener;
            return this;
        }

        @Override
        public SpotListDialog create() {
            SpotListDialog dialog = new SpotListDialog();
            params.apply(dialog.mController);
            return dialog;
        }
    }
}
