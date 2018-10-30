package com.jon.spotdialog.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by liubin on 2018/10/30.
 */
public abstract class SpotBaseDialogFragment extends DialogFragment {

    public static final String TAG = "SpotliteBaseDialogFragment";
    private static final float DEFAULT_DIMAMOUNT = 0.6F;
    protected static final float DEFAULT_WIDTH_RATIO = 0.8F;

    protected abstract int getLayoutRes();

    protected abstract void initView(View view);

    protected abstract View getDialogView();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutRes() > 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        if (getDialogView() != null) {
            view = getDialogView();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(isCancelableOutside());
        if (dialog.getWindow() != null && getDialogAnimationRes() > 0) {
            dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = getDialogWidth();
            layoutParams.height = getDialogHeight();
            //透明度
            layoutParams.dimAmount = getDimAmount();
            //位置
            layoutParams.gravity = getGravity();
            window.setAttributes(layoutParams);
        }
    }

    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    //默认高为包裹内容
    public int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public int getDialogWidth() {
        return (int) (DEFAULT_WIDTH_RATIO * getScreenWidth(getActivity()));
    }

    public float getWidthRatio() {
        return DEFAULT_WIDTH_RATIO;
    }

    //默认透明度
    public float getDimAmount() {
        return DEFAULT_DIMAMOUNT;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, getFragmentTag());
    }

    protected boolean isCancelableOutside() {
        return true;
    }

    //获取弹窗显示动画,子类实现
    protected int getDialogAnimationRes() {
        return 0;
    }


    public interface OnDialogDismissListener {
        void onDismiss();
    }

    OnDialogDismissListener mDismissListener;

    public SpotBaseDialogFragment setOnDismissListener(OnDialogDismissListener listener) {
        mDismissListener = listener;
        return this;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    public static final int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static final int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
