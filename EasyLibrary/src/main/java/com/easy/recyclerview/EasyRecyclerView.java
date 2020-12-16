package com.easy.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;


/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 13:51
 * @Description:    简单初始化RecyclerView <br/>
 *                  整合了SmartLayout库, 当普通RecyclerView调用即可 <br/>
 *                  TODO 加入空白页和错误页功能，<br/>
 *                  TODO 简易初始化@{@link RecyclerView.Adapter},<br/>
 *                  TODO 整合@{@link androidx.recyclerview.widget.DiffUtil},<br/>
 *                  TODO adapter中的数据将为线程安全,<br/>
 *                  TODO 添加{@link java.lang.ref.WeakReference}进行优化
 */
public class EasyRecyclerView extends FrameLayout{
    private WeakReference<Context> wContext = null;
    //  是否已经初始化
    private boolean isHasInit = false;

    private _EasyRecyclerView _easyRecyclerView = null;

    private RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    //  对应了SmartLayout中滑动到底部时候, 是否自动加载更多, 默认为关闭
    private boolean isAllowAutoLoadMore = false;

    private RecyclerView.ItemDecoration itemDecoration;

    //  是否可以下拉刷新
    private boolean isAllowRefresh = false;
    private BaseEasyHeader<? extends BaseInnerHeader> easyHeader;

    //  是否可以上拉加载更多
    private boolean isAllowLoadMore = false;
    private BaseEasyFooter<? extends BaseInnerFooter>  easyFooter;

    private View EmptyView = null;
    private View ErrorView = null;

    public EasyRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public EasyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EasyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
        this._easyRecyclerView.setItemDecoration(itemDecoration);
    }

    public void setAllowRefresh(boolean allowRefresh) {
        isAllowRefresh = allowRefresh;
        this._easyRecyclerView.setAllowRefresh(allowRefresh);
    }

    public void setHeader(BaseEasyHeader<? extends BaseInnerHeader> easyHeader) {
        this.easyHeader = easyHeader;
        this._easyRecyclerView.setEasyHeader(easyHeader);
    }

    public void setAllowLoadMore(boolean allowLoadMore) {
        isAllowLoadMore = allowLoadMore;
        this._easyRecyclerView.setAllowLoadMore(allowLoadMore);
    }

    public void setFooter(BaseEasyFooter<? extends BaseInnerFooter> easyFooter) {
        this.easyFooter = easyFooter;
        this._easyRecyclerView.setEasyFooter(easyFooter);
    }

    public void setClassicsFooter(@Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
        this.easyFooter.setListener(listener);
        this._easyRecyclerView.setEasyFooter(easyFooter);
    }

    public void setClassicsHeader(@Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
        this.easyHeader.setListener(listener);
        this._easyRecyclerView.setEasyHeader(easyHeader);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this._easyRecyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this._easyRecyclerView.setLayoutManager(layoutManager);
    }

    public void setAllowAutoLoadMore(boolean allowAutoLoadMore) {
        isAllowAutoLoadMore = allowAutoLoadMore;
        this._easyRecyclerView.setAllowAutoLoadMore(allowAutoLoadMore);
    }

    /**
     * 初始化装载容器
     * 只初始化一次
     */
    private void init(Context context) {
        if (isHasInit)
            return;
        isHasInit = true;
        this.easyFooter = new EasyClassicsFooter(context);
        this.easyHeader = new EasyClassicsHeader(context);
        _easyRecyclerView = new _EasyRecyclerView(context);
        this.addView(_easyRecyclerView.getView());
    }
    

}