package com.example.surfaceviewapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CheckPermissionsActivity() {
    var mediaPlayer: MediaPlayer? = null
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    mediaPlayer!!.setDataSource("/storage/emulated/0/struggle.mp4")
                    mediaPlayer!!.prepare()
                } catch (e: Exception) {
                }
                mediaPlayer?.setDisplay(holder)
                mediaPlayer?.start()
                mediaPlayer?.seekTo(sp.getInt("current", 0))
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