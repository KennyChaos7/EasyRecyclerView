package com.easy.recyclerview;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 14:16
 * @Description:        整合了SmartLayout提供的RefreshFooter和自定义的footer的状态回调接口
 *                      也可以自己实现{@link RefreshFooter}(建议用{@link EasyFooter}, 基本不需要使用
 *                      {@link EasyRecyclerView.Builder#setFooter(BaseEasyFooter)}}
 */
public class EasyFooter implements BaseEasyFooter<EasyFooter.InnerFooter> {
    private final Set<BaseOnLoadMoreListener> listenerHashSet = Collections.synchronizedSet(new HashSet<BaseOnLoadMoreListener>());
    private View view = null;
    private boolean isSupportHorizontalDrag = false;
    private SpinnerStyle spinnerStyle = null;
    private InnerFooter _footer = null;

    public EasyFooter(View view, boolean isSupportHorizontalDrag) {
        if (_footer == null) {
            _footer = new InnerFooter();
        }
        this.view = view;
        this.isSupportHorizontalDrag = isSupportHorizontalDrag;
    }

    @Override
    public void setListener(@Nullable BaseOnLoadMoreListener listener) {
        listenerHashSet.add(listener);
    }

    public void setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.spinnerStyle = spinnerStyle;
    }

    @Override
    public InnerFooter toSmartLayout() {
        return _footer;
    }

    protected class InnerFooter implements BaseInnerFooter {

        @NonNull
        @Override
        public View getView() {
            return view;
        }

        @NonNull
        @Override
        public SpinnerStyle getSpinnerStyle() {
            return spinnerStyle == null ? SpinnerStyle.Translate : spinnerStyle;
        }

        @Override
        public void setPrimaryColors(int... colors) {

        }

        @Override
        public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

        }

        @Override
        public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

        }

        @Override
        public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

        }

        @Override
        public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

        }

        @Override
        public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
            if (!listenerHashSet.isEmpty()) {
                for (BaseOnLoadMoreListener listener : listenerHashSet)
                    listener.onFinish(success, view);
            }
            return 300;
        }

        @Override
        public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

        }

        @Override
        public boolean isSupportHorizontalDrag() {
            return isSupportHorizontalDrag;
        }

        @Override
        public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
            Log.i(this.getClass().getSimpleName(), "oldState = " + oldState + " newState = " + newState);
            if (oldState != newState && !listenerHashSet.isEmpty()) {
                for (BaseOnLoadMoreListener listener : listenerHashSet) {
                    switch (newState) {
                        case PullUpToLoad:
                            listener.onPullingToLoad(view);
                            break;
                        case Loading:
                            listener.onLoading(view);
                            break;
                        case ReleaseToLoad:
                            listener.onLoadMoreStart(view);
                            break;
                    }
                }
            }
        }

        @Override
        public boolean setNoMoreData(boolean noMoreData) {
            return false;
        }

    }
}
