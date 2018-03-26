package com.liuhe.videodemo

import android.os.Bundle
import android.os.Environment
import android.widget.MediaController
import com.liuhe.kotlinutilslib.CheckPermissionsActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.ActivityInfo
import android.net.Uri


class MainActivity : CheckPermissionsActivity() {

    private val localUrl = Environment.getExternalStorageDirectory().path + "/struggle.mp4"

    /**
     * 网络视频地址
     */
    private val netUrl = "http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

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
