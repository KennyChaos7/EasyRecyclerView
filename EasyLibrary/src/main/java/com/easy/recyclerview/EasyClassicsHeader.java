package com.easy.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;

import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;

/**
 * 直接继承于 {@linkplain ClassicsHeader}
 */
public class EasyClassicsHeader extends ClassicsHeader {
    private BaseEasyHeader.BaseOnRefreshListener listener = null;

    public EasyClassicsHeader(Context context) {
        super(context);
    }

    public EasyClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setListener(BaseEasyHeader.BaseOnRefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        if (listener != null) listener.onFinish(success, layout.getLayout());
        return super.onFinish(layout, success);
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (oldState != newState && listener != null) {
            switch (newState) {
                case PullDownToRefresh:
                    listener.onPullingToRefresh(refreshLayout.getLayout());
                    break;
                case RefreshReleased:
                    listener.onRefreshStart(refreshLayout.getLayout());
                    break;
                case Refreshing:
                    listener.onRefreshing(refreshLayout.getLayout());
                    break;
            }
        }
        super.onStateChanged(refreshLayout, oldState, newState);
    }
}
