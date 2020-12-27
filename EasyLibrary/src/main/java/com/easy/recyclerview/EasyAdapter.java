package com.easy.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Author: ou
 * @Date:   2020-12-18 0018 14:17
 * @Description:
 */
public class EasyAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {
    private @LayoutRes int itemLayoutId = -1;
    private EasyItemListener itemListener = null;
    private EasyViewHolder viewHolder = null;
    private final List<T> arrayList = Collections.synchronizedList(new ArrayList<>());//CopyOnWriteArrayList

    public EasyAdapter(@LayoutRes int itemLayoutId,@NonNull EasyAdapter.EasyItemListener listener) {
        this.itemLayoutId = itemLayoutId;
        this.itemListener = listener;
    }

    /**
     * 更新数据
     * TODO 利用DiffUtil处理数据
     * @param newData
     */
    public void update(List<T> newData) {
        arrayList.clear();
        arrayList.addAll(newData);
        notifyDataSetChanged();
    }

    public void update(T t) {
        arrayList.add(t);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EasyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewHolder == null)
            viewHolder = new EasyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, null)
            );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EasyViewHolder holder, int position) {
        itemListener.bindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface EasyItemListener {
        void bindViewHolder(EasyViewHolder holder, int position);
    }
}
