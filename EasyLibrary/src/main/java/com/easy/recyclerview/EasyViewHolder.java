package com.easy.recyclerview;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

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

    public void setText(@IdRes int id, String string) {
        ((TextView)getTargetView(id)).setText(string);
    }

    public void setVisible(@IdRes int id, boolean isVisible) {
        getTargetView(id).setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
    }

    public void setHide(@IdRes int id, boolean isHide) {
        getTargetView(id).setVisibility(isHide ? View.GONE : View.VISIBLE);
    }


    private <T extends View> T getTargetView(@IdRes int id) {
        View view = views.get(id);
        if (view == null)
        {
            view = this.itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }
}