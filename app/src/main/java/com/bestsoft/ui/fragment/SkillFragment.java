package com.bestsoft.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bestsoft.Constant;
import com.bestsoft.R;
import com.bestsoft.base.BaseMvpFragment;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.rxUtils.RxEvent;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.SkillContract;
import com.bestsoft.mvp.presenter.SkillPresenter;
import com.bestsoft.ui.activity.BanlanceActivity;
import com.bestsoft.ui.activity.HairCircleCenterActivity;
import com.bestsoft.ui.activity.MemberActivity;
import com.bestsoft.ui.activity.MessageActivity;
import com.bestsoft.ui.activity.PersonalActivity;
import com.bestsoft.ui.activity.WithdrawActivity;
import com.bestsoft.utils.IntentUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
@CreatePresenterAnnotation(SkillPresenter.class)
public class SkillFragment extends BaseMvpFragment<SkillContract.View, SkillPresenter> implements SkillContract.View {
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
    @BindView(R.id.ll_core_member)
    LinearLayout llCoreMember;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_balance)
    TextView txtBalance;
    @BindView(R.id.img_next)
    ImageView imgNext;
    @BindView(R.id.ll_center)
    LinearLayout llCenter;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_skill;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        txtTitle.setText(mContext.getString(R.string.title_up_skill));
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .init();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(RxEvent messageEvent) {
        if (messageEvent.getCode() == Constant.WITH_DRAW) {
            getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
        }
    }

    @OnClick({R.id.img_me, R.id.ll_core_member, R.id.ll_center, R.id.img_message, R.id.txt_withdraw, R.id.rl_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_me:
                IntentUtils.get().goActivity(mContext, PersonalActivity.class);
                break;
            case R.id.img_message:
                IntentUtils.get().goActivity(mContext, MessageActivity.class);
                break;
            case R.id.txt_withdraw:
                IntentUtils.get().goActivity(mContext, WithdrawActivity.class);
                break;
            case R.id.rl_balance:
                IntentUtils.get().goActivity(mContext, BanlanceActivity.class);
                break;
            case R.id.ll_center:
                IntentUtils.get().goActivity(mContext, HairCircleCenterActivity.class);
                break;
            case R.id.ll_core_member://核心会员
                IntentUtils.get().goActivity(mContext, MemberActivity.class);
                break;
        }
    }

    @Override
    public void setUserModel(UserModel userModel) {
        txtPrice.setText(userModel.getTotal_income() + "");
        txtBalance.setText("¥" + userModel.getBalance());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}