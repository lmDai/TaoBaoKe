package com.bestsoft.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.IncomeDetailModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.InComeListContract;
import com.bestsoft.mvp.presenter.InComeListPresenter;
import com.bestsoft.ui.adapter.IncomeDetailAdapter;
import com.bestsoft.ui.widget.IncomPopWindow;
import com.bestsoft.utils.RecyclerViewUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益明细
 */
@CreatePresenterAnnotation(InComeListPresenter.class)
public class IncomeDetailsActivity extends BaseMvpActivity<InComeListContract.View, InComeListPresenter> implements InComeListContract.View {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.txt_total_income)
    TextView txtTotalIncome;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private IncomeDetailAdapter incomeDetailAdapter;
    private String type = "0";//0.全部，1.佣金流水，2.提现流水，3.余额流水
    private IncomPopWindow incomPopWindow;

    @Override
    protected int getLayout() {
        return R.layout.activity_income_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText("明细");
        txtRight.setVisibility(View.VISIBLE);
        txtRight.setText("全部");
        Drawable drawable = mContext.getResources().getDrawable(R.drawable
                .ic_dropdown_normal);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        txtRight.setCompoundDrawables(null, null, drawable, null);
        incomeDetailAdapter = new IncomeDetailAdapter(R.layout.item_income_detail);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(incomeDetailAdapter);
        getMvpPresenter().getUserBills(type, userModel.getId(), userModel.getUser_channel_id(), true);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        incomeDetailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMvpPresenter().getUserBills(type, userModel.getId(), userModel.getUser_channel_id(), false);
            }
        }, recyclerView);
    }

    @Override
    public void showUserBillList(List<IncomeDetailModel> models, String total, boolean isRefresh) {
        txtTotalIncome.setText("¥" + total);
        RecyclerViewUtils.handleNormalAdapter(incomeDetailAdapter, models, isRefresh);
    }

    @Override
    public void showError(Throwable throwable, boolean isRefresh) {
        RecyclerViewUtils.handError(incomeDetailAdapter, isRefresh);
    }

    @OnClick({R.id.img_back, R.id.txt_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_right:
                List<String> list = new ArrayList<>();
                list.add("全部");
                list.add("佣金流水");
                list.add("提现流水");
                list.add("余额流水");
                if (incomPopWindow == null) {
                    incomPopWindow = new IncomPopWindow(mContext, list, new IncomPopWindow.TagClickListener() {
                        @Override
                        public void tagClickListener(int position) {
                            type = position + "";
                            getMvpPresenter().getUserBills(type, userModel.getId(), userModel.getUser_channel_id(), true);
                        }
                    });
                    incomPopWindow.showPopupWindow(toolbar);
                } else {
                    Log.i("single",incomPopWindow.isShowing()+"");
                    if (incomPopWindow.isShowing()) {
                        incomPopWindow.dismiss();
                    } else {
                        incomPopWindow.showPopupWindow(toolbar);
                    }
                }
                break;
        }
    }
}
