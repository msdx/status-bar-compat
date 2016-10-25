package com.githang.statusbar;

import android.os.Build;

/**
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-10-25
 * @since 2016-10-25
 */
public class StatusBarExclude {
    static boolean exclude = false;

    public static void excludeIncompatibleFlyMe() {
        try {
            Build.class.getMethod("hasSmartBar");
        } catch (NoSuchMethodException e) {
            exclude |= Build.BRAND.contains("Meizu");
        }
    }
}
