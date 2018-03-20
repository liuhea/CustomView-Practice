package com.liuhe.kotlinutilslib

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast

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
