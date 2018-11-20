package com.bestsoft.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestsoft.Constant;
import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.blankj.utilcode.util.LogUtils;
import com.mob.paysdk.AliPayAPI;
import com.mob.paysdk.MobPayAPI;
import com.mob.paysdk.OnPayListener;
import com.mob.paysdk.PayOrder;
import com.mob.paysdk.PayResult;
import com.mob.paysdk.PaySDK;
import com.mob.paysdk.WXPayAPI;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员充值
 */

public class MemberRechargeActivity extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    /**
     * 区别三种支付方式 0:微信支付 1:支付宝
     **/
    public static int payWay = Constant.MY_WALLET;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_decrease)
    ImageView imgDecrease;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.img_increase)
    ImageView imgIncrease;
    @BindView(R.id.btn_switch)
    TextView btnSwitch;
    @BindView(R.id.txt_payment_amount)
    TextView txtPaymentAmount;
    @BindView(R.id.recharge_zhifubao_cb)
    ImageView rechargeZhifubaoCb;
    @BindView(R.id.ll_wechat)
    LinearLayout llWechat;
    @BindView(R.id.recharge_wechat_cb)
    ImageView rechargeWechatCb;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.btn_pay)
    Button btnPay;
    private int amount = 1; //购买数量

    @Override
    protected int getLayout() {
        return R.layout.activity_member_recharge;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        txtRight.setVisibility(View.VISIBLE);
        txtRight.setText("充值记录");
        txtTitle.setText(mContext.getString(R.string.title_member_recharge));

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }


    @OnClick({R.id.ll_alipay, R.id.ll_wechat, R.id.img_back, R.id.txt_right,
            R.id.img_decrease, R.id.img_increase, R.id.btn_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_decrease:
                if (amount > 1) {
                    amount--;
                    txtNumber.setText(amount + "");
                }
                break;
            case R.id.img_increase:
                amount++;
                txtNumber.setText(amount + "");
                break;
            case R.id.ll_wechat:
                checkChanges(0);
                break;
            case R.id.ll_alipay:
                checkChanges(1);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_right:
                //todo 充值记录
                break;
            case R.id.btn_pay:
                //todo 确认支付
// 如果想获取支付宝的支付api，可以这样子做：
                PayOrder order = new PayOrder();
                order.setOrderNo("订单号");
                order.setAmount(15);
                order.setSubject("支付标题");
                order.setBody("支付主体");
                AliPayAPI alipay = PaySDK.createMobPayAPI(AliPayAPI.class);
                alipay.pay(order, new OnPayListener<PayOrder>() {
                    @Override
                    public boolean onWillPay(String ticketId, PayOrder payOrder, MobPayAPI mobPayAPI) {
                        // TODO 保存本次支付操作的 ticketId
                        // 返回false表示不阻止本次支付
                        LogUtils.i(order.getAmount());
                        return false;
                    }

                    @Override
                    public void onPayEnd(PayResult payResult, PayOrder payOrder, MobPayAPI mobPayAPI) {
                        // TODO 处理支付的结果，成功或失败可以在payResult中获取
                    }
                });
// 如果想获取微信的支付api，可以这样子做：
//               WXPayAPI wxpay = PaySDK.createMobPayAPI(WXPayAPI.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 改变选中
     */
    private void checkChanges(int index) {
        if (index == 1) {//支付宝
            rechargeZhifubaoCb.setImageResource(R.drawable.ic_pay_selected);
            rechargeWechatCb.setImageResource(R.drawable.ic_pay_unselected);
        } else {//微信
            rechargeWechatCb.setImageResource(R.drawable.ic_pay_selected);
            rechargeZhifubaoCb.setImageResource(R.drawable.ic_pay_unselected);
        }
    }
}