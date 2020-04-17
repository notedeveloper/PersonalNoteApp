package com.exmply.personalnote.calendar.pager;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.exmply.personalnote.calendar.Article;
import com.exmply.personalnote.calendar.ArticleAdapter;
import com.exmply.personalnote.R;
import com.exmply.personalnote.calendar.base.fragment.BaseFragment;
import com.exmply.personalnote.calendar.group.GroupItemDecoration;
import com.exmply.personalnote.calendar.group.GroupRecyclerView;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    public static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = (GroupRecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    public boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
