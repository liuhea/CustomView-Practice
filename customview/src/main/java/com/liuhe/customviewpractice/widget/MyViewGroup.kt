package com.liuhe.customviewpractice.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import com.liuhe.customviewpractice.R

/**
 *  自定义ViewGroup
 * @author liuhe
 * @date 2018-04-01
 */
class MyViewGroup @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {
    init {

        for (i in 0..5) {
            addView(ImageView(getContext()).apply { setImageResource(R.mipmap.ic_launcher) })
        }
    }

    //继承ViewGroup必须实现的方法，用于对子视图的摆放
    // 当前控件对应的边的左上右下
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 获取对应位置的孩子视图
//        val childAt = this.getChildAt(0)
//        // 摆放孩子视图，layout方法可以用来改变图形的大小
//        childAt.layout(0, 0, 100, 100)

        // 正三角
        var childIndex = 0
        var top = 0
        for (i in 1..3) {
            println("MyViewGroup-->i=$i")
            for (j in 0 until i) {
                println("MyViewGroup-->j=$j")
                val child = this.getChildAt(childIndex)
                child.layout(50 * j, top, 50 * (j + 1), top + 50)
                childIndex++
            }
            top += 50
        }
    }
}
