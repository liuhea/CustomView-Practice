package com.liuhe.videodemo

import android.os.Environment

/**
 * Created by liuhe on 26/03/2018.
 */
/**
 * 本地播放地址
 */
 val localUrl = Environment.getExternalStorageDirectory().path + "/struggle.mp4"

/**
 * 网络视频地址
 */
val netUrl = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=89206&editionType=default&source=aliyun"
