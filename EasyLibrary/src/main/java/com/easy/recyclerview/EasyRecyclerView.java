package com.easy.recyclerview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;


/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 13:51
 * @Description:    简单初始化RecyclerView <br/>
 *                  整合了SmartLayout库，用建造者模式实现了简易初始化@{{@link EasyRecyclerView.Builder#build(Context)}} <br/>
 *                  TODO 加入空白页和错误页功能，<br/>
 *                  TODO 简易初始化@{@link RecyclerView.Adapter},<br/>
 *                  TODO 整合@{@link androidx.recyclerview.widget.DiffUtil},<br/>
 *                  TODO adapter中的数据将为线程安全,<br/>
 *                  TODO 添加{@link java.lang.ref.WeakReference}进行优化
 */
public class EasyRecyclerView {
    //  是否已经初始化
    private boolean isHasInit = false;
    //  装载容器
    private FrameLayout parent = null;

    @Deprecated
    private Builder default_builder = null;

    private View EmptyView = null;
    private View ErrorView = null;

    /**
     * 返回装载容器
     * @return
     */
    public View toView() {
        return parent;
    }

    /**
     * 扩展
     * 往装载容器中加入其他控件
     * 同一个View只能存在一个(待优化)
     * @param view
     * @param width
     * @param height
     */
    public void addView(View view, int width , int height) {
        if (parent.getChildAt(0) != null && parent.getChildAt(0) instanceof SmartRefreshLayout) {
            parent.removeViewAt(0);
        }
        parent.addView(view, width, height);
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
        parent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        isHasInit = true;
    }


    /**
     * 建造者
     */
    public static class Builder {
        private EasyRecyclerView me = null;
        private _EasyRecyclerView _easyRecyclerView = null;

        private RecyclerView.Adapter adapter;

        private RecyclerView.LayoutManager layoutManager;

        //  对应了SmartLayout中滑动到底部时候, 是否自动加载更多, 默认为关闭
        private boolean isAllowAutoLoadMore = false;

        private RecyclerView.ItemDecoration itemDecoration;

        //  是否可以下拉刷新
        private boolean isAllowRefresh = false;
        private RefreshHeader baseEasyHeader;

        //  是否可以上拉加载更多
        private boolean isAllowLoadMore = false;
        private RefreshFooter baseEasyFooter;

        public Builder setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.itemDecoration = itemDecoration;
            return this;
        }

        public Builder setAllowRefresh(boolean allowRefresh) {
            isAllowRefresh = allowRefresh;
            return this;
        }

        public Builder setHeader(RefreshHeader baseEasyHeader) {
            this.baseEasyHeader = baseEasyHeader;
            return this;
        }

        public Builder setAllowLoadMore(boolean allowLoadMore) {
            isAllowLoadMore = allowLoadMore;
            return this;
        }

        public Builder setFooter(RefreshFooter baseEasyFooter) {
            this.baseEasyFooter = baseEasyFooter;
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

        public EasyRecyclerView build(Context context) {
            if (me == null) {
                /**
                 * 初始化{@link _EasyRecyclerView}和 {@link EasyRecyclerView}
                 * 只初始化一次
                 */
                if (_easyRecyclerView == null) _easyRecyclerView = new _EasyRecyclerView(context);
                if (adapter != null) _easyRecyclerView.setAdapter(adapter);
                if (layoutManager != null) _easyRecyclerView.setLayoutManager(layoutManager);
                if (itemDecoration != null) _easyRecyclerView.setItemDecoration(itemDecoration);
                _easyRecyclerView.setAllowLoadMore(isAllowLoadMore);
                if (baseEasyFooter != null) _easyRecyclerView.setBaseEasyFooter(baseEasyFooter);
                _easyRecyclerView.setAllowRefresh(isAllowRefresh);
                if (baseEasyHeader != null) _easyRecyclerView.setBaseEasyHeader(baseEasyHeader);
                _easyRecyclerView.setAllowAutoLoadMore(isAllowAutoLoadMore);
                me = new EasyRecyclerView(context);
            }

            me.addView(_easyRecyclerView.getView(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return me;
        }
    }

}