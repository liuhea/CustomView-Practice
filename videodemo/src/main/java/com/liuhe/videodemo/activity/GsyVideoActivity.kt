package com.liuhe.videodemo.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liuhe.videodemo.R
import com.liuhe.videodemo.netUrl
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_gsy.*

/**
 * Created by liuhe on 26/03/2018.
 */
class GsyVideoActivity : AppCompatActivity() {
    /**
     * 是否暂停
     */
    private var isPause: Boolean = false
    var gsyVideoHelper: GSYVideoHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gsy)

        initGsyVideo()
    }

    private fun initGsyVideo() {
        gsy_player.setUp(netUrl, false, null)
        gsyVideoHelper = GSYVideoHelper(this, gsy_player)
    }

    override fun onDestroy() {
        super.onDestroy()
        gsyVideoHelper?.releaseVideoPlayer()
    }

    override fun onPause() {
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        super.onResume()
        isPause = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}