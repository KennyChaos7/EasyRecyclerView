package com.example.easyrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.recyclerview.BaseEasyFooter;
import com.easy.recyclerview.BaseEasyHeader;
import com.easy.recyclerview.EasyRecyclerView;
import com.easy.recyclerview.EasyRecyclerViewUtils;

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

        initTestEasyRecyclerView(view.findViewById(R.id.framelayout));
    }

    private void initTestEasyRecyclerView(ViewGroup viewGroup) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
            stringList.add(new StringBuffer().append(Math.random()).append(" - ").append(i).toString());
        }
        TestAdapter testAdapter = new TestAdapter();
        testAdapter.setData(stringList);
        EasyRecyclerView easyRecyclerView = new EasyRecyclerView.Builder(getContext())
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
                })
                .build();
        viewGroup.addView(
                        easyRecyclerView.toView()
        );
    }

    private class TestVH extends RecyclerView.ViewHolder{
        public TestVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class TestAdapter extends RecyclerView.Adapter<TestVH> {
        private List<String> stringList = new ArrayList<>();

        public void setData(List<String> stringList) {
            this.stringList = stringList;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public TestVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TestVH(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_easy_recyclerview, null)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull TestVH holder, int position) {
            ((TextView)holder.itemView.findViewById(R.id.textview)).setText(stringList.get(position));
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }
    }
}