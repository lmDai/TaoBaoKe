package com.bestsoft.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.bestsoft.R;
import com.bestsoft.base.BaseActivity;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private String link;
    private int type = 0;
    private String realm = "http://test.bestsoft.channel.cqjjsms.com";
    private boolean isFirstLoad = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.toolbar)
                .statusBarColor(R.color.colorWhite)
                .statusBarDarkFont(true, 0.2f)
                .init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView(Bundle savedInstanceState) {
        txtTitle.setVisibility(View.GONE);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            link = bundle.getString("link");
            if (bundle.containsKey("type")) {
                type = bundle.getInt("type");
            }
        }
        settingWebView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                webView.loadUrl(link);
            }
        });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void settingWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
        if (type == 0) {
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String title = view.getTitle();
                    if (!TextUtils.isEmpty(title)) {
                        txtTitle.setVisibility(View.VISIBLE);
                        txtTitle.setText(title);
                    }
                    refreshLayout.finishRefresh();
//                view.loadUrl(String.format(Locale.CHINA, "javascript:document.body.style.paddingTop='%fpx'; void 0", DensityUtil.px2dp(webView.getPaddingTop())));
                }
            });
        } else {
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setEnablePureScrollMode(true);
            webView.setWebViewClient(new MyWebViewClient());
        }
    }

    /**
     * 重写WebViewClient
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //微信H5支付核心代码
            LogUtils.i(url + "dfsfsd");
            if (url.startsWith("weixin://wap/pay?")) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            }
            if (!(url.startsWith("http") || url.startsWith("https"))) {
                return true;
            }
            final PayTask task = new PayTask(mContext);
            boolean isInstercepter = task.payInterceptorWithUrl(url, true, new H5PayCallback() {
                @Override
                public void onPayResult(H5PayResultModel h5PayResultModel) {
                    final String url = h5PayResultModel.getReturnUrl();
                    WebViewActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!TextUtils.isEmpty(url)) {
                                Map<String, String> extraHeaders = new HashMap<>();
                                extraHeaders.put("Referer", realm);
                                view.loadUrl(url, extraHeaders);
                            }
                        }
                    });
                }
            });
            if (!isInstercepter) {
                Map<String, String> extraHeaders = new HashMap<>();
                extraHeaders.put("Referer", realm);
                view.loadUrl(url, extraHeaders);
            }

            return true;
        }

        //处理https请求
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
            handler.proceed();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.stopLoading();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }
}
