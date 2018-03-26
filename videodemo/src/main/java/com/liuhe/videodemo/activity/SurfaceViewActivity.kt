package com.liuhe.videodemo.activity

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import com.liuhe.kotlinutilslib.CheckPermissionsActivity
import com.liuhe.kotlinutilslib.toast
import com.liuhe.videodemo.R
import com.liuhe.videodemo.netUrl
import kotlinx.android.synthetic.main.activity_surfview.*

class SurfaceViewActivity : CheckPermissionsActivity() {
    var mediaPlayer: MediaPlayer? = null
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surfview)
        sp = this.getSharedPreferences("config", Context.MODE_PRIVATE)!!
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                if (null != mediaPlayer) {
                    val currentPosition = mediaPlayer!!.currentPosition
                    sp.edit().putInt("current", currentPosition).commit()
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()
                }
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                try {
                    mediaPlayer = MediaPlayer()
//                    mediaPlayer!!.setDataSource("/storage/emulated/0/struggle.mp4")
                    mediaPlayer!!.setDataSource(this@SurfaceViewActivity, Uri.parse(netUrl))
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.setOnPreparedListener({
                        mediaPlayer?.start()
                        mediaPlayer?.seekTo(sp.getInt("current", 0))
                    })

                    mediaPlayer!!.setOnBufferingUpdateListener({ mediaPlayer: MediaPlayer, i: Int ->

                        toast("currentPercent=$i")

                    })

                    mediaPlayer!!.setOnInfoListener { mp, what, extra ->
                        when (what) {
                            MediaPlayer.MEDIA_INFO_BUFFERING_START ->
                                toast("开始卡顿")
                            MediaPlayer.MEDIA_INFO_BUFFERING_END ->
                                toast("卡顿结束")
                        }
                        true
                    }

                    mediaPlayer!!.setOnErrorListener { _, _, _ ->
                        true
                    }
                } catch (e: Exception) {
                }
                mediaPlayer?.setDisplay(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        sp.edit().clear()
    }
}