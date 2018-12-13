package com.githang.statusbar.demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.githang.statusbar.StatusBarCompat;

/**
 * @author 黄浩杭 (msdx.android@qq.com)
 * @since 2018-12-13
 */
class NoActionBarActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.setBackgroundColor(Color.CYAN);
        webView.loadUrl("http://www.githang.com");
        setContentView(webView);
        StatusBarCompat.setStatusBarColor(this, Color.BLUE);
    }

    @Override
    protected void onDestroy() {
        webView.stopLoading();
        webView.destroy();
        super.onDestroy();
    }
}