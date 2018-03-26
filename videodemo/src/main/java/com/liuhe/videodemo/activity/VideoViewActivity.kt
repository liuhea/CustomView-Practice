package com.liuhe.videodemo.activity

import android.annotation.TargetApi
import android.os.Bundle
import android.widget.MediaController
import com.liuhe.kotlinutilslib.CheckPermissionsActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import com.liuhe.kotlinutilslib.toast
import com.liuhe.videodemo.R
import com.liuhe.videodemo.netUrl


class VideoViewActivity : CheckPermissionsActivity() {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
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

//         onPrepared(mp: MediaPlayer) 解析视频的过程
        vv_main.setOnPreparedListener {
            vv_main.start()
        }

        vv_main.setOnInfoListener { _, what, _ ->
            when (what) {
                MediaPlayer.MEDIA_INFO_BUFFERING_START ->
                    toast("开始卡顿")
                MediaPlayer.MEDIA_INFO_BUFFERING_END ->
                    toast("卡顿结束")
            }
            true
        }
    }
}
