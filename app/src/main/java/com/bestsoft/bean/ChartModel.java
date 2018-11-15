package com.bestsoft.bean;

import java.util.List;

/**
 * @package: com.bestsoft.bean
 * @user:xhkj
 * @date:2018/11/15
 * @description:
 **/
public class ChartModel {

    /**
     * teams_count : 1
     * commission : 0
     * potential_count : 1
     * direct_count : 0
     * indirect_count : 0
     * chart : [{"date":"2018-11-09","commission":0},{"date":"2018-11-10","commission":0},{"date":"2018-11-11","commission":0},{"date":"2018-11-12","commission":0},{"date":"2018-11-13","commission":0},{"date":"2018-11-14","commission":0},{"date":"2018-11-15","commission":0}]
     */

    private String teams_count;
    private String commission;
    private String potential_count;
    private String direct_count;
    private String indirect_count;
    private List<ChartBean> chart;

    public String getTeams_count() {
        return teams_count;
    }

    public void setTeams_count(String teams_count) {
        this.teams_count = teams_count;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getPotential_count() {
        return potential_count;
    }

    public void setPotential_count(String potential_count) {
        this.potential_count = potential_count;
    }

    public String getDirect_count() {
        return direct_count;
    }

    public void setDirect_count(String direct_count) {
        this.direct_count = direct_count;
    }

    public String getIndirect_count() {
        return indirect_count;
    }

    public void setIndirect_count(String indirect_count) {
        this.indirect_count = indirect_count;
    }

    public List<ChartBean> getChart() {
        return chart;
    }

    public void setChart(List<ChartBean> chart) {
        this.chart = chart;
    }

    public static class ChartBean {
        /**
         * date : 2018-11-09
         * commission : 0
         */

        private String date;
        private float commission;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public float getCommission() {
            return commission;
        }

        public void setCommission(float commission) {
            this.commission = commission;
        }
    }
}
