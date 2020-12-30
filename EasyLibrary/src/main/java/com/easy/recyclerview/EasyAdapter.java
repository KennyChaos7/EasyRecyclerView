package com.easy.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
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
 * @Description:    如果要做VH为<? extentds EasyViewHolder>的话需要做类型擦除, 然后取出基类EasyViewHolder
 */
public abstract class EasyAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {
    private @LayoutRes int itemLayoutId = -1;
    private EasyItemListener itemListener = null;
//    private EasyViewHolder viewHolder = null;
    private final List<T> arrayList = Collections.synchronizedList(new ArrayList<>());//CopyOnWriteArrayList

    public EasyAdapter(@LayoutRes int itemLayoutId,@NonNull EasyAdapter.EasyItemListener listener) {
        this.itemLayoutId = itemLayoutId;
        this.itemListener = listener;
    }

    public List<T> getDataList() {
        return arrayList;
    }

    public T getData(int position) {
        if (position > -1 && position < arrayList.size()) {{
            return arrayList.get(position);
        }}
        return null;
    }

    public void setNewData(List<T> newData) {
        arrayList.clear();
        arrayList.addAll(newData);
        notifyDataSetChanged();
    }

    public void addNewData(List<T> newData) {
        arrayList.addAll(newData);
        notifyDataSetChanged();
    }

    public void addNewData(T t) {
        arrayList.add(t);
        notifyDataSetChanged();
    }

    /**
     * 更新数据
     * TODO 利用DiffUtil处理数据
     * @param newData
     */
    public void diffUpdate(List<T> newData) {

    }

    public void replace(int position, T t) {
        if (position > -1 && position< arrayList.size() && arrayList.get(position) != null) {
            arrayList.set(position, t);
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public EasyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewHolder == null)
//            viewHolder = new EasyViewHolder(
//                LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false)
//            );
        return new EasyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false)
        );
    }

    public abstract void onEasyBindViewHolder(@NonNull EasyViewHolder holder, int position);

    @Override
    public void onBindViewHolder(@NonNull EasyViewHolder holder, int position)
    {
        onEasyBindViewHolder(holder, position);
        bindClickListener(holder, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void bindClickListener(EasyViewHolder e, int position) {
        e.itemView.setOnClickListener(v -> {itemListener.onItemClick(position, e.itemView);});
    }

    public interface EasyItemListener {
        void onItemClick(int position, View view);
    }
}
