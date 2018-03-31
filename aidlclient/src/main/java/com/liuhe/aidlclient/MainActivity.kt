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
    init {
        println("init$companionField")
    }

    companion object {
        var companionField = "haha"
    }

    var iService: IRemoteInterface? = null
    private var childHandler: Handler? = null

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
            intent.`package` = "com.liuhe.aidlservice"
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



    /**
     * 子线程之间的通信
     */
    private fun connChildThread() {
        Thread(Runnable {
            Looper.prepare()
            childHandler = @SuppressLint("HandlerLeak")
            object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    System.out.println("这个消息是从-->>" + msg.obj + "过来的，在" + "btn的子线程当中" + "中执行的")
                }
            }
            Looper.loop()//开始轮循
        }).start()

        Thread(Runnable {
            val msg = childHandler?.obtainMessage()
            msg?.obj = "btn2当中子线程"
            childHandler?.sendMessage(msg)
        }).start()
    }
}
