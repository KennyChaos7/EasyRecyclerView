package com.easy.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.lang.ref.WeakReference;


/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 13:51
 * @Description:    简单初始化RecyclerView <br/>
 *                  整合了SmartLayout库，用建造者模式实现了简易初始化{@link EasyRecyclerView.Builder#build()} <br/>
 *                  加入空白页和错误页功能，<br/>
 *                  TODO 简易初始化{@link RecyclerView.Adapter},<br/>
 *                  TODO 整合{@link androidx.recyclerview.widget.DiffUtil},<br/>
 *                  TODO adapter中的数据将为线程安全,<br/>
 *                  TODO 添加{@link java.lang.ref.WeakReference}进行优化
 */
public class EasyRecyclerView {
    private final static ViewGroup.LayoutParams defaultLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    //  是否已经初始化
    private boolean isHasInit = false;
    //  装载容器
    private FrameLayout parent = null;
    //  容器中View索引
    private final static int INDEX_SMARTLAYOUT  =   0;
    private final static int INDEX_EMPTYVIEW    =   1;
    private final static int INDEX_ERRORVIEW    =   2;
    /**
     * 返回装载容器
     * @return
     */
    public View toView() {
        return parent;
    }

    public ViewGroup toViewGroup() {return parent;}

    /**
     * 扩展
     * 往装载容器中加入其他控件
     * 特定的View只能存在一个
     * @param view
     * @param width 单位dp
     * @param height 单位dp
     */
    public void addView(View view, int width, int height) {
        ViewGroup.LayoutParams targetLayoutParams = new ViewGroup.LayoutParams(
                EasyRecyclerViewUtils.getInstance().dp2px(view.getContext(), width),
                EasyRecyclerViewUtils.getInstance().dp2px(view.getContext(), height)
        );
        this.addView(view, targetLayoutParams);
    }

    /**
     * 扩展
     * 往装载容器中加入其他控件
     * 特定的View只能存在一个
     * @param view
     * @param layoutParams ViewGroup.LayoutParams
     */
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (parent.getChildAt(INDEX_SMARTLAYOUT) != null
                && parent.getChildAt(INDEX_SMARTLAYOUT) instanceof SmartRefreshLayout
                && view instanceof SmartRefreshLayout) {
            parent.removeViewAt(INDEX_SMARTLAYOUT);
        }
        else if (parent.getChildAt(INDEX_EMPTYVIEW) != null) {
            parent.removeViewAt(INDEX_EMPTYVIEW);
        }
        else if (parent.getChildAt(INDEX_ERRORVIEW) != null) {
            parent.removeViewAt(INDEX_ERRORVIEW);
        }
        int index = parent.getChildCount();
        parent.addView(view, index, layoutParams);
    }

    /**
     * 主动展示错误页
     * 需要在先{@link EasyRecyclerView.Builder#}
     */
    public void showErrorView() {

    }

    /**
     * 主动展示空白页
     * 一般当adapter更新数据时数据量为0时会自动显示
     * 需要在先{@link EasyRecyclerView.Builder#}
     */
    public void showEmptyView() {

    }

    /**
     * 初始化装载容器
     * 只初始化一次
     * @param context
     */
    private EasyRecyclerView(Context context) {
        if (isHasInit)
            return;
        parent = new FrameLayout(context);
        parent.setLayoutParams(defaultLayoutParams);
        isHasInit = true;
    }


    /**
     * 建造者
     */
    public static class Builder {
        private WeakReference<Context> context = null;
        private EasyRecyclerView me = null;

        private RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.ItemDecoration itemDecoration;
        //  RecyclerView+SmartLayout
        private _EasyRecyclerView _easyRecyclerView = null;
        //  空白页面
        private View emptyView = null;
        //  是否自动显示空白页
        private boolean isAllowAutoShowEmpty = true;
        //  错误页面
        private View errorView = null;

        //  对应了SmartLayout中滑动到底部时候, 是否自动加载更多, 默认为关闭
        private boolean isAllowAutoLoadMore = false;

        //  是否可以下拉刷新
        private boolean isAllowRefresh = false;
        private BaseEasyHeader<? extends BaseInnerHeader> easyHeader;

        //  是否可以上拉加载更多
        private boolean isAllowLoadMore = false;
        private BaseEasyFooter<? extends BaseInnerFooter> easyFooter;

        public Builder(Context context) {
            if (this.context != null) {
                this.context.clear();
                this.context = null;
            }
            this.context = new WeakReference<>(context);
        }

        public Builder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.itemDecoration = itemDecoration;
            return this;
        }

        public Builder setAllowRefresh(boolean allowRefresh) {
            isAllowRefresh = allowRefresh;
            return this;
        }

        public Builder setHeader(BaseEasyHeader<? extends BaseInnerHeader> easyHeader) {
            this.easyHeader = easyHeader;
            return this;
        }

        public Builder setAllowLoadMore(boolean allowLoadMore) {
            isAllowLoadMore = allowLoadMore;
            return this;
        }

        public Builder setFooter(BaseEasyFooter<? extends BaseInnerFooter> easyFooter) {
            this.easyFooter = easyFooter;
            return this;
        }

        public Builder setClassicsFooter(@Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
            this.easyFooter = new EasyClassicsFooter(context.get());
            this.easyFooter.setListener(listener);
            return this;
        }

        public Builder setClassicsHeader(@Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
            this.easyHeader = new EasyClassicsHeader(context.get());
            this.easyHeader.setListener(listener);
            return this;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public Builder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        public Builder setAllowAutoLoadMore(boolean allowAutoLoadMore) {
            isAllowAutoLoadMore = allowAutoLoadMore;
            return this;
        }

        public Builder setAllowAutoShowEmpty(boolean allowAutoShowEmpty) {
            this.isAllowAutoShowEmpty = allowAutoShowEmpty;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            this.emptyView = emptyView;
            return this;
        }

        public Builder setEmptyViewByIds(@LayoutRes int emptyViewId) {
            this.emptyView = LayoutInflater.from(context.get()).inflate(emptyViewId, null, false);
            return this;
        }

        public Builder setErrorView(View errorView) {
            this.errorView = errorView;
            return this;
        }

        public Builder setErrorViewByIds(@LayoutRes int errorViewId) {
            this.errorView = LayoutInflater.from(context.get()).inflate(errorViewId, null, false);
            return this;
        }

        public EasyRecyclerView build() {
            if (me == null) {
                /**
                 * 初始化{@link _EasyRecyclerView}和 {@link EasyRecyclerView}
                 * 只初始化一次
                 */
                if (_easyRecyclerView == null) _easyRecyclerView = new _EasyRecyclerView(context.get());
                if (adapter != null) _easyRecyclerView.setAdapter(adapter);
                if (layoutManager != null) _easyRecyclerView.setLayoutManager(layoutManager);
                if (itemDecoration != null) _easyRecyclerView.setItemDecoration(itemDecoration);
                _easyRecyclerView.setAllowLoadMore(isAllowLoadMore);
                if (easyFooter != null) _easyRecyclerView.setEasyFooter(easyFooter);
                _easyRecyclerView.setAllowRefresh(isAllowRefresh);
                if (easyHeader != null) _easyRecyclerView.setEasyHeader(easyHeader);
                _easyRecyclerView.setAllowAutoLoadMore(isAllowAutoLoadMore);
                _easyRecyclerView.setAllowAutoShowEmpty(isAllowAutoShowEmpty);
                me = new EasyRecyclerView(context.get());
                me.addView(_easyRecyclerView.getView(), defaultLayoutParams);
                if (emptyView == null) {
                    emptyView = LayoutInflater.from(context.get()).inflate(R.layout.layout_empty, null);
                }
                emptyView.setVisibility(View.GONE);
                me.addView(emptyView, defaultLayoutParams);
                if (errorView  == null) {
                    errorView = LayoutInflater.from(context.get()).inflate(R.layout.layout_error, null);
                }
                errorView.setVisibility(View.GONE);
                me.addView(errorView, defaultLayoutParams);

            }
            return me;
        }
    }

}