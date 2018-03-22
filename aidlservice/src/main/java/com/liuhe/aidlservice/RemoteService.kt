package com.liuhe.aidlservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import com.liuhe.kotlinutilslib.log
import android.os.Looper
import com.liuhe.kotlinutilslib.toast


/**
 * @author liuhe
 * @date 2018/3/21
 */
class RemoteService : Service() {

    val mHandler = Handler()

    class MyBinder(private var context: Context) : IRemoteInterface.Stub() {
        override fun doSomething(msg: String?) {
            println("RemoteService->${Thread.currentThread().name}")
//            03-22 16:19:43.831 14010-14023/com.liuhe.aidlservice I/System.out: RemoteService->Binder:14010_2

            val handler = Handler(Looper.getMainLooper())
            handler.post({ context.toast("RemoteService 收到了来自Client的消息：$msg+ ${Thread.currentThread().name}") })
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder(this@RemoteService)
    }

    override fun onCreate() {
        super.onCreate()
        "RemoteService onCreate".log()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        object : Thread() {
            override fun run() {
                mHandler.post({
                    toast("RemoteService onStartCommand->${Thread.currentThread().name}")
                })
            }
        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun bindService(service: Intent?, conn: ServiceConnection?, flags: Int): Boolean {
        "RemoteService bindService".log()
        return super.bindService(service, conn, flags)
    }

    override fun unbindService(conn: ServiceConnection?) {
        "RemoteService unbindService".log()
        super.unbindService(conn)
    }

    override fun onDestroy() {
        super.onDestroy()
        "RemoteService onDestroy".log()
    }
}