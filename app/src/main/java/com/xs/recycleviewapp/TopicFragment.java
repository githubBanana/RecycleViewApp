package com.xs.recycleviewapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kennyc.view.MultiStateView;
import com.xs.recycleviewapp.adapter.BaseAdapter;
import com.xs.recycleviewapp.adapter.TopicAdapter;
import com.xs.recycleviewapp.model.TopicModel;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 17:54
 * @email Xs.lin@foxmail.com
 */
public class TopicFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{
    private static final String TAG = "TopicFragment";

    private static final String TYPE = "type";
    private MultiStateView mMultiStateView;
    private BGARefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private TopicAdapter mAdapter;


    public TopicFragment(){}
    public static TopicFragment newInstance(Context context,int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE,type);
        return (TopicFragment) Fragment.instantiate(context,TopicFragment.class.getName(),bundle);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null || !getArguments().containsKey(TYPE)) {
            throw new NullPointerException();
        }
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        requestData();
    }
    private void initData() {
        mAdapter = new TopicAdapter(getActivity(), new BaseAdapter.OnItemClickListener<TopicModel>() {
            @Override
            public void onItemClick(View view, TopicModel topicModel, int position) {

            }
        });
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.bgaRefreshLayout);
        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder viewHolder = new BGANormalRefreshViewHolder(getActivity(),true);
        viewHolder.setLoadingMoreText("加载更多...");
        mRefreshLayout.setRefreshViewHolder(viewHolder);

        mMultiStateView = (MultiStateView) view.findViewById(R.id.multi_state_view);
    }

    //模拟网络访问
    private void requestData() {
        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }
    class MyAsyncTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            mRefreshLayout.endRefreshing();
            mRefreshLayout.endLoadingMore();
            if (mAdapter.isEmpty()) {
                mAdapter.setData(init_non_true_data());
            } else {
                mAdapter.addAll(init_non_true_data());
            }
        }
    }
    //假数据
    private List<TopicModel> init_non_true_data() {
        List<TopicModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TopicModel t = new TopicModel();
            t.setID("00"+i);
            t.setContent("我想说什么"+i);
            t.setName("linxiaosheng");
            t.setAddTime("2016/5/16 11:22:1"+i);
            t.setHeadImg("http://wx.qlogo.cn/mmopen/FdvzHoYqRRkbh5MtEQODgXibfG3aL5Dgc1Y5eUtECsoZrQeT1TjABvicKItAceEfNbPwgxxpXohNrKjyH7bs9kFMBJpc5juxpu/0");
            t.setImgs("http://weidongzn.com//Files/upload/201605/16/i20160516112152448.jpg,http://weidongzn.com//Files/upload/201605/16/i20160516112152650.jpg,http://weidongzn.com//Files/upload/201605/16/i20160516112216401.jpg,http://weidongzn.com//Files/upload/201605/16/i20160516112216684.jpg,http://weidongzn.com//Files/upload/201605/16/i20160516112216844.jpg,http://weidongzn.com//Files/upload/201605/16/i20160516112217007.jpg");
            t.setCollectCount("2");
            t.setIS_Follow("1");
            t.setComment("lala");
            t.setIS_Keep("1");
            t.setLabel("中超,乘风破浪,爱运动,中国好肌友,美队3");
            list.add(t);
        }
        return list;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        Log.e(TAG,"onBGARefreshLayoutBeginRefreshing");
        requestData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        Log.e(TAG,"onBGARefreshLayoutBeginLoadingMore");
        requestData();
        return true;
    }
}

