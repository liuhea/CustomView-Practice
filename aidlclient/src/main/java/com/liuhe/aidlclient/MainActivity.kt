package com.liuhe.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.liuhe.aidlservice.IRemoteInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var iService: IRemoteInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_main_bind.setOnClickListener({
            var intent = Intent()
            intent.action = "com.liuhe.aidlservice"
            /*
             服务端包名
            有些时候我们使用Service的时需要采用隐私启动的方式，但是Android 5.0一出来后，其中有个特性就是Service Intentmust be explitict，
            也就是说从Lollipop开始，service服务必须采用显示方式启动。
             */
            intent.`package`="com.liuhe.aidlservice"
            bindService(intent, MyConn(), Context.BIND_AUTO_CREATE)
        })

        btn_main_call.setOnClickListener({
            iService?.doSomething("我是Aidl Client")
        })
    }

    inner class MyConn : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = IRemoteInterface.Stub.asInterface(service)
        }
    }
}
