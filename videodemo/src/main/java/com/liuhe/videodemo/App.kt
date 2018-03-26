package com.liuhe.videodemo

import android.app.Application
import com.liuhe.kotlinutilslib.log
import com.liuhe.kotlinutilslib.getProcessName


/**
 * Created by liuhe on 26/03/2018.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        packageName.log()
        val myPid = android.os.Process.myPid()
        (getProcessName(this, myPid)).log()
    }
}