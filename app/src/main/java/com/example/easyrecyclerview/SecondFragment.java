package com.example.easyrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.easy.recyclerview.BaseEasyFooter;
import com.easy.recyclerview.BaseEasyHeader;
import com.easy.recyclerview.EasyAdapter;
import com.easy.recyclerview.EasyRecyclerView;
import com.easy.recyclerview.EasyRecyclerViewUtils;
import com.easy.recyclerview.EasyViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

//        initTestEasyRecyclerView(view.findViewById(R.id.framelayout));
        initTestBeanEasyRecyclerView(view.findViewById(R.id.framelayout));
    }

    private void initTestEasyRecyclerView(ViewGroup viewGroup) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
            stringList.add(new StringBuffer().append(Math.random()).append(" - ").append(i).toString());
        }
        TestAdapter testAdapter = new TestAdapter(R.layout.item_test_easy_recyclerview, new EasyAdapter.EasyItemListener() {
            @Override
            public void onItemClick(int position, View view) {
                Log.e(":", position + " view " + view.getClass().getSimpleName());
            }
        });
        EasyRecyclerView.Builder easyRecyclerViewBuilder = new EasyRecyclerView.Builder(getContext())
                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false))
                .setAdapter(testAdapter)
                .setAllowLoadMore(true)
                .setAllowRefresh(true)
                .setClassicsHeader(new BaseEasyHeader.BaseOnRefreshListener() {
                    @Override
                    public void onPullingToRefresh(View v) {

                    }

                    @Override
                    public void onRefreshStart(View v) {

                    }

                    @Override
                    public void onRefreshing(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                })
                .setFooter(EasyRecyclerViewUtils.getInstance().createFooter(getContext(), R.layout.footer_test, false, new BaseEasyFooter.BaseOnLoadMoreListener() {
                    @Override
                    public void onPullingToLoad(View v) {

                    }

                    @Override
                    public void onLoadMoreStart(View v) {

                    }

                    @Override
                    public void onLoading(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                }))
                .setClassicsFooter(new BaseEasyFooter.BaseOnLoadMoreListener() {
                    @Override
                    public void onPullingToLoad(View v) {

                    }

                    @Override
                    public void onLoadMoreStart(View v) {

                    }

                    @Override
                    public void onLoading(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                });
        EasyRecyclerView easyRecyclerView = easyRecyclerViewBuilder.build();
        viewGroup.addView(
                        easyRecyclerView.toView()
        );
        testAdapter.setNewData(stringList);
    }

    //diff
    //  鉴于需要双倍list的内存大小才能匹配, 并不建议使用
    private void initTestBeanEasyRecyclerView(ViewGroup viewGroup) {
        TestBeanAdapter testBeanAdapter = new TestBeanAdapter(R.layout.item_test_easy_recyclerview, new EasyAdapter.EasyItemListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });
        EasyRecyclerView.Builder easyRecyclerViewBuilder = new EasyRecyclerView.Builder(getContext())
                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false))
                .setAdapter(testBeanAdapter)
                .setAllowLoadMore(true)
                .setAllowRefresh(true)
                .setClassicsHeader(new BaseEasyHeader.BaseOnRefreshListener() {
                    @Override
                    public void onPullingToRefresh(View v) {

                    }

                    @Override
                    public void onRefreshStart(View v) {

                    }

                    @Override
                    public void onRefreshing(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                })
                .setFooter(EasyRecyclerViewUtils.getInstance().createFooter(getContext(), R.layout.footer_test, false, new BaseEasyFooter.BaseOnLoadMoreListener() {
                    @Override
                    public void onPullingToLoad(View v) {

                    }

                    @Override
                    public void onLoadMoreStart(View v) {

                    }

                    @Override
                    public void onLoading(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                }))
                .setClassicsFooter(new BaseEasyFooter.BaseOnLoadMoreListener() {
                    @Override
                    public void onPullingToLoad(View v) {

                    }

                    @Override
                    public void onLoadMoreStart(View v) {

                    }

                    @Override
                    public void onLoading(View v) {

                    }

                    @Override
                    public void onFinish(boolean isSuccess, View view) {

                    }
                });
        EasyRecyclerView easyRecyclerView = easyRecyclerViewBuilder.build();
        List<TestBean> testBeanList = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
            testBeanList.add(new TestBean(i + "0", " - " + i, i%2));
        }
        viewGroup.addView(
                easyRecyclerView.toView()
        );
        testBeanAdapter.addNewData(testBeanList);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<TestBean> testBeanList2 = new ArrayList<>();
                for (int i = 0; i < 30; i ++) {
                    testBeanList2.add(new TestBean(i + "/0", " - " + i, i));
                }
                testBeanAdapter.diffUpdate(testBeanList2, new EasyAdapter.DiffVerifyCallback<TestBean>() {
                    @Override
                    public boolean isSameItem(TestBean _old, TestBean _new) {
                        if (_old.index == _new.index) {
                            Toast.makeText(getActivity(),"isSameItem true " + _new.name, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean isSameContent(TestBean _old, TestBean _new) {
                        if (_old.value.equals(_new.value)) {
                            Toast.makeText(getActivity(), "isSameContent true " + _new.name, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
            }
        },10 * 1000);
    }

    private class TestAdapter extends EasyAdapter<String>  {

        public TestAdapter(int itemLayoutId, @NonNull EasyItemListener listener) {
            super(itemLayoutId, listener);
        }

        @Override
        public void onEasyBindViewHolder(@NonNull EasyViewHolder holder, int position) {
            holder.setText(R.id.textview, getData(position));
        }
    }

    private class TestBeanAdapter extends EasyAdapter<TestBean> {

        public TestBeanAdapter(int itemLayoutId, @NonNull EasyItemListener listener) {
            super(itemLayoutId, listener);
        }

        @Override
        public void onEasyBindViewHolder(@NonNull EasyViewHolder holder, int position) {
            holder.setText(R.id.textview,
                    getData(position).name
                            + " -- " + getData(position).value
                            + " -- " + getData(position).index
                            + " -- " + getData(position).time);
        }
    }

    private class TestBean{
        private String name;
        private String value;
        private long time;
        private int index;

        public TestBean(String name, String value, int index) {
            this.name = name;
            this.value = value;
            this.index = index;
            this.time = System.currentTimeMillis();
        }
    }
}