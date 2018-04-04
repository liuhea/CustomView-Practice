package com.liuhe.kotlinutilslib

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager


/**
 *
 * @author liuhe
 * @create 2018-04-03
 *
 */

/**
 * 判断是否联网
 */
fun isConnected(context: Context): Boolean {
    val cm = context.applicationContext.getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = cm?.activeNetworkInfo
    return info != null && info!!.isConnected



}
