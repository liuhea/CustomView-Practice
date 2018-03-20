package com.liuhe.customviewpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liuhe.kotlinutilslib.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        "onCreate()执行完毕".log()
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
