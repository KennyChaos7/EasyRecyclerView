package com.easy.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;

public class _EasyRecyclerView {

    private RecyclerView _rv = null;
    private SmartRefreshLayout _srLayout = null;
    private ClassicsHeader _ch = null;
    private ClassicsFooter _cf = null;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.ItemDecoration itemDecoration;

    private boolean isAllowRefresh;
    private RefreshHeader baseEasyHeader;

    private boolean isAllowLoadMore;
    private RefreshFooter baseEasyFooter;

    private boolean isAllowAutoLoadMore = false;

    _EasyRecyclerView(Context context) {
        if (_rv != null && _srLayout != null)
            return;
        if (context != null) {
            _srLayout = new SmartRefreshLayout(context);
            _rv = new RecyclerView(context);
            _srLayout.addView(_rv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            _ch = new EasyClassicsHeader(context);
            _cf = new EasyClassicsFooter(context);
            _srLayout.setRefreshHeader(_ch);
            _srLayout.setRefreshFooter(_cf);
        }
//        initEasyRecyclerView();
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

    public void setBaseEasyHeader(RefreshHeader baseEasyHeader) {
        this.baseEasyHeader = baseEasyHeader;
        _srLayout.setRefreshHeader(baseEasyHeader);
    }

    public void setAllowLoadMore(boolean allowLoadMore) {
        isAllowLoadMore = allowLoadMore;
        _srLayout.setEnableLoadMore(isAllowLoadMore);
    }

    public void setBaseEasyFooter(RefreshFooter baseEasyFooter) {
        this.baseEasyFooter = baseEasyFooter;
        _srLayout.setRefreshFooter(baseEasyFooter);
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

    public RefreshHeader getBaseEasyHeader() {
        return baseEasyHeader;
    }

    public boolean isAllowLoadMore() {
        return isAllowLoadMore;
    }

    public RefreshFooter getBaseEasyFooter() {
        return baseEasyFooter;
    }

    public boolean isAllowAutoLoadMore() {
        return isAllowAutoLoadMore;
    }

    @Deprecated
    private void initEasyRecyclerView() {
        if (itemDecoration != null)
            _rv.addItemDecoration(itemDecoration);
        _srLayout.setEnableAutoLoadMore(isAllowAutoLoadMore);
        _srLayout.setEnableRefresh(isAllowRefresh);
        _srLayout.setEnableLoadMore(isAllowLoadMore);
        if (isAllowRefresh) {
            if (baseEasyHeader != null) {
                _srLayout.setRefreshHeader(baseEasyHeader);
            }else {
                _srLayout.setRefreshHeader(_ch);
            }
        }
        if (isAllowLoadMore) {
            if (baseEasyFooter != null) {
                _srLayout.setRefreshFooter(baseEasyFooter);
            }else {
                _srLayout.setRefreshFooter(_cf);
            }
        }
    }

}
