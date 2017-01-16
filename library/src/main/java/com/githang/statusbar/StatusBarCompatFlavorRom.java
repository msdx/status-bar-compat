package com.githang.statusbar;

import android.os.Build;
import android.os.Environment;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 适配小米
 *
 * @author msdx (msdx.android@qq.com)
 * @version 0.5
 * @since 0.2
 */

class StatusBarCompatFlavorRom {

    interface ILightStatusBar {
        void setLightStatusBar(Window window, boolean lightStatusBar);
    }

    static final ILightStatusBar IMPL;

    static {
        if (MIUILightStatusBarImpl.isMe()) {
            IMPL = new MIUILightStatusBarImpl();
        } else if (MeizuLightStatusBarImpl.isMe()) {
            IMPL = new MeizuLightStatusBarImpl();
        } else {
            IMPL = new ILightStatusBar() {
                @Override
                public void setLightStatusBar(Window window, boolean lightStatusBar) {
                }
            };
        }
    }

    static void setLightStatusBar(Window window, boolean lightStatusBar) {
        IMPL.setLightStatusBar(window, lightStatusBar);
    }

    static class MIUILightStatusBarImpl implements ILightStatusBar {

        private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
        private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
        private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

        static boolean isMe() {
            FileInputStream is = null;
            try {
                is = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                Properties prop = new Properties();
                prop.load(is);
                return prop.getProperty(KEY_MIUI_VERSION_CODE) != null
                        || prop.getProperty(KEY_MIUI_VERSION_NAME) != null
                        || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE) != null;
            } catch (final IOException e) {
                return false;
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // ignore all exception
                    }
                }
            }
        }

        public void setLightStatusBar(Window window, boolean lightStatusBar) {
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

    static class MeizuLightStatusBarImpl implements ILightStatusBar {
        static boolean isMe() {
            return Build.DISPLAY.startsWith("Flyme");
        }

        public void setLightStatusBar(Window window, boolean lightStatusBar) {
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
