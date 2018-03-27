package com.liuhe.customviewpractice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Created by liuhe on 27/03/2018.
 */
class PathView(context: Context) : View(context) {
    private lateinit var greenPaint: Paint

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context) {
        init()
    }

    private fun init() {
        greenPaint = Paint().apply {
            color = Color.GREEN
            //设置防抖动
            isDither = true
            strokeWidth = 10f
            //抗锯齿
            isAntiAlias = true
            style = Paint.Style.STROKE

        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(200f, 0f, 200f, 1000f, greenPaint)
//        drawPath(canvas)
        drawQuad(canvas)

        doOtherThings(canvas)
    }

    /**
     * 绘制贝塞尔曲线
     * 1.起始点
     * 2.终点
     * 3.控制点：control point
     */
    private fun drawQuad(canvas: Canvas?) {
        val path = Path().apply {
            // 将path移动到绘制的起始点
            moveTo(200f, 200f)
            // 贝塞尔曲线-控制点、终点
            quadTo(500f, 400f, 200f, 500f)
            close()
        }
        canvas?.drawPath(path, greenPaint)
    }

    /**
     * 绘制线
     */
    private fun drawPath(canvas: Canvas?) {

        val path = Path().apply {
            // 将path移动到绘制的起始点
            moveTo(200f, 200f)
            // 以moveTo之后的点为起始点，以参数1，2为终点绘制一条线段
            lineTo(500f, 200f)
            lineTo(500f, 500f)
            lineTo(200f, 500f)
            close()
        }

        canvas?.drawPath(path, greenPaint)

//        canvas?.restore()
    }

    /**
     * canvas其他操作
     * 平移、旋转、缩放等操作之前必须先调用save方法处理后再调用restore方法
     */
    private fun doOtherThings(canvas: Canvas?) {
        canvas?.save()
        // 平移距离，dx,dy
//        canvas?.translate(100f, 0f)

    // 旋转角度
        canvas?.rotate(10f)
        //缩放比例，sx,sy
//        canvas?.scale(1.2f,0.5f)
        drawPath(canvas)
        canvas?.restore()
    }
}