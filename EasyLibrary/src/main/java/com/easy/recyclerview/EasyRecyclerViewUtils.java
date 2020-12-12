package com.easy.recyclerview;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.easy.recyclerview.BaseEasyHeader;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 14:06
 * @Description:        工具类, 用来创建SmartLayout的Header和Footer<br/>
 *                      {@link EasyRecyclerView.Builder#setFooter(RefreshFooter)} {@link EasyRecyclerView.Builder#setHeader(RefreshHeader)}<br/>
 *                      也可以创建默认
 */
public class EasyRecyclerViewUtils {
    private static EasyRecyclerViewUtils _instance = null;
    public static EasyRecyclerViewUtils getInstance() {
        if (_instance == null) {
            synchronized (EasyRecyclerViewUtils.class) {
                _instance = new EasyRecyclerViewUtils();
            }
        }
        return _instance;
    }


    /**
     * 根据传入的layoutId创建并初始化一个header<br/>
     * 会有状态回调
     * @param context
     * @param layoutId 布局id
     * @param isSupportHorizontalDrag 是否支持横向拖拽
     * @param listener 状态回调
     * @return
     */
    public BaseEasyHeader createHeader(Context context, @LayoutRes int layoutId, boolean isSupportHorizontalDrag, @Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
        View view = LayoutInflater.from(context).inflate(layoutId, null, false);
        BaseEasyHeader header = new BaseEasyHeader(view, isSupportHorizontalDrag);
        header.setListener(listener);
        return header;
    }

    /**
     * 根据传入的layoutId创建并初始化一个footer<br/>
     * 会有状态回调
     * @param context
     * @param layoutId  布局id
     * @param isSupportHorizontalDrag
     * @param listener  状态回调
     * @return
     */
    public BaseEasyFooter createFooter(Context context, @LayoutRes int layoutId, boolean isSupportHorizontalDrag, @Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
        View view = LayoutInflater.from(context).inflate(layoutId, null, false);
        BaseEasyFooter footer = new BaseEasyFooter(view, isSupportHorizontalDrag);
        footer.setListener(listener);
        return footer;
    }

    /**
     * 创建一个跟{@link com.scwang.smart.refresh.header.ClassicsHeader}一样效果的header<br/>
     * 会有状态回调
     * @param context
     * @param listener  状态回调
     * @return
     */
    public EasyClassicsHeader createClassicsHeader(Context context, @Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
        EasyClassicsHeader header = new EasyClassicsHeader(context);
        header.setListener(listener);
        return header;
    }

    /**
     * 创建一个跟{@link com.scwang.smart.refresh.footer.ClassicsFooter}一样效果的footer<br/>
     * 会有状态回调
     * @param context
     * @param listener  状态回调
     * @return
     */
    public EasyClassicsFooter createClassicsFooter(Context context, @Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
        EasyClassicsFooter footer = new EasyClassicsFooter(context);
        footer.setListener(listener);
        return footer;
    }


}
