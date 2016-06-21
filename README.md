StatusBarCompat
---

[ ![Download](https://api.bintray.com/packages/msdx/maven/StatusBarCompat/images/download.svg) ](https://bintray.com/msdx/maven/StatusBarCompat/_latestVersion)

# 简介
StatusBarCompat是一个用于设置系统状态栏颜色的兼容库，兼容Android 4.4.2(API 19)以上，使用简单，仅需要一行代码的调用。

# 效果展示
![5.1.1运行效果](images/Android5.1.1_P7.jpg)
![6.0深色状态栏白色文字](images/Android6.0_5C_dark.jpg)
![6.0浅色状态栏黑色文字](images/Android6.0_5C_light.jpg)

#使用方式

##声明仓库
确保在你的根项目的build.gradle中对`jcenter`的声明：
```gradle
allprojects {
    repositories {
        jcenter()
    }
}
```

## 声明依赖
在你要使用的module的`build.gradle`文件中声明以下依赖：
```gradle
    compile 'com.githang:status-bar-compat:0.1.1'
```

##代码调用
最后在你的Activity的onCreate代码中调用以下代码就可以了。
```java
    StatusBarCompat.setStatusBarColor(this, color, lightStatusBar);
```
