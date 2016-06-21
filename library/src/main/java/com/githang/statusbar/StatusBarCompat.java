package com.githang.statusbar;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompatM.setStatusBarColor(activity, color, lightStatusBar);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            该方法无效，暂时屏蔽，采用以下方式来设置系统状态栏
//            StatusBarCompatLollipop.setStatusBarColor(activity, color, lightStatusBar);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarCompatKitkat.setStatusBarColor(activity, color, lightStatusBar);
        }
    }

    public static void setFitsSystemWindows(Activity activity, boolean fitSystemWindows) {
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
            ViewCompat.setFitsSystemWindows(mChildView, fitSystemWindows);
        }
    }
}
