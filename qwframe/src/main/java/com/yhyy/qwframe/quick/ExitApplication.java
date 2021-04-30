package com.yhyy.qwframe.quick;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IceWolf on 2019/9/12.
 */
public class ExitApplication extends Application {
    private static List<AppCompatActivity> appCompatActivityList = new LinkedList<>();
    private static AppCompatActivity appCompatActivity;
    private static ExitApplication exitApplication;

    private ExitApplication() {
    }

    // 单例模式中获取唯一的ExitApplication实例
    public static ExitApplication getInstance() {
        if (null == exitApplication) {
            exitApplication = new ExitApplication();
        }
        return exitApplication;

    }

    public static void removeActivity(AppCompatActivity appCompatActivity) {
        appCompatActivityList.remove(appCompatActivity);
    }

    public static void setCurrentActivity(AppCompatActivity activity) {
        appCompatActivity = activity;
    }

    public static AppCompatActivity getCurrentActivity() {
        return appCompatActivity;
    }

    // 添加Activity到容器中
    public void addActivity(AppCompatActivity appCompatActivity) {
        appCompatActivityList.add(appCompatActivity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        try {
            for (AppCompatActivity appCompatActivity : appCompatActivityList) {
                appCompatActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
