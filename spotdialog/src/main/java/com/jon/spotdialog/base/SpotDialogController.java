package com.jon.spotdialog.base;

import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.jon.spotdialog.R;
import com.jon.spotdialog.SpotDialog;
import com.jon.spotdialog.listener.OnBindViewListener;
import com.jon.spotdialog.listener.OnViewClickListener;


/**
 * Created by liubin on 2018/8/21.
 */

public class SpotDialogController<A extends SpotListDialogBaseAdapter> {
    private FragmentManager fragmentManager;
    private int layoutRes;
    private int height;
    private int width;
    private float widthAspect;
    private float heightAspect;
    private float dimAmount;
    private int gravity;
    private String tag;
    private int[] clickIds;
    private boolean isCancelableOutside;
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private A adapter;
    private SpotListDialogBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
    //    private int orientation;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private int listViewId;
    private int dialogAnimationRes;
    private View dialogView;
    private DialogInterface.OnDismissListener onDismissListener;

    public SpotDialogController() {
    }


    //get
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getWidthAspect() {
        return widthAspect;
    }

    public float getHeightAspect() {
        return heightAspect;
    }

    public void setWidth(int mWidth) {
        this.width = mWidth;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public int getGravity() {
        return gravity;
    }

    public String getTag() {
        return tag;
    }

    public int[] getClickIds() {
        return clickIds;
    }

    public boolean isCancelableOutside() {
        return isCancelableOutside;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public View getDialogView() {
        return dialogView;
    }

    //列表
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(A adapter) {
        this.adapter = adapter;
    }

    public SpotListDialogBaseAdapter.OnAdapterItemClickListener getAdapterItemClickListener() {
        return adapterItemClickListener;
    }

    public void setAdapterItemClickListener(SpotListDialogBaseAdapter.OnAdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }

    public int getDialogAnimationRes() {
        return dialogAnimationRes;
    }

    public int getListViewId() {
        return listViewId;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration;
    }

    /**************************************************************************
     */
    public static class Params<A extends SpotListDialogBaseAdapter> {
        public FragmentManager mFragmentManager;
        public int mLayoutRes;
        public int mWidth;
        public int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        public float mWidthAspect = SpotDialog.DEFAULT_WIDTH_RATIO;
        public float mHeightAspect;
        public float mDimAmount = 0.6f;
        public int mGravity = Gravity.CENTER;
        public String mTag = "SpotDialog";
        public int[] ids;
        public boolean mIsCancelableOutside = true;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;
        public int mDialogAnimationRes = 0;//弹窗动画
        //列表
        public A adapter;
        public SpotListDialogBaseAdapter.OnAdapterItemClickListener adapterItemClickListener;
        public int listLayoutRes;
        //        public int orientation = LinearLayoutManager.VERTICAL;//默认RecyclerView的列表方向为垂直方向
        public RecyclerView.LayoutManager layoutManager;
        public RecyclerView.ItemDecoration itemDecoration;
        public int listViewId;
        public View mDialogView;//直接使用传入进来的View,而不需要通过解析Xml
        public DialogInterface.OnDismissListener mOnDismissListener;

        public Params() {
        }

        public void apply(SpotDialogController tController) {
            tController.fragmentManager = mFragmentManager;
            if (mLayoutRes > 0) {
                tController.layoutRes = mLayoutRes;
            }
            if (mDialogView != null) {
                tController.dialogView = mDialogView;
            }
            if (mWidth > 0) {
                tController.width = mWidth;
            }
            tController.height = mHeight;
            tController.widthAspect = mWidthAspect;
            tController.heightAspect = mHeightAspect;
            tController.dimAmount = mDimAmount;
            tController.gravity = mGravity;
            tController.tag = mTag;
            if (ids != null) {
                tController.clickIds = ids;
            }
            tController.isCancelableOutside = mIsCancelableOutside;
            tController.onViewClickListener = mOnViewClickListener;
            tController.onBindViewListener = bindViewListener;
            tController.onDismissListener = mOnDismissListener;
            tController.dialogAnimationRes = mDialogAnimationRes;

            if (adapter != null) {
                tController.setAdapter(adapter);
                if (listLayoutRes <= 0) {//使用默认的布局
                    tController.setLayoutRes(R.layout.dialog_recycler);
                } else {
                    tController.setLayoutRes(listLayoutRes);
                }
                tController.layoutManager = layoutManager;
                tController.listViewId = listViewId;
                tController.itemDecoration = itemDecoration;
            } else {
                if (tController.getLayoutRes() <= 0 && tController.getDialogView() == null) {
                    throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的xml布局!");
                }
            }
            if (adapterItemClickListener != null) {
                tController.setAdapterItemClickListener(adapterItemClickListener);
            }

//            if (tController.width <= 0) {
//                tController.width = (int) (SpotDialog.DEFAULT_WIDTH_RATIO * LiveApplication.getInstance().getScreenWidth());
//            }
        }
    }
}
