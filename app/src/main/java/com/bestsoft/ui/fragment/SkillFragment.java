package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseFragment;
import com.bestsoft.ui.activity.BanlanceActivity;
import com.bestsoft.ui.activity.WithdrawActivity;
import com.bestsoft.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @package: com.bestsoft.ui.fragment
 * @user:xhkj
 * @date:2018/10/31
 * @description:
 **/
public class SkillFragment extends BaseFragment {
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.txt_withdraw)
    TextView txtWithdraw;
    @BindView(R.id.rl_balance)
    RelativeLayout rlBalance;
    Unbinder unbinder;

    @Override
    protected int getLayout() {
        return R.layout.fragment_skill;
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

    @OnClick({R.id.img_me, R.id.img_message, R.id.txt_withdraw, R.id.rl_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                break;
            case R.id.img_message:
                break;
            case R.id.txt_withdraw:
                IntentUtils.get().goActivity(mContext, WithdrawActivity.class);
                break;
            case R.id.rl_balance:
                IntentUtils.get().goActivity(mContext, BanlanceActivity.class);
                break;
        }
    }
}