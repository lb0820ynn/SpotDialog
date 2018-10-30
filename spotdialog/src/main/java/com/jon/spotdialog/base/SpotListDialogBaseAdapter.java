package com.jon.spotdialog.base;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jon.spotdialog.SpotDialog;

import java.util.List;

/**
 * Created by liubin on 2018/9/6.
 */

public abstract class SpotListDialogBaseAdapter<T> extends RecyclerView.Adapter<BindViewHolder> {
    private final int layoutRes;
    private List<T> datas;
    private OnAdapterItemClickListener adapterItemClickListener;
    private SpotDialog mDialog;

    protected abstract void onBind(BindViewHolder holder, int position, T t);

    public SpotListDialogBaseAdapter(@LayoutRes int layoutRes, List<T> datas) {
        this.layoutRes = layoutRes;
        this.datas = datas;
    }

    @Override
    public BindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false));
    }

    @Override
    public void onBindViewHolder(final BindViewHolder holder, final int position) {
        onBind(holder, position, datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterItemClickListener.onItemClick(holder, position, datas.get(position), mDialog);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setDialog(SpotDialog dialog) {
        this.mDialog = dialog;
    }

    public interface OnAdapterItemClickListener<T> {
        void onItemClick(BindViewHolder holder, int position, T t, SpotDialog dialog);
    }

    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener) {
        this.adapterItemClickListener = listener;
    }
}
