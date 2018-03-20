package com.liuhe.customviewpractice

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.liuhe.kotlinutilslib.log

/**
 * 自定义View 默认四个构造函数
 *
 *
 * Created by liuhe on 20/03/2018.
 *
 * java类的加载过程 静态代码块及静态成员->成员变量->构造函数
 * kotlin 成员变量->主构造函数和init
 */
class CanvasView(context: Context) : View(context) {

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        "onMeasure".log()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        "onLayout".log()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        "onDraw".log()
    }
}