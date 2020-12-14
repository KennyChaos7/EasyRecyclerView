package com.easy.recyclerview;

import android.view.View;

import androidx.annotation.Nullable;

public interface BaseEasyHeader<T extends BaseInnerHeader> extends OnConvertListener<T> {

    void setListener(@Nullable BaseOnRefreshListener listener);

    interface BaseOnRefreshListener extends BaseOnRLListener {
        int listenerType = TYPE_REFRESH;

        void onPullingToRefresh(View v);

        void onRefreshStart(View v);

        void onRefreshing(View v);
    }
}
