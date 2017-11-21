package com.githang.statusbar;

import android.view.Window;

/**
 * 状态栏接口
 *
 * @author msdx (msdx.android@qq.com)
 * @version 0.3
 * @since 0.3
 */

interface IStatusBar {
    /**
     * Set the status bar color
     *
     * @param window The window to set the status bar color
     * @param color  Color value
     */
    void setStatusBarColor(Window window, int color);
}
