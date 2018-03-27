package com.liuhe.customviewpractice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.jar.Attributes

/**
 * Created by liuhe on 27/03/2018.
 */
class CompassView(context: Context) : View(context) {
    lateinit var redPaint: Paint

    constructor(context: Context, attributes: Attributes) : this(context, attributes, 0)

    constructor(context: Context, attributes: Attributes, defStyle: Int) : this(context) {
        init()
    }

    private fun init() {
        redPaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            // 抗锯齿（毛刺），开启后，占用内存
            isAntiAlias = true
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    /**
     * 绘制罗盘
     * https://github.com/ChaosOctopus/ChaosCompass
     */
    private fun drawCompass(canvas: Canvas?) {
        val px = measuredWidth / 2
        val py = measuredHeight / 2

        //取较小的值为半径
        val radius = Math.min(px, py)
        //绘制背景
        canvas?.drawCircle(px.toFloat(), py.toFloat(), radius.toFloat(), redPaint)
//        用来保存Canvas的状态,save()方法之后的代码，可以调用Canvas的平移、放缩、旋转、裁剪等操作！
        canvas?.save()
    }

}