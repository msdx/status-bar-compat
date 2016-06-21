StatusBarCompat
---

# 简介
StatusBarCompat是一个用于设置系统状态栏颜色的兼容库，兼容Android 4.4.2(API 19)以上，使用简单，仅需要一行代码的调用。

# 效果展示
![5.1.1运行效果](images/Android5.1.1_P7.jpg)
![6.0深色状态栏白色文字](images/Android6.0_5C_dark.jpg)
![6.0浅色状态栏黑色文字](images/Android6.0_5C_light.jpg)

#使用方式

只需要在你的Activity的onCreate代码中加入以下一行代码：
```java
    StatusBarCompat.setStatusBarColor(this, color, lightStatusBar);
```
