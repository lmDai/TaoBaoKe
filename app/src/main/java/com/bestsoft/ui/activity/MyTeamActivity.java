package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bestsoft.R;
import com.bestsoft.base.BaseMvpActivity;
import com.bestsoft.bean.ChartModel;
import com.bestsoft.bean.PotentialFanModel;
import com.bestsoft.common.mvp_senior.annotaions.CreatePresenterAnnotation;
import com.bestsoft.mvp.contract.ChartDataContract;
import com.bestsoft.mvp.presenter.ChartDataPresenter;
import com.bestsoft.ui.adapter.PotentialFanAdapter;
import com.bestsoft.utils.ChartUtils;
import com.bestsoft.utils.RecyclerViewUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的团队
 */
@CreatePresenterAnnotation(ChartDataPresenter.class)
public class MyTeamActivity extends BaseMvpActivity<ChartDataContract.View, ChartDataPresenter> implements ChartDataContract.View {

    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_teams_count)
    TextView txtTeamsCount;
    @BindView(R.id.txt_commission)
    TextView txtCommission;
    @BindView(R.id.txt_potential_count)
    TextView txtPotentialCount;
    @BindView(R.id.txt_direct_count)
    TextView txtDirectCount;
    @BindView(R.id.txt_indirect_count)
    TextView txtIndirectCount;
    private PotentialFanAdapter potentialFanAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_team;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setText(mContext.getString(R.string.title_my_team));
        toolbar.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_toolbar));

        ChartUtils.initChart(lineChart);
        potentialFanAdapter = new PotentialFanAdapter(R.layout.item_potential_fan);
        RecyclerViewUtils.initLinerLayoutRecyclerView(recyclerView, mContext);
        recyclerView.setAdapter(potentialFanAdapter);
        initData();
        getMvpPresenter().getUserChart(userModel.getId(), userModel.getUser_channel_id());
    }

    private void initData() {
        List<PotentialFanModel> modelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            modelList.add(new PotentialFanModel());
        }
        potentialFanAdapter.setNewData(modelList);
    }

//    private List<Entry> getData(List<>) {
//        List<Entry> values = new ArrayList<>();
//        values.add(new Entry(0, 15, "08-1"));
//        values.add(new Entry(1, 15, "08-1"));
//        values.add(new Entry(2, 15, "08-1"));
//        values.add(new Entry(3, 20, "08-1"));
//        values.add(new Entry(4, 25, "08-1"));
//        values.add(new Entry(5, 20, "08-1"));
//        values.add(new Entry(6, 20, "08-1"));
//        return values;
//    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void setChartData(ChartModel models) {
        txtTeamsCount.setText(models.getTeams_count());
        txtCommission.setText(models.getCommission());
        txtPotentialCount.setText(models.getPotential_count());
        txtDirectCount.setText(models.getDirect_count());
        txtIndirectCount.setText(models.getIndirect_count());
        if (models.getChart() != null && models.getChart().size() > 0) {
            ChartUtils.notifyDataSetChanged(lineChart, getData(models.getChart()));
        }

    }

    private List<Entry> getData(List<ChartModel.ChartBean> chart) {
        List<Entry> values = new ArrayList<>();
        int i = 0;
        for (ChartModel.ChartBean bean : chart) {
            values.add(new Entry(i++, bean.getCommission(), bean.getDate()));
        }
        return values;
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
