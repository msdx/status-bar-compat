package com.githang.statusbar;

import android.view.Window;

/**
 * 状态栏接口
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-06-23
 * @since 2016-06-23
 */

interface IStatusBar {
    void setStatusBarColor(Window window, int color, boolean lightStatusBar);
}
