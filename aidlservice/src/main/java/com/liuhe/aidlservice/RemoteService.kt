package com.liuhe.aidlservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.liuhe.kotlinutilslib.log

/**
 * @author liuhe
 * @date 2018/3/21
 */
class RemoteService : Service() {
    class MyBinder(var context: Context) : IRemoteInterface.Stub() {
        override fun doSomething(msg: String?) {
            ("RemoteService 收到了来自Client的消息：" + msg).log()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder(this@RemoteService)
    }

    override fun onCreate() {
        super.onCreate()
        "RemoteService onCreate".log()
    }

    override fun onDestroy() {
        super.onDestroy()
        "RemoteService onDestroy".log()
    }
}