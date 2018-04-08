package com.liuhe.customviewpractice.utils

import android.graphics.Rect
import android.view.View

/**
 *
 * @author liuhe
 * @date 2018-04-08
 */

/**
 * 获取状态栏高度
 */
fun getStatusBarHeight(view: View): Int {
    val rect = Rect()
    // 获取视图相应的可视范围，会把视图的左上右下的数据传入到一个矩形中
    view.getWindowVisibleDisplayFrame(rect)
    return rect.top
}