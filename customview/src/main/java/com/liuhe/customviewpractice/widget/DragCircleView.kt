package com.liuhe.customviewpractice.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.liuhe.customviewpractice.utils.GeometryUtil
import com.liuhe.customviewpractice.utils.getStatusBarHeight

/**
 * 拖拽圆
 * @author liuhe
 * @date 2018-04-08
 */
class DragCircleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var paint: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        flags = Paint.ANTI_ALIAS_FLAG
    }
    /**
     * 固定圆圆心
     */
    private var stableCircleCenter = PointF(200f, 200f)
    /**
     * 固定圆半径
     */
    private var stableCircleRadius = 20f
    /**
     * 固定圆附着点
     */
    private var stablePoints = listOf(PointF(200f, 180f), PointF(200f, 220f))

    /**
     * 拖拽圆圆心
     */
    private var dragCircleCenter = PointF(100f, 100f)
    /**
     * 拖拽圆半径
     */
    private var dragCircleRadius = 30f
    /**
     *拖拽圆附着点
     */
    private var dragPoints = listOf(PointF(100f, 70f), PointF(100f, 130f))

    /**
     * 贝塞尔曲线参考点
     */
    private var controlPoint = PointF(150f, 150f)

    var downX = 0f
    var downY = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 调整画布，移除状态栏
        canvas?.save()
        canvas?.translate(0f, getStatusBarHeight(this).toFloat())

        // 计算两个圆圆心的斜率
        val dx = dragCircleCenter.x - stableCircleCenter.x
        val dy = dragCircleCenter.y - stableCircleCenter.y

        // 计算斜率
        var lineK = 0.0
        if (dx != 0f) {
            lineK = ((dy / dx).toDouble())
        }
        // 将计算后的数据赋值给原有的静态数据
        dragPoints = GeometryUtil.getIntersectionPoints(dragCircleCenter, dragCircleRadius, lineK).toList()
        stablePoints = GeometryUtil.getIntersectionPoints(stableCircleCenter, stableCircleRadius, lineK).toList()
        controlPoint = GeometryUtil.getMiddlePoint(dragCircleCenter, stableCircleCenter)

        //绘制中间图形
        val path = Path().apply {
            //1.移动到固定圆的附着点1
            moveTo(stablePoints[0].x, stablePoints[0].y)
            //2.向拖拽圆附着点1绘制贝塞尔曲线
            quadTo(controlPoint.x, controlPoint.y, dragPoints[0].x, dragPoints[0].y)
            //3.向拖拽圆附着点2绘制直线
            lineTo(dragPoints[1].x, dragPoints[1].y)
            //4.向固定圆附着点2绘制贝塞尔曲线
            quadTo(controlPoint.x, controlPoint.y, stablePoints[1].x, stablePoints[1].y)
            //5.闭合
            close()

        }
        canvas?.drawPath(path, paint)
        // 6.重置路径，防止path重叠
        path.reset()
        canvas?.drawCircle(stableCircleCenter.x, stableCircleCenter.y, stableCircleRadius, paint)
        canvas?.drawCircle(dragCircleCenter.x, dragCircleCenter.y, dragCircleRadius, paint)
        canvas?.restore()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // event.getX() 点击的点距离当前自定义控件左边缘的距离
                // event.getRawX() 点击的点距离屏幕左边缘的距离
                downX = event.rawX
                downY = event.rawY
                dragCircleCenter.set(downX, downY)
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                downX = event.rawX
                downY = event.rawY
                dragCircleCenter.set(downX, downY)
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
            }
        }
        return true
    }
}