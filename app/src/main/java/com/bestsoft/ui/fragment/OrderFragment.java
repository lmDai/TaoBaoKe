package com.bestsoft.ui.fragment;

import android.view.LayoutInflater;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description:
 **/
public class OrderFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }
}
