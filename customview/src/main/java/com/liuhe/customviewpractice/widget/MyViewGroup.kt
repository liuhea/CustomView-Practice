package com.liuhe.customviewpractice.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 *  自定义ViewGroup
 * @author liuhe
 * @date 2018-04-01
 */
class MyViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    //继承ViewGroup必须实现的方法，用于对子视图的摆放
    // 当前控件对应的边的左上右下
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}
