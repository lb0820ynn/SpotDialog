package com.jon.spotdialog;

import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jon.spotdialog.base.BindViewHolder;
import com.jon.spotdialog.base.SpotBaseDialogFragment;
import com.jon.spotdialog.base.SpotDialogController;
import com.jon.spotdialog.listener.OnBindViewListener;
import com.jon.spotdialog.listener.OnViewClickListener;


/**
 * Created by liubin on 2018/8/16.
 */
public class SpotDialog extends SpotBaseDialogFragment {

    protected SpotDialogController mController;

    public SpotDialog() {
        mController = new SpotDialogController();
    }

    @Override
    protected void initView(View view) {
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (mController.getClickIds() != null && mController.getClickIds().length > 0) {
            for (int id : mController.getClickIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        if (mController.getOnBindViewListener() != null) {
            mController.getOnBindViewListener().bindView(viewHolder);
        }
    }


    @Override
    protected int getLayoutRes() {
        return mController.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return mController.getDialogView();
    }

    @Override
    public int getGravity() {
        return mController.getGravity();
    }

    @Override
    public float getDimAmount() {
        return mController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return mController.getHeightAspect() > 0f ? (int) (mController.getHeightAspect() * getScreenHeight(getContext())) : mController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return mController.getWidthAspect() > 0f ? (int) (mController.getWidthAspect() * getScreenWidth(getContext())) : mController.getWidth();
    }

    @Override
    public String getFragmentTag() {
        return mController.getTag();
    }

    public OnViewClickListener getOnViewClickListener() {
        return mController.getOnViewClickListener();
    }

    @Override
    protected boolean isCancelableOutside() {
        return mController.isCancelableOutside();
    }

    @Override
    protected int getDialogAnimationRes() {
        return mController.getDialogAnimationRes();
    }

    public SpotDialog show() {
        FragmentTransaction ft = mController.getFragmentManager().beginTransaction();
        ft.add(this, mController.getTag());
        ft.commitAllowingStateLoss();
        return this;
    }


    public static class Builder {

        protected SpotDialogController.Params params;

        public Builder(FragmentManager fragmentManager) {
            params = new SpotDialogController.Params();
            params.mFragmentManager = fragmentManager;
        }

        /**
         * 传入弹窗xmL布局文件
         *
         * @param layoutRes
         * @return
         */
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        /**
         * 直接传入控件
         *
         * @param view
         * @return
         */
        public Builder setDialogView(View view) {
            params.mDialogView = view;
            return this;
        }

        /**
         * 设置弹窗宽度(单位:像素)
         *
         * @param widthPx
         * @return
         */
        public Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置弹窗高度(px)
         *
         * @param heightPx
         * @return
         */
        public Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public Builder setScreenWidthAspect(float widthAspect) {
            params.mWidthAspect = widthAspect;
            return this;
        }

        /**
         * 设置弹窗高度是屏幕高度的比例 0 -1
         */
        public Builder setScreenHeightAspect(float heightAspect) {
            params.mHeightAspect = heightAspect;
            return this;
        }

        /**
         * 设置弹窗在屏幕中显示的位置
         *
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        /**
         * 设置弹窗在弹窗区域外是否可以取消
         *
         * @param cancel
         * @return
         */
        public Builder setCancelableOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        /**
         * 弹窗dismiss时监听回调方法
         *
         * @param dismissListener
         * @return
         */
        public Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
            return this;
        }


        /**
         * 设置弹窗背景透明度(0-1f)
         *
         * @param dim
         * @return
         */
        public Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        /**
         * 通过回调拿到弹窗布局控件对象
         *
         * @param listener
         * @return
         */
        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        /**
         * 添加弹窗控件的点击事件
         *
         * @param ids 传入需要点击的控件id
         * @return
         */
        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        /**
         * 弹窗控件点击回调
         *
         * @param listener
         * @return
         */
        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        /**
         * 设置弹窗动画
         *
         * @param animationRes
         * @return
         */
        public Builder setDialogAnimationRes(int animationRes) {
            params.mDialogAnimationRes = animationRes;
            return this;
        }

        /**
         * 真正创建TDialog对象实例
         *
         * @return
         */
        public SpotDialog create() {
            SpotDialog dialog = new SpotDialog();
            params.apply(dialog.mController);
            return dialog;
        }
    }

}
