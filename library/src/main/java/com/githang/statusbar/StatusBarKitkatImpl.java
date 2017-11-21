package com.githang.statusbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


/**
 * 兼容KITKAT版本
 *
 * @author msdx (msdx.android@qq.com)
 * @version 0.4.1
 * @since 0.3
 */

class StatusBarKitkatImpl implements IStatusBar {
    private static final String STATUS_BAR_VIEW_TAG = "ghStatusBarView";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void setStatusBarColor(Window window, int color) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup decorViewGroup = (ViewGroup) window.getDecorView();
        View statusBarView = decorViewGroup.findViewWithTag(STATUS_BAR_VIEW_TAG);
        if (statusBarView == null) {
            statusBarView = new StatusBarView(window.getContext());
            statusBarView.setTag(STATUS_BAR_VIEW_TAG);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            decorViewGroup.addView(statusBarView);
        }
        statusBarView.setBackgroundColor(color);
        StatusBarCompat.internalSetFitsSystemWindows(window, true);
    }

}
