package com.liuhe.customviewpractice.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.liuhe.customviewpractice.bean.PieData

/**
 *绘制饼状图
 *
 * 1. 最内侧扇形组成的圆形区域
 *      （1）定义一个起始角度
 *      （2）计算每一块扇形的弧度
 *      （3）遍历数据，每一个起始角度是上一个扇形的结束角度
 * 2. 中间短线段的绘制
 * 3. 最外侧文本的绘制
 *
 * @user liuhe
 * @date 27/03/2018
 */
class PieView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    /**
     * 画笔
     */
    lateinit var paint: Paint

    /**
     * 路径
     */
    lateinit var path: Path
    /**
     * 数据源
     */
    var pies: List<PieData>? = null

    /**
     * 扇形的外接矩形
     */
    private lateinit var rectF: RectF

    /**
     * 圆心
     */
    private var circleX = 0
    private var circleY = 0
    /**
     * 圆的半径
     */
    private var radius = 0f


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    private fun init() {
        rectF = RectF()
        path = Path()

        paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
            // 抗锯齿（毛刺），开启后，占用内存
            isAntiAlias = true
        }
    }

    /**
     * 当自定义控件的尺寸已经决定好之后的回调
     * 这个方法在onMeasure方法之后执行，最终的测量结果已经产生
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.circleX = w
        this.circleY = h
        /*
        * 防止绘制后，超出屏幕，获取屏幕宽高德较小值
         */
        var min = Math.min(w, h)
        radius = min * 0.7f / 2

        rectF.left = -radius
        rectF.top = -radius
        rectF.right = radius
        rectF.bottom = radius
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        // 移动画布，以中心为左边原点
        canvas?.translate(circleX.toFloat() / 2, circleY.toFloat() / 2)
        // 绘制扇形区域
        canvasPie(canvas)
        canvas?.restore()
    }

    private fun canvasPie(canvas: Canvas?) {
        var startAngle = 0f
        pies?.forEach {
            paint.color = it.color.toInt()
            // 每次绘制要移动到原点
            path.moveTo(0f, 0f)
            path.arcTo(rectF, startAngle, it.ratio)
            // 每个扇形区域的起始点是上一个扇形区域的终点
            startAngle += it.ratio
            canvas?.drawPath(path, paint)
            // 在每次绘制扇形之后要对path重置操作，这样就可以清楚上一次绘制path使用的画笔的相关记录
            path.reset()
        }
    }


    fun setData(data: List<PieData>) {
        this.pies = data
        var totalRadio = 0f

        pies?.forEach {
            totalRadio += it.ratio
        }

        /**
         * 设置每个扇形的占比
         */
        pies?.forEach { it.ratio = it.ratio / totalRadio * 360 }
    }
}