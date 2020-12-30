package com.easy.recyclerview;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//TODO 支持更多简易设置
public class EasyViewHolder extends RecyclerView.ViewHolder {
    //  线程安全, 自动扩容
    private SparseArray<View> views = new SparseArray<>();

    public EasyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public EasyViewHolder setText(@IdRes int id, String string) {
        TextView textView = getTargetView(id);
        if (textView != null) textView.setText(string);
        return this;
    }

    public EasyViewHolder setTextColor(@IdRes int id, @ColorInt int colorId) {
        TextView textView = getTargetView(id);
        if (textView != null) textView.setTextColor(colorId);
        return this;
    }

    public EasyViewHolder setBackgroundColor(@IdRes int id, @ColorInt int colorId) {
        View view = getTargetView(id);
        if (view != null) view.setBackgroundColor(colorId);
        return this;
    }

    public EasyViewHolder setBackgroundResource(@IdRes int id, @DrawableRes int drawableId) {
        View view = getTargetView(id);
        if (view != null) view.setBackgroundResource(drawableId);
        return this;
    }

    public EasyViewHolder setVisible(@IdRes int id, boolean isVisible) {
        View view = getTargetView(id);
        if (view != null) view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public EasyViewHolder setGone(@IdRes int id, boolean isHide) {
        View view = getTargetView(id);
        if (view != null) view.setVisibility(isHide ? View.GONE : View.VISIBLE);
        return this;
    }

    public EasyViewHolder bindOnClickListener(@IdRes int id, View.OnClickListener onClickListener) {
        View view = getTargetView(id);
        if (view != null) view.setOnClickListener(onClickListener);
        return this;
    }

    public View findView(@IdRes int id) {
        return this.itemView.findViewById(id);
    }

    /**
     * 不用每次都去findView
     * @param id
     * @param <T>
     * @return
     */
    private <T extends View> T getTargetView(@IdRes int id) {
        View view = views.get(id);
        if (view == null)
        {
            view = this.itemView.findViewById(id);
            if (view != null) {
                views.put(id, view);
            }
        }
        return (T) view;
    }
}