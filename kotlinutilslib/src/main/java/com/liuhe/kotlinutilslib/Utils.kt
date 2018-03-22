package com.liuhe.kotlinutilslib

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import android.util.DisplayMetrics


/**
 * Created by liuhe on 20/03/2018.
 */

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(msg)) {
        return
    }
    Toast.makeText(this.applicationContext, msg, duration).show()
}

fun String.log(logType: Int = Log.DEBUG) {
    if (TextUtils.isEmpty(this)) {
        return
    }
    when (logType) {
        Log.ERROR -> Log.e("liuhe->e", this)
        else -> Log.d("liuhe->d", this)
    }
}

/**
 *  屏幕宽（像素，如：3200px）
 */
fun getScreenWidth(context: Context): Float {
    val appContext = context.applicationContext
    var dm = appContext.resources.displayMetrics as DisplayMetrics
    return dm.widthPixels.toFloat()
}

/**
 * 屏幕高（像素，如：1280px）
 */
fun getScreenHeight(context: Context): Float {
    val appContext = context.applicationContext
    var dm = appContext.resources.displayMetrics as DisplayMetrics
    return dm.heightPixels.toFloat()
}

/**
 * 屏幕密度（每寸像素：120/160/240/320）
 */
fun getScreenDPI(context: Context): Float {
    val appContext = context.applicationContext
    var dm = appContext.resources.displayMetrics as DisplayMetrics
    return dm.densityDpi.toFloat()
}

/**
 *  屏幕密度（像素比例：0.75/1.0/1.5/2.0）
 */
fun getScreenDensity(context: Context): Float {
    val appContext = context.applicationContext
    var dm = appContext.resources.displayMetrics as DisplayMetrics
    return dm.density
}


