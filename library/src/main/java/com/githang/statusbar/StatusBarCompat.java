package com.githang.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 设置系统状态栏颜色
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-06-20
 * @since 2016-06-20
 */

public class StatusBarCompat {
    /**
     * Set system status bar color.
     * @param activity
     * @param color status bar color
     * @param lightStatusBar if the status bar color is light. Only effective when API >= 23
     */
    public static void setStatusBarColor(Activity activity, int color, boolean lightStatusBar) {
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) > 0) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompatM.setStatusBarColor(window, color, lightStatusBar);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            该方法无效，暂时屏蔽，采用以下方式来设置系统状态栏
//            StatusBarCompatLollipop.setStatusBarColor(window, color, lightStatusBar);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitkat.setStatusBarColor(window, color, lightStatusBar);
        }
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void setFitsSystemWindows(Window window, boolean fitSystemWindows) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                mChildView.setFitsSystemWindows(fitSystemWindows);
            }
        }
    }
}
