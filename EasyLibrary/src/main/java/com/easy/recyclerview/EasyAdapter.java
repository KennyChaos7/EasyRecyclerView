package com.easy.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
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
    private EasyAdapter<T> adapter = null;
    private @LayoutRes int itemLayoutId = -1;
    private EasyItemListener itemListener = null;
//    private EasyViewHolder viewHolder = null;
    private final List<T> arrayList = Collections.synchronizedList(new ArrayList<>());//CopyOnWriteArrayList

    public EasyAdapter(@LayoutRes int itemLayoutId,@NonNull EasyAdapter.EasyItemListener listener) {
        adapter = this;
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
     * 利用DiffUtil处理数据
     * @param newData
     * @param callback
     */
    public void diffUpdate(List<T> newData, DiffVerifyCallback<T> callback) {
        if (arrayList.size() > 0 && newData.size() > 0) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return arrayList.size();
                }

                @Override
                public int getNewListSize() {
                    return newData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return callback.isSameItem(arrayList.get(oldItemPosition), newData.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return callback.isSameContent(arrayList.get(oldItemPosition), newData.get(newItemPosition));
                }
            }, false);
            diffResult.dispatchUpdatesTo(new ListUpdateCallback() {
                @Override
                public void onInserted(int position, int count) {
                    adapter.notifyItemRangeInserted(position, count);
                }

                @Override
                public void onRemoved(int position, int count) {
                    adapter.notifyItemRangeRemoved(position, count);
                }

                @Override
                public void onMoved(int fromPosition, int toPosition) {
                    adapter.notifyItemMoved(fromPosition, toPosition);
                }

                @Override
                public void onChanged(int position, int count, @Nullable Object payload) {
                    adapter.notifyItemRangeChanged(position, count, payload);
                }
            });
        }
        else if (arrayList.size() == 0 && newData.size() > 0) {
            addNewData(newData);
        }
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

    public interface DiffVerifyCallback<T> {
        boolean isSameItem(T _old, T _new);
        boolean isSameContent(T _old, T _new);
    }
}
