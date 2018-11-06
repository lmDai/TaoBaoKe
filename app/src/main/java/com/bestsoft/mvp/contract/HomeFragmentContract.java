package com.bestsoft.mvp.contract;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.bestsoft.common.https.BasePresenter;
import com.bestsoft.common.https.IBaseView;
import com.bestsoft.ui.adapter.BaseDelegateAdapter;

/**
 * 首页
 */
public interface HomeFragmentContract {


    interface View extends IBaseView {

        void setOnclick(int position);


        Context getContext();
    }


    abstract class Presenter extends BasePresenter<View> {
        public abstract DelegateAdapter initRecyclerView(RecyclerView recyclerView);

        public abstract BaseDelegateAdapter initGvMenu();//初始化九宫格

        public abstract BaseDelegateAdapter initTitle();//初始化标题

        public abstract BaseDelegateAdapter initSearch();//初始化搜索框

        public abstract BaseDelegateAdapter initBanner();//初始化banner

        public abstract BaseDelegateAdapter initFastEntrceTitle();//初始化快速入口

        public abstract BaseDelegateAdapter initFastEntrace();//初始化快速入口内容

        public abstract BaseDelegateAdapter initStickyTab();//初始化吸顶内容

        public abstract BaseDelegateAdapter initFragment();//初始化底部

    }


}
