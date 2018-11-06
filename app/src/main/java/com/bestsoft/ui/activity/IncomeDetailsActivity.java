package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.bean.IncomeDetailModel;
import com.bestsoft.ui.adapter.IncomeDetailAdapter;
import com.bestsoft.utils.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 收益明细
 */
public class IncomeDetailsActivity extends BaseActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private IncomeDetailAdapter incomeDetailAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_income_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        incomeDetailAdapter = new IncomeDetailAdapter(R.layout.item_income_detail);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(incomeDetailAdapter);
        initData();
    }

    private void initData() {
        List<IncomeDetailModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new IncomeDetailModel());
        }
        incomeDetailAdapter.setNewData(modelList);
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


}
