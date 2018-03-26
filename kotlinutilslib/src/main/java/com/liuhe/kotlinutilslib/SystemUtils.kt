package com.liuhe.kotlinutilslib

import android.app.ActivityManager
import android.content.Context


/**
 * Created by liuhe on 26/03/2018.
 */
fun getProcessName(context: Context, pid: Int): String {
    val applicationContext = context.applicationContext
    val manager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningAppProcesses = manager.runningAppProcesses
    for (processInfo in runningAppProcesses) {
        if (processInfo.pid == pid) {
            return processInfo.processName
        }
    }
    return ""
}
