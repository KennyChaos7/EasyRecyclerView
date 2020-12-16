package com.example.easyrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easy.recyclerview.BaseEasyFooter;
import com.easy.recyclerview.EasyRecyclerView;
import com.easy.recyclerview.EasyRecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {

    private EasyRecyclerView easyRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        easyRecyclerView = view.findViewById(R.id.easyrecyclerview);
        init();
    }

    private void init() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
            stringList.add(new StringBuffer().append(Math.random()).append(" - ").append(i).toString());
        }
        TestAdapter testAdapter = new TestAdapter();
        testAdapter.setData(stringList);

        easyRecyclerView.setAdapter(testAdapter);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        easyRecyclerView.setAllowLoadMore(true);
        easyRecyclerView.setAllowRefresh(true);
        easyRecyclerView.setHeader(EasyRecyclerViewUtils.getInstance().createHeader(getContext(), R.layout.footer_test, false,null));
        easyRecyclerView.setFooter(EasyRecyclerViewUtils.getInstance().createFooter(getContext(), R.layout.footer_test, false, new BaseEasyFooter.BaseOnLoadMoreListener() {
            @Override
            public void onFinish(boolean isSuccess, View view) {

            }

            @Override
            public void onPullingToLoad(View v) {

            }

            @Override
            public void onLoadMoreStart(View v) {

            }

            @Override
            public void onLoading(View v) {

            }
        }));
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
