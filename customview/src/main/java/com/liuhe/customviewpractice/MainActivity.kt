package com.liuhe.customviewpractice

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.liuhe.kotlinutilslib.log
import android.os.Looper
import android.os.Message


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "onCreate()执行完毕".log()

        connChildThread()
    }

    private var childHandler: Handler? = null

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

    override fun onStart() {
        super.onStart()
        "onStart()执行完毕".log()
    }

    override fun onResume() {
        super.onResume()
        "onResume()执行完毕".log()
    }

    override fun onPause() {
        super.onPause()
        "onPause()执行完毕".log()
    }

    override fun onStop() {
        super.onStop()
        "onStop()执行完毕".log()
    }

    override fun onDestroy() {
        super.onDestroy()
        "onDestroy()执行完毕".log()
    }
}
