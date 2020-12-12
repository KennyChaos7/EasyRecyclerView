package com.easy.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import android.util.AttributeSet;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;

/**
 * 直接继承于 {@linkplain ClassicsFooter}
 */
public class EasyClassicsFooter extends ClassicsFooter {
    private BaseEasyFooter.BaseOnLoadMoreListener listener = null;

    public EasyClassicsFooter(Context context) {
        super(context);
    }

    public EasyClassicsFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(BaseEasyFooter.BaseOnLoadMoreListener listener) {
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
                case PullUpToLoad:
                    listener.onPullingToLoad(refreshLayout.getLayout());
                    break;
                case Loading:
                    listener.onLoading(refreshLayout.getLayout());
                    break;
                case ReleaseToLoad:
                    listener.onLoadMoreStart(refreshLayout.getLayout());
                    break;
            }
        }
        super.onStateChanged(refreshLayout, oldState, newState);
    }
}
