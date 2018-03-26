package com.liuhe.videodemo.activity

import android.os.Bundle
import android.widget.MediaController
import com.liuhe.kotlinutilslib.CheckPermissionsActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.ActivityInfo
import android.net.Uri
import com.liuhe.videodemo.R
import com.liuhe.videodemo.netUrl


class VideoViewActivity : CheckPermissionsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//横竖屏切换
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

//        vv_main.setVideoURI(Uri.parse(localUrl))
        vv_main.setVideoURI(Uri.parse(netUrl))
        val mediaController = MediaController(this)
        // 设置控制器控制的是哪一个VideoView
        mediaController.setAnchorView(vv_main)
        // 设置VideoView控制器
        vv_main.setMediaController(mediaController)
        vv_main.start()
    }
}
