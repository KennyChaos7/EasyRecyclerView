package com.easy.recyclerview;

import android.view.View;

import androidx.annotation.Nullable;

import com.scwang.smart.refresh.layout.api.RefreshFooter;

public interface BaseEasyFooter<T extends RefreshFooter> extends OnConvertListener<T> {

    void setListener(@Nullable BaseOnLoadMoreListener listener);

    interface BaseOnLoadMoreListener extends BaseOnRLListener {
        int listenerType = TYPE_LOADMOEW;

        void onPullingToLoad(View v);

        void onLoadMoreStart(View v);

        void onLoading(View v);
    }
}
