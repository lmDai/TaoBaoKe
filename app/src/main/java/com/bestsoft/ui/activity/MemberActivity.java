package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bestsoft.Constant;
import com.bestsoft.MyApplication;
import com.bestsoft.R;
import com.bestsoft.api.TaoBaoKeApi;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.UpgradeModel;
import com.bestsoft.bean.UserModel;
import com.bestsoft.common.https.BaseApi;
import com.bestsoft.common.https.rxUtils.RxEvent;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.UpgradeContract;
import com.bestsoft.mvp.presenter.UpgradePresenter;
import com.bestsoft.ui.adapter.FastEntranceAdapter;
import com.bestsoft.utils.IntentUtils;
import com.bestsoft.utils.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @package: com.bestsoft.ui.activity
 * @user:xhkj
 * @date:2018/11/6
 * @description:会员中心
 **/
@CreatePresenterAnnotation(UpgradePresenter.class)
public class MemberActivity extends BaseMvpActivity<UpgradeContract.View, UpgradePresenter> implements UpgradeContract.View {
    @BindView(R.id.txt_level_name)
    TextView txtLevelName;
    @BindView(R.id.txt_describe)
    TextView txtDescribe;
    @BindView(R.id.btn_upgrade)
    Button btnUpgrade;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler_entrance)
    RecyclerView recyclerEntrance;
    @BindView(R.id.txt_vip)
    TextView txtVip;

    @Override
    protected int getLayout() {
        return R.layout.activity_member;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerEntrance.setLayoutManager(linearLayoutManager);
        recyclerEntrance.addItemDecoration(new SpacesItemDecoration(0));
        List<String> fruitList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fruitList.add(i + "");
        }
        FastEntranceAdapter adapter = new FastEntranceAdapter(fruitList);
        recyclerEntrance.setAdapter(adapter);
        getMvpPresenter().getUserUpgrade(userModel.getId(), userModel.getUser_channel_id());

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().getUserInfo(userModel.getId(), userModel.getUser_channel_id());
        getMvpPresenter().getUserUpgrade(userModel.getId(), userModel.getUser_channel_id());
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getMvpPresenter().getUserUpgrade(userModel.getId(), userModel.getUser_channel_id());
            }
        });
    }

    @Override
    public void setUpgrade(UpgradeModel models) {
        refreshLayout.finishRefresh();
        txtDescribe.setText(models.getDescribe());
        txtLevelName.setText(models.getLevel_name());
        btnUpgrade.setText("开通" + models.getUpgrade_level_name());
        txtVip.setText("升级至" + models.getUpgrade_level_name() + "每月权益预估");
    }

    @Override
    public void showError(Throwable throwable) {
        refreshLayout.finishRefresh(false);
    }

    @Override
    public void showPayPage(String page) {
        Bundle bundle = new Bundle();
        bundle.putString("link", BaseApi.getBaseUrl() + TaoBaoKeApi.UPGRADE_PAY + "?orderId=" + page);
        bundle.putInt("type", 1);
        IntentUtils.get().goActivity(mContext, WebViewActivity.class, bundle);

    }

    @Override
    public void setUserModel(UserModel userModel) {
        MyApplication.mApplication.setUserModel(userModel);
        EventBus.getDefault().post(new RxEvent(1, Constant.UPDATE_USER));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_upgrade, R.id.img_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_upgrade:
                getMvpPresenter().getUpgradeApply(userModel.getId(), userModel.getUser_channel_id());
                break;
            case R.id.img_me:
                finish();
                break;
        }
    }
}
