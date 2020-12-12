package com.easy.recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 14:16
 * @Description:        整合了SmartLayout提供的RefreshHeader和自定义的header的状态回调接口
 *                      也可以自己实现{@link RefreshHeader}(建议用{@link BaseEasyHeader}, 基本不需要使用
 *                      {@link EasyRecyclerView.Builder#setHeader(RefreshHeader)}
 */
public class BaseEasyHeader implements RefreshHeader {
    private BaseOnRefreshListener listener = null;
    private View view = null;
    private boolean isSupportHorizontalDrag = false;
    private SpinnerStyle spinnerStyle = null;

    public BaseEasyHeader(View view, boolean isSupportHorizontalDrag) {
        this.view = view;
        this.isSupportHorizontalDrag = isSupportHorizontalDrag;
    }

    public void setListener(@Nullable BaseOnRefreshListener listener) {
        this.listener = listener;
    }

    public void setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.spinnerStyle = spinnerStyle;
    }

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
        if (listener != null) listener.onFinish(success, view);
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
        if (oldState != newState && listener != null) {
            switch (newState) {
                case PullDownToRefresh:
                    listener.onPullingToRefresh(view);
                    break;
                case RefreshReleased:
                    listener.onRefreshStart(view);
                    break;
                case Refreshing:
                    listener.onRefreshing(view);
                    break;
            }
        }
    }

    public interface BaseOnRefreshListener extends BaseOnRLListener{
        int listenerType = TYPE_REFRESH;
        void onPullingToRefresh(View v);
        void onRefreshStart(View v);
        void onRefreshing(View v);
    }
}