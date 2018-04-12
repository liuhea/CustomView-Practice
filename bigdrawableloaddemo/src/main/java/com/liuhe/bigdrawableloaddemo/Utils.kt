package com.liuhe.bigdrawableloaddemo

import android.app.ActivityManager
import android.content.Context

/**
 *
 * @author liuhe
 * @create 2018-04-12
 *
 */
object  Utils {

    /**
     * 获取程序堆大小
     */
    fun getMaxHeapSize(context: Context): Pair<Int, Int> {
        var manager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val heapSize = manager.memoryClass
        val maxHeapSize = manager.largeMemoryClass
        return Pair(heapSize, maxHeapSize)

//        或者：val maxMemory = (Runtime.getRuntime().maxMemory() / (1024 * 1024)).toInt()
    }
}