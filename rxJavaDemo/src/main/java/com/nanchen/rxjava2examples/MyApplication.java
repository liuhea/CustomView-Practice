package com.nanchen.rxjava2examples;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import java.util.List;

/**
 * Author: nanchen
 * Email: liushilin520@foxmail.com
 * Date: 2017-06-30  15:02
 */

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {

    public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        AndroidNetworking.initialize(getApplicationContext());
        Thread.setDefaultUncaughtExceptionHandler(this);
        System.out.println("-----MyApplication: getProcessName(this)" + getProcessName(this));
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                System.out.println(Thread.currentThread());
                Toast.makeText(getApplicationContext(), "thread=" + thread.getId() + " ex=" + ex.toString(), Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    public static String getProcessName(Context cxt) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }

        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            System.out.println("-----MyApplication: pid processName->" + procInfo.pid + " " + procInfo.processName);
            if (procInfo.pid == android.os.Process.myPid()) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
