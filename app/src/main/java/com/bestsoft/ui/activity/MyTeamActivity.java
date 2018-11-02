package com.bestsoft.ui.activity;

import android.os.Bundle;

import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.bestsoft.utils.ChartUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的团队
 */
public class MyTeamActivity extends BaseActivity {


    @BindView(R.id.line_chart)
    LineChart lineChart;

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
        ChartUtils.initChart(lineChart);
        ChartUtils.notifyDataSetChanged(lineChart, getData());
    }

    private List<Entry> getData() {
        List<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 15,"08-1"));
        values.add(new Entry(1, 15,"08-1"));
        values.add(new Entry(2, 15,"08-1"));
        values.add(new Entry(3, 20));
        values.add(new Entry(4, 25));
        values.add(new Entry(5, 20));
        values.add(new Entry(6, 20));
        return values;
    }
}
