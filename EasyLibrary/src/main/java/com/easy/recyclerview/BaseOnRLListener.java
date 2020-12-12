package com.easy.recyclerview;

import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshHeader;

public interface BaseOnRLListener {
    final int TYPE_REFRESH = 1;
    final int TYPE_LOADMOEW = 2;

    int listenerType = 0;
    void onFinish(boolean isSuccess, View view);
}
