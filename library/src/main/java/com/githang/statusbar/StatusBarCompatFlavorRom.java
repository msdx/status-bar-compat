package com.githang.statusbar;

import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 适配小米
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-06-22
 * @since 2016-06-22
 */

class StatusBarCompatFlavorRom {
    static void setLightStatusBar(Window window, boolean lightStatusBar) {
        if (StatusBarCompatMIUI.isMe()) {
            StatusBarCompatMIUI.setLightStatusBar(window, lightStatusBar);
        } else if (StatusBarCompatMeizu.isMe()) {
            StatusBarCompatMeizu.setLightStatusBar(window, lightStatusBar);
        }
    }

    static class StatusBarCompatMIUI {
        static boolean isMe() {
            return "Xiaomi".equals(Build.MANUFACTURER);
        }

        static void setLightStatusBar(Window window, boolean lightStatusBar) {
            Class<? extends Window> clazz = window.getClass();
            try {
                Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                int darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                extraFlagField.invoke(window, lightStatusBar ? darkModeFlag : 0, darkModeFlag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class StatusBarCompatMeizu {
        static boolean isMe() {
            final Method method;
            try {
                method = Build.class.getMethod("hasSmartBar");
                return method != null;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return false;
        }

        static void setLightStatusBar(Window window, boolean lightStatusBar) {
            WindowManager.LayoutParams params = window.getAttributes();
            try {
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(params);
                if (lightStatusBar) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(params, value);
                window.setAttributes(params);
                darkFlag.setAccessible(false);
                meizuFlags.setAccessible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
