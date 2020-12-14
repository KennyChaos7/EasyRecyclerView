package com.easy.recyclerview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;

/**
 * 直接继承于 {@linkplain EasyFooter}
 */
public class EasyClassicsFooter implements BaseEasyFooter<EasyClassicsFooter.InnerFooter> {
    private EasyFooter.BaseOnLoadMoreListener listener = null;
    private InnerFooter _footer = null;

    public EasyClassicsFooter(Context context) {
        if (_footer == null) {
            _footer = new InnerFooter(context);
        }
    }

    public void setListener(EasyFooter.BaseOnLoadMoreListener listener) {
        this.listener = listener;
    }

    @Override
    public InnerFooter toSmartLayout() {
        return _footer;
    }

    protected class InnerFooter extends ClassicsFooter implements BaseInnerFooter{

        public InnerFooter(Context context) {
            super(context);
        }

        public InnerFooter(Context context, AttributeSet attrs) {
            super(context, attrs);
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
}
