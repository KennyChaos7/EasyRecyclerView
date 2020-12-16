package com.easy.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;

public class _EasyRecyclerView {

    private RecyclerView _rv = null;
    private SmartRefreshLayout _srLayout = null;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.ItemDecoration itemDecoration;

    private boolean isAllowRefresh;
    private BaseEasyHeader<? extends RefreshHeader> easyHeader;

    private boolean isAllowLoadMore;
    private BaseEasyFooter<? extends BaseInnerFooter> easyFooter;

    private boolean isAllowAutoLoadMore = false;

    _EasyRecyclerView(Context context) {
        if (_rv != null && _srLayout != null)
            return;
        if (context != null) {
            _srLayout = new SmartRefreshLayout(context);
            _rv = new RecyclerView(context);
            _srLayout.addView(_rv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            _srLayout.setEnableAutoLoadMore(false);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        _rv.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        _rv.setLayoutManager(layoutManager);
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        _rv.addItemDecoration(itemDecoration);
    }

    public void setAllowRefresh(boolean allowRefresh) {
        isAllowRefresh = allowRefresh;
        _srLayout.setEnableRefresh(isAllowRefresh);
    }

    public void setEasyHeader(BaseEasyHeader<? extends RefreshHeader> easyHeader) {
        _srLayout.setRefreshHeader(easyHeader.toSmartLayout());
    }

    public void setAllowLoadMore(boolean allowLoadMore) {
        isAllowLoadMore = allowLoadMore;
        _srLayout.setEnableLoadMore(isAllowLoadMore);
    }

    public void setEasyFooter(BaseEasyFooter<? extends BaseInnerFooter>  easyFooter) {
        _srLayout.setRefreshFooter(easyFooter.toSmartLayout());
    }

    public void setAllowAutoLoadMore(boolean allowAutoLoadMore) {
        isAllowAutoLoadMore = allowAutoLoadMore;
        _srLayout.setEnableAutoLoadMore(isAllowAutoLoadMore);
    }

    public RecyclerView getRecyclerView() {
        return _rv;
    }

    public SmartRefreshLayout getParentLayout() { return _srLayout; }

    public View getView() { return _srLayout; }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration;
    }

    public boolean isAllowRefresh() {
        return isAllowRefresh;
    }

    public boolean isAllowLoadMore() {
        return isAllowLoadMore;
    }

    public boolean isAllowAutoLoadMore() {
        return isAllowAutoLoadMore;
    }

}
