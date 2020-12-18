package com.easy.recyclerview;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
/**
 * @Auther: ou
 * @Time:   2020-12-12 0012 14:06
 * @Description:        工具类, 用来创建SmartLayout的Header和Footer<br/>
 *                      {@link EasyRecyclerView.Builder#setFooter(BaseEasyFooter)} {@link EasyRecyclerView.Builder#setHeader(BaseEasyHeader)}<br/>
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
    public EasyHeader createHeader(Context context, @LayoutRes int layoutId, boolean isSupportHorizontalDrag, @Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
        View view = LayoutInflater.from(context).inflate(layoutId, null, false);
        EasyHeader header = new EasyHeader(view, isSupportHorizontalDrag);
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
    public EasyFooter createFooter(Context context, @LayoutRes int layoutId, boolean isSupportHorizontalDrag, @Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
        View view = LayoutInflater.from(context).inflate(layoutId, null, false);
        EasyFooter footer = new EasyFooter(view, isSupportHorizontalDrag);
        footer.setListener(listener);
        return footer;
    }

    /**
     * 需要自己加载对应的类{@link com.scwang.smart.refresh.header.ClassicsHeader}到项目中
     * 创建一个跟{@link com.scwang.smart.refresh.header.ClassicsHeader}一样效果的header<br/>
     * 会有状态回调
     * @param context
     * @param listener  状态回调
     * @return
     */
    @Deprecated
    public EasyClassicsHeader createClassicsHeader(Context context, @Nullable BaseEasyHeader.BaseOnRefreshListener listener) {
        EasyClassicsHeader header = new EasyClassicsHeader(context);
        header.setListener(listener);
        return header;
    }

    /**
     * 需要自己加载对应的类{@link com.scwang.smart.refresh.footer.ClassicsFooter}到项目中
     * 创建一个跟{@link com.scwang.smart.refresh.footer.ClassicsFooter}一样效果的footer<br/>
     * 会有状态回调
     * @param context
     * @param listener  状态回调
     * @return
     */
    @Deprecated
    public EasyClassicsFooter createClassicsFooter(Context context, @Nullable BaseEasyFooter.BaseOnLoadMoreListener listener) {
        EasyClassicsFooter footer = new EasyClassicsFooter(context);
        footer.setListener(listener);
        return footer;
    }

    /**
     *
     * @param context
     * @return
     */
    public ClassicsHeader createClassicsHeader(Context context) {
        ClassicsHeader header = new ClassicsHeader(context);
        return header;
    }

    /**
     *
     * @param context
     * @return
     */
    public ClassicsFooter createClassicsFooter(Context context) {
        ClassicsFooter footer = new ClassicsFooter(context);
        return footer;
    }

    /**
     *  dp转换为px
     * @param dp
     * @return
     */
    protected int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
