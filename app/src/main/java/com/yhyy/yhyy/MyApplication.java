package com.yhyy.yhyy;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.yhyy.qwframe.control.CrashHandler;
import com.yhyy.yhyy.config.AppConfig;

public class MyApplication extends MultiDexApplication {
    private static Context context;
    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        // 这里不用判断instance是否为空
        return myApplication;
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        myApplication = this;
        context = this.getApplicationContext();
        Stetho.initializeWithDefaults(this);
        CrashHandler.create(context, AppConfig.SAVEFILENAME);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
