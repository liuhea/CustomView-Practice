package com.liuhe.aiqiyidemo

import android.app.Activity
import android.app.Application
import java.util.*

/**
 *
 * @author liuhe
 * @create 2018-04-11
 *
 */
class App : Application() {

    private val activityList = LinkedList<Activity>()

    /**
     * 添加Activity到容器中
     */
    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }

    /**
     * 移除已经销毁的Activity
     */
    fun removeActivity(activity: Activity) {
        activityList.remove(activity)
    }
}