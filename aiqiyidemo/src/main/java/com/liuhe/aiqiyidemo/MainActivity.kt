package com.liuhe.aiqiyidemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.util.LruCache
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = ListView(this)
    }
}
